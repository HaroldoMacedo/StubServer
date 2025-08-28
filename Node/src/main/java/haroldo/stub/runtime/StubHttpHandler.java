package haroldo.stub.runtime;


import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import haroldo.stub.api.Api;
import haroldo.stub.api.Response;
import haroldo.stub.metrics.AvgStdDev;
import haroldo.stub.metrics.MaxMin;

import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Semaphore;

class StubHttpHandler implements HttpHandler {
    final Api api;
    private final long timeBetweenRequestReceivedNanoSec;
    private long lastRequestReceivedNanoSec = 0;

    private static int stubHandlerInstanceCount = 0;
    private final int stubHandlerInstanceNumber;
    private int concurrentThreads;

    private final MaxMin threadsMaxMin = new MaxMin();
    private final AvgStdDev responseTimeAvgStdDev = new AvgStdDev();
    private final AvgStdDev throttleAvgStdDev = new AvgStdDev();
    private final Semaphore maxThreadsSemphore;

    private static final int COUNTPERPRINT = 100;

    StubHttpHandler(int maxThroughPutPerSecond, Api api) {
        synchronized (this) {
            stubHandlerInstanceNumber = ++stubHandlerInstanceCount;
        }
        this.api = api;
        int permits = (int) Math.max(Math.ceil(maxThroughPutPerSecond * api.getAvgResponseTimeMS() / 1000.0 ), 1);
        this.maxThreadsSemphore = new Semaphore(permits);
//        this.maxThreadsSemphore = new Semaphore((int) Math.min(Math.ceil(maxThroughPutPerSecond * 1000.0 / api.getAvgResponseTimeMS()), 1));
        long nanoSecPerSecond = 1000000000;
        this.timeBetweenRequestReceivedNanoSec = nanoSecPerSecond / maxThroughPutPerSecond;
    }

    private int count = 0;

    @Override
    public void handle(HttpExchange exchange) {
        threadsMaxMin.addSample(concurrentThreads++);

        if (count++ % COUNTPERPRINT == 0)
            System.out.println(now() + ": " + stubHandlerInstanceNumber + " - " + count + ": Request '" + exchange.getRequestMethod() + " " + exchange.getRequestURI() + "' received! " +
                    "- Threads = (" + threadsMaxMin + ") - Response Time = (" + responseTimeAvgStdDev + ") - Throttle = (" + throttleAvgStdDev + ") " +
                    " semaphores available = " + maxThreadsSemphore.availablePermits());

        switch (exchange.getRequestMethod()) {
            case "GET":
                respondRequest(exchange, api.getNextGetResponse());
                break;
            case "POST":
                respondRequest(exchange, api.getNextPostResponse());
                break;
            case "PUT":
                respondRequest(exchange, api.getNextPutResponse());
                break;
            case "DELETE":
                respondRequest(exchange, api.getNextDeleteResponse());
                break;
            default:
                System.err.println("Method '" + exchange.getRequestMethod() + "' is not implemented.");
        }
        if (count % COUNTPERPRINT == 0)
            System.out.println(now() + " - " + count + ": Responded to '" + exchange.getRequestMethod() + " " + exchange.getRequestURI() + "'");
        synchronized (this) {
            concurrentThreads--;
        }
    }

    private void respondRequest(HttpExchange exchange, Response response) {
        long ms = System.currentTimeMillis();
        try {
            throttle(response.getLatencyMs());
            throttleAvgStdDev.addTimeMs(System.currentTimeMillis() - ms);
            OutputStream os = exchange.getResponseBody();
            Thread.sleep(response.getLatencyMs());
            responseTimeAvgStdDev.addTimeMs(System.currentTimeMillis() - ms);
            byte[] message = response.getMessage().getBytes();
            exchange.sendResponseHeaders(200, message.length);

            os.write(message);
            os.close();
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }

    }

    private void throttle(long latencyMs) {
        long waitNanoSec = System.nanoTime();
        try {
            maxThreadsSemphore.acquire();
            synchronized (this) {
                waitNanoSec = (lastRequestReceivedNanoSec + timeBetweenRequestReceivedNanoSec) - waitNanoSec;
                lastRequestReceivedNanoSec = System.nanoTime();
            }
            if (waitNanoSec <= 1000 * latencyMs)
                return;

            if (waitNanoSec < 1000000)
                Thread.sleep(0, (int) waitNanoSec);
            else
                Thread.sleep(waitNanoSec / 1000000, (int) waitNanoSec % 1000000);
        } catch (InterruptedException e) {
        } finally {
            maxThreadsSemphore.release();
        }

//        lastRequestReceivedNanoSec = System.nanoTime();
    }

    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MMM-yy HH:mm:ss.SSS");

    private String now() {
        return LocalDateTime.now().format(dateFormat);
    }
}
