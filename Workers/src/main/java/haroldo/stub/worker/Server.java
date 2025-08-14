package haroldo.stub.worker;


import com.sun.net.httpserver.HttpServer;
import haroldo.stub.application.Application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Server {
    private final int port;
    private Listener listener;
    private final List<Application> apiList = new ArrayList<>();
    private final static Map<Integer, HttpServer> httpServers = new HashMap<>();

    public Server(int port) {
        this.port = port;
    }

    public void addOrReplace(Application api) {
        apiList.remove(api);
        apiList.add(api);
    }

    public void startServer() throws ServerException {
        try {
            listener = new Listener(port);
            listener.startListener();
            System.out.println("Server started!");
        } catch (IOException e) {
            throw new ServerException("Port already in use");
        }
    }

    public void stopServer() throws ServerException {
        try {
            System.out.println("Stopping server");
            listener.stopListener();
            System.out.println("Server stopped!");
        } catch (ListenerException | IOException e) {
            throw new ServerException(e.getMessage());
        }
    }

    public void startApplication(String appName) throws ServerException {
        Application application = getApi(appName);
        if (application == null)
            throw new ServerException("App name " + appName + " not found.");

        try {
            listener.startApplication(application);
        } catch (ListenerException e) {
            throw new ServerException(e.getMessage());
        }
        System.out.println("Application '" + appName + "' started!");
    }

    public void stopApplication(String appName) throws ServerException {
        Application application = getApi(appName);
        if (application == null)
            throw new ServerException("App name " + appName + " not found.");

        try {
            listener.stopApplication(application.getApi().getUri());
        } catch (ListenerException e) {
            throw new ServerException(e.getMessage());
        }
        System.out.println("Application '" + appName + "' stopped!");
    }

    public Application getApi(String name) {
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
