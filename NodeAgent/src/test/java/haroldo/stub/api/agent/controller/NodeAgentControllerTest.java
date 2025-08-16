package haroldo.stub.api.agent.controller;

import org.junit.jupiter.api.Test;

public class NodeAgentControllerTest {
    private int port = 8081;
    private String appName = "Hello!";

    @Test
    void startAndStopLocalHostTest() {
        System.out.println("Starting and stopping local host test");
        NodeAgentController nodeAgentController = new NodeAgentController();
        String response = nodeAgentController.startLocalHost(port);
        assert(response.equals("Server started"));
        response = nodeAgentController.stopLocalHost(port);
        assert(response.equals("Server stopped"));
        System.out.println("Starting and stopping local host tested");
    }

    @Test
    void deployApplicationTest() {
        System.out.println("Start of deploying application test");

        NodeAgentController nodeAgentController = new NodeAgentController();
        String response = nodeAgentController.startLocalHost(port);
        assert(response.equals("Server started"));

        response = nodeAgentController.deployLocalHostApplication(port, appName);
        assert (response.equals("Application '" + appName + "' deployed!"));

        System.out.println("End of deploy application test");
    }

}
