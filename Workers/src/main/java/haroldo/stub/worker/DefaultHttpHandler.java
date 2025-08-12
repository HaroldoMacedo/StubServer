package haroldo.stub.worker;


import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import haroldo.stub.api.ApiResponse;
import haroldo.stub.execution.Response;

import java.io.IOException;
import java.io.OutputStream;
import java.util.concurrent.Semaphore;

public class DefaultHttpHandler implements HttpHandler {
    final ApiResponse apiResponse;
    final Semaphore semaphore;

    public DefaultHttpHandler(int maxConcurrency, ApiResponse apiResponse) {
        this.apiResponse = apiResponse;
        semaphore = new Semaphore(maxConcurrency);
    }

    private int count = 0;
    @Override
    public void handle(HttpExchange exchange)  {
        if (++count % 100 == 0)
            System.out.println(count + ": Request '" + exchange.getRequestMethod() + exchange.getRequestURI() + "' received!");

        switch (exchange.getRequestMethod()) {
            case "GET":
                respondRequest(exchange, apiResponse.getGetResponse());
                return;
            case "POST":
                respondRequest(exchange, apiResponse.getPostResponse());
                return;
            case "PUT":
                respondRequest(exchange, apiResponse.getPutResponse());
                return;
            case "DELETE":
                respondRequest(exchange, apiResponse.getDeleteResponse());
                return;
        }
        System.err.println("Method '" + exchange.getRequestMethod() + "' is not implemented.");
    }

    void respondRequest(HttpExchange exchange, Response response) {
        try {
//            System.out.printf("%d: Acquiring semaphore\n", Thread.currentThread().threadId());
            semaphore.acquire();
//            System.out.printf("%d: Semaphore acquired. At least %d semaphores available.\n",
//                    Thread.currentThread().threadId(), semaphore.availablePermits());
            OutputStream os = exchange.getResponseBody();
            Thread.sleep(response.getLatencyMs());
            byte[] message = response.getMessage().getBytes();
            exchange.sendResponseHeaders(200, message.length);
            os.write(message);
            os.close();

        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        } finally {
            semaphore.release();
            if (count % 100 == 0)
                System.out.printf("%d: Semaphore released. Total free = %d\n", count, semaphore.availablePermits());
        }
    }

}
