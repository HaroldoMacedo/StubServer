package haroldo.stub.node;

import com.sun.net.httpserver.HttpServer;
import haroldo.stub.application.*;
import haroldo.stub.operation.AppsDeployed;

import java.io.IOException;
import java.net.BindException;
import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

public class Listener {
    private final int port;
    private final AppsDeployed appsDeployed;
    private HttpServer server;
    private final StubHttpHandler stubHttpHandler;

    public Listener(int port) {
        this.port = port;
        appsDeployed = new AppsDeployed();
        stubHttpHandler = new StubHttpHandler(appsDeployed);
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
        if (server == null)
            return;
        server.stop(1);
    }

    public void deployApplication(DeployedApplication deployedApplcation) throws DeployException {
        appsDeployed.deployApp(deployedApplcation);
    }

    public void undeployApplication(int applicationId) {
        stopApplication(applicationId);
        appsDeployed.undeployApp(applicationId);
    }

    public boolean startApplication(int applicationId) {
        DeployedApplication deployedApplication = getApplicationById(applicationId);
        if (deployedApplication == null)
            return false;

        server.createContext(deployedApplication.getUri(), stubHttpHandler);
        return true;
    }

    public boolean stopApplication(int applicationId) {
        DeployedApplication deployedApplication = getApplicationById(applicationId);
        if (deployedApplication == null)
            return false;

        if (server == null)
            return false;

        try {
            server.removeContext(deployedApplication.getUri());
        } catch (IllegalArgumentException e) {
            // Ignore removal of uri that is not running.
        }
        return true;
    }

    public DeployedApplication getApplicationByName(String applicationName) {
        return appsDeployed.getApplicationByName(applicationName);
    }

    public DeployedApplication getApplicationById(int applicationId) {
        return appsDeployed.getApplicationById(applicationId);
    }
}