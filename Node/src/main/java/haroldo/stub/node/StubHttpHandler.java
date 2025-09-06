package haroldo.stub.node;


import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import haroldo.stub.operation.AppsDeployed;
import haroldo.stub.application.DeployException;
import haroldo.stub.application.DeployedApplication;
import haroldo.stub.metrics.StubStatistics;
import haroldo.stub.throttle.Throttle;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

class StubHttpHandler implements HttpHandler {
    private final AppsDeployed appsDeployed;

    private int count = 0;

    public StubHttpHandler(AppsDeployed appsDeployed) {
        this.appsDeployed = appsDeployed;
        System.out.println("\tStarting StubHttpHandler - normally");
    }

    @Override
    public void handle(HttpExchange exchange) {
        try {
            boolean print = (++count % 100) == 0;
            if (print)
                System.out.println(now() + " - " + count + ": Request '" + exchange.getRequestMethod() + " " + exchange.getRequestURI() + "' received!");

            DeployedApplication app = appsDeployed.getApp(exchange.getRequestMethod(), exchange.getRequestURI().getPath());
            long requestId = app.requestStart();

            app.throttleMaxThroughput();

            responseMessage(exchange, 200, app.getNextMessage());

            if (print)
                System.out.println(now() + " - " + count + ": Responded to '" + exchange.getRequestMethod() + " " + exchange.getRequestURI() + "'");

            app.requestEnd(requestId);
        } catch (DeployException e) {
            String message = "Method '" + exchange.getRequestMethod() + " " + exchange.getRequestURI().getPath() + "' is not deployed: " + e.getMessage();
            System.err.println(message);
            responseMessageNoExceptionThrown(exchange, 404, message);
        } catch (IOException e) {
            String message = "Error: Method '" + exchange.getRequestMethod() + " " + exchange.getRequestURI().getPath() + "': " + e.getMessage();
            System.err.println(message);
            responseMessageNoExceptionThrown(exchange, 500, message);
        } catch (InterruptedException e) {
            String message = "Interrupted: Method '" + exchange.getRequestMethod() + " " + exchange.getRequestURI().getPath() + "': " + e.getMessage();
            System.err.println(message);
            responseMessageNoExceptionThrown(exchange, 500, message);
        }
    }

    private void responseMessage(HttpExchange exchange, int httpCode, String message) throws IOException {
        byte[] msg = message.getBytes();
        OutputStream os = exchange.getResponseBody();
        exchange.sendResponseHeaders(httpCode, msg.length);
        os.write(msg);
        os.close();
    }

    private void responseMessageNoExceptionThrown(HttpExchange exchange, int httpCode, String message) {
        try {
            responseMessage(exchange, httpCode, message);
        } catch (IOException e) {
            // Ignore error!
        }
    }

    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MMM-yy HH:mm:ss.SSS");

    private String now() {
        return LocalDateTime.now().format(dateFormat);
    }
}
