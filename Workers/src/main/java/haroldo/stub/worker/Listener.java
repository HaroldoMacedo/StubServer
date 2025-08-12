package haroldo.stub.worker;

import com.sun.net.httpserver.HttpServer;
import haroldo.stub.application.Application;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class Listener {
    private HttpServer httpServer;
    private final int port;

    public Listener(int port) {
        this.port = port;
    }

    public void startListener() throws IOException {
        httpServer = HttpServer.create(new InetSocketAddress(port), 0);

        httpServer.setExecutor(Executors.newVirtualThreadPerTaskExecutor());
        httpServer.start();
    }

    public void stopListener() throws ListenerException, IOException {
        if (httpServer == null)
            throw new ListenerException("Listener not running!");
        httpServer.stop(1);
    }

    public void startApplication(Application application) throws ListenerException {
        if (httpServer == null)
            throw new ListenerException("Server has not been started!");
        httpServer.createContext(application.getApi().getUri(), application.getHttpHandler());
    }

        public void stopApplication(String uri) throws ListenerException {
        if (httpServer == null)
            throw new ListenerException("Server has not been started!");
        try {
            httpServer.removeContext(uri);
        } catch (IllegalArgumentException e) {
            // Ignored. Application was not running.
        }
    }

}