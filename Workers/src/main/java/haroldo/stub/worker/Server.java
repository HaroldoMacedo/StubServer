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

public class Server {
    private final static Map<Integer, HttpServer> httpServers = new HashMap<>();

    private final int port;
    private final List<Application> apiList = new ArrayList<>();

    public Server(int port) {
        this.port = port;
    }

    public void addOrReplace(Application api) {
        apiList.remove(api);
        apiList.add(api);
    }

    public void startServer() throws ServerException {
        System.out.println("Starting server at port " + port);

        HttpServer httpServer = httpServers.get(port);
        if (httpServer != null)
            return;

        try {
            httpServer = HttpServer.create(new InetSocketAddress(port), 0);
            httpServers.put(port, httpServer);

            httpServer.setExecutor(Executors.newVirtualThreadPerTaskExecutor());
            httpServer.start();

            System.out.println("Server started!");
        } catch (IOException e) {
            throw new ServerException("Port already in use");
        }
    }

    public void stopServer() throws ServerException {
        System.out.println("Stopping server at port " + port);

        HttpServer httpServer = httpServers.get(port);
        if (httpServer == null)
            throw new ServerException("Listener not running!");

        httpServer.stop(1);
        httpServers.remove(port);

        System.out.println("Server stopped!");
    }

    public void startApplication(String appName) throws ServerException {
        System.out.println("Starting application '" + appName + "'");

        HttpServer httpServer = httpServers.get(port);
        if (httpServer == null)
            throw new ServerException("Server has not been started!");

        Application application = getApi(appName);
        if (application == null)
            throw new ServerException("App name " + appName + " not found.");

        httpServer.createContext(application.getApi().getUri(), application.getHttpHandler());

        System.out.println("Application '" + appName + "' started!");
    }

    public void stopApplication(String appName) throws ServerException {
        System.out.println("Stopping application " + appName);

        HttpServer httpServer = httpServers.get(port);
        if (httpServer == null)
            throw new ServerException("Server has not been started!");


        Application application = getApi(appName);
        if (application == null)
            throw new ServerException("App name " + appName + " not found.");

        try {
            httpServer.removeContext(application.getApi().getUri());
        } catch (IllegalArgumentException e) {
            // Ignored. Application was not running.
        }

        System.out.println("Application '" + appName + "' stopped!");
    }

    private Application getApi(String name) {
        return apiList.stream()
                .filter(api -> api.getName().equals(name))
                .findFirst()
                .orElse(null);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Integer)
            return port == ((Integer) obj);
        if (!(obj instanceof Server))
            return false;

        return port == ((Server) obj).port;
    }

    @Override
    public int hashCode() {
        return port;
    }
}
