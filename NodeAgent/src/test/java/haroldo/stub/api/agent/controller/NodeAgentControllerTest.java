package haroldo.stub.api.agent.controller;

import haroldo.stub.api.resource.haroldo.stub.api.RestResponse;
import org.junit.jupiter.api.Test;

public class NodeAgentControllerTest {
    private int port = 8081;
    private String appName = "Hello!";

    @Test
    void startAndStopLocalHostTest() {
        System.out.println("Test start - Start and stop of Listener at port " + port);
        NodeAgentController nodeAgentController = new NodeAgentController();
        RestResponse response = nodeAgentController.startLocalHost(port);
        assert(response.getHttpCode() == 200);
        assert(response.getBody().toString().contains("Server started"));

        response = nodeAgentController.stopLocalHost(port);
        assert(response.getHttpCode() == 200);
        assert(response.getBody().toString().contains("Server stopped"));
        System.out.println("Test end  - Start and stop of Listener at port " + port);
    }

    @Test
    void deployApplicationTest() {
        System.out.println("Test start - Deploy application test");

        NodeAgentController nodeAgentController = new NodeAgentController();
        RestResponse response = nodeAgentController.startLocalHost(port);
        assert(response.getHttpCode() == 200);
        assert(response.getBody().toString().contains("Server started"));

        response = nodeAgentController.deployLocalHostApplication(port, appName);
        assert(response.getHttpCode() == 200);
        assert(response.getBody().toString().contains("Application deployed"));

        System.out.println("Test end  - Deploy application test");
    }

}
