package haroldo.stub.node;

import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.BindException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;

public class Listener {
    private final int port;
    private HttpServer server;
    List<DeployableApplication> deployableApplicationList = new ArrayList<>();

    public Listener(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        try {
            server = HttpServer.create(new InetSocketAddress(port), 0);

            server.setExecutor(Executors.newVirtualThreadPerTaskExecutor());
            server.start();
        } catch (BindException e) {
            System.out.println("Listener already started!");
        }
    }

    public void stop() {
        server.stop(1);
    }

    public void deployApplication(DeployableApplication deployableApplication) {
        deployableApplicationList.add(deployableApplication);
    }

    public void undeployApplication(String applicationName) {
        stopApplication(applicationName);
        deployableApplicationList.removeIf(deployableApplication -> deployableApplication.getName().equals(applicationName));
    }

    public boolean startApplication(String applicationName) {
        DeployableApplication deployableApplication = getApplicationByName(applicationName);
        if (deployableApplication == null)
            return false;

        server.createContext(deployableApplication.getApi().getUri(), deployableApplication.getApplicationHandle());
        return true;
    }

    public void stopApplication(String applicationName) {
        DeployableApplication deployableApplication = getApplicationByName(applicationName);
        if (deployableApplication == null)
            return;

        try {
            server.removeContext(deployableApplication.getApi().getUri());
        } catch (IllegalArgumentException e) {
            // Ignore removal of uri that is not running.
        }
    }

    private DeployableApplication getApplicationByName(String applicationName) {
        return deployableApplicationList.stream()
                .filter(deployableApplication -> deployableApplication.getName().equals(applicationName))
                .findFirst()
                .orElse(null);
    }
}


