package haroldo.stub.node;

import com.sun.net.httpserver.HttpServer;
import haroldo.stub.runtime.DeployableApplication;

import java.io.IOException;
import java.net.BindException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

public class Listener {
    private final int port;
    private HttpServer server;
    List<DeployableApplication> deployedApplicationList = new ArrayList<>();

    public Listener(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        try {
            server = HttpServer.create(new InetSocketAddress(port), 0);

//            server.setExecutor(Executors.newVirtualThreadPerTaskExecutor());
            server.setExecutor(new ExecutorVirtualThreads(10, 100));    // TODO: Add config data!
            server.start();
        } catch (BindException e) {
            System.out.println("Listener already started!");
        }
    }

    public void stop() {
        server.stop(1);
    }

    public void deployApplication(DeployableApplication deployableApplication) {
        deployedApplicationList.add(deployableApplication);
    }

    public void undeployApplication(int applicationId) {
        stopApplication(applicationId);
        deployedApplicationList.removeIf(deployableApplication -> deployableApplication.getId() == applicationId);
    }

    public boolean startApplication(int applicationId) {
        DeployableApplication deployableApplication = getApplicationById(applicationId);
        if (deployableApplication == null)
            return false;

        server.createContext(deployableApplication.getUri(), deployableApplication.getApplicationHandle());
        return true;
    }

    public boolean stopApplication(int applicationId) {
        DeployableApplication deployableApplication = getApplicationById(applicationId);
        if (deployableApplication == null)
            return false;

        if (server == null)
            return false;

        try {
            server.removeContext(deployableApplication.getUri());
        } catch (IllegalArgumentException e) {
            // Ignore removal of uri that is not running.
        }
        return true;
    }

    public DeployableApplication getApplicationByName(String applicationName) {
        return deployedApplicationList.stream()
                .filter(deployableApplication -> deployableApplication.getName().equals(applicationName))
                .findFirst()
                .orElse(null);
    }

    public DeployableApplication getApplicationById(int applicationId) {
        return deployedApplicationList.stream()
                .filter(deployableApplication -> deployableApplication.getId() == applicationId)
                .findFirst()
                .orElse(null);
    }
}


