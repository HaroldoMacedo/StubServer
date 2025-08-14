package haroldo.stub.worker;

import com.sun.net.httpserver.HttpServer;
import haroldo.stub.application.Application;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

public class Listener {
    private final static Map<Integer, HttpServer> httpServers = new HashMap<>();
    private final int port;
    private HttpServer httpServer;

    public Listener(int port) {
        this.port = port;

    }

    public void startListener() throws IOException {
        if (httpServers.get(port) != null)
            return;

        httpServer = HttpServer.create(new InetSocketAddress(port), 0);
        httpServers.put(port, httpServer);

        httpServer.setExecutor(Executors.newVirtualThreadPerTaskExecutor());
        httpServer.start();
    }

    public void stopListener() throws ListenerException, IOException {
        if (httpServer == null)
            throw new ListenerException("Listener not running!");
        httpServer.stop(1);
        httpServers.remove(port);
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