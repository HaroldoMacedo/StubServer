package haroldo.stub.node;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Class that mimics or runs in a node on a JVM server.
 */
public class Node {
    private final static Map<Integer, Listener> listenerList = new HashMap<>();

    private Node() {
    }

    public static Listener createListener(int port) {
        Listener listener = listenerList.get(port);
        if (listener != null)
            return listener;
        System.out.println("Creating listener on port " + port);
        listener = new Listener(port);
        listenerList.put(port, listener);
        System.out.println("Listener created on port " + port);

        return listener;
    }

    public static void startListener(int port) throws IOException {
        System.out.println("Starting listener on port " + port);
        Listener listener = createListener(port);
        listener.start();
        System.out.println("Listener started on port " + port);
    }

    public static void stopListener(int port) {
        System.out.println("Stopping listener on port " + port);
        Listener listener = listenerList.get(port);
        if (listener == null)
            return;
        listener.stop();
        System.out.println("Listener stopped on port " + port);
    }

    public static int deployApplication(int port, DeployableApplication deployableApplication) {
        System.out.println("Deploying application " + deployableApplication.getName() + " on port " + port);

        if (findApplication(deployableApplication.getName()) != null)   //  Name must be unique
            return 0;

        Listener listener = listenerList.get(port);
        if (listener == null)
            return 0;

        int id = getNextApplicationId();
        deployableApplication.setId(id);
        listener.deployApplication(deployableApplication);
        System.out.println("Application " + deployableApplication.getName() + " deployed on port " + port);

        return id;
    }

    public static boolean undeployApplication(int applicationId) {
        System.out.println("Undeploying application id " + applicationId);

        Listener listener = findListenerForApplicationId(applicationId);
        if (listener == null)
            return false;

        listener.undeployApplication(applicationId);
        System.out.println("Application " + applicationId + " undeployed");
        return true;
    }

    public static boolean startApplication(int applicationId) {
        System.out.println("Starting application " + applicationId);

        Listener listener = findListenerForApplicationId(applicationId);
        if (listener == null)
            return false;
        if (!listener.startApplication(applicationId))
            return false;

        System.out.println("Application " + applicationId + " started");
        return true;
    }

    public static boolean stopApplication(int applicationId) {
        System.out.println("Stopping application " + applicationId);

        Listener listener = findListenerForApplicationId(applicationId);
        if (listener == null)
            return false;

        if (! listener.stopApplication(applicationId))
            return false;
        System.out.println("Application " + applicationId + " stopped");
        return true;
    }

    private static DeployableApplication findApplication(String applicationName) {
        for (Listener listener : listenerList.values()) {
            DeployableApplication deployableApplication = listener.getApplicationByName(applicationName);
            if (deployableApplication != null)
                return deployableApplication;
        }
        return null;
    }

    private static Listener findListenerForApplicationId(int applcationId) {
        for (Listener listener : listenerList.values()) {
            DeployableApplication deployableApplication = listener.getApplicationById(applcationId);
            if (deployableApplication != null)
                return listener;
        }
        return null;
    }

    private static int nextApplcationId = 1;
    private static synchronized int getNextApplicationId() {
        return nextApplcationId++;
    }
}

