package haroldo.stub.api.agent.controller;

import haroldo.stub.api.resource.ResourceId;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class NodeAgentControllerTest {
    private int port = 8081;
    private String appName = "Hello!";

    @Test
    void startAndStopLocalHostTest() {
        System.out.println("Test start - Start and stop of Listener at port " + port);

        startLocalHost();
        stopLocalHost();

        System.out.println("Test end  - Start and stop of Listener at port " + port);
    }

    @Test
    void deployApplicationTest() {
        port = 8083;
        appName = "Alo!";
        System.out.println("Test start - Deploy application test");

        startLocalHost();

        NodeAgentController nodeAgentController = new NodeAgentController();
        ResponseEntity<?> response  = nodeAgentController.deployLocalHostApplication(port, appName);
        assert(response.getStatusCode() == HttpStatus.OK);
        assert(!((ResourceId) response.getBody()).getHyperlink().isEmpty());

        stopLocalHost();

        System.out.println("Test end  - Deploy application test");
    }

    private void startLocalHost() {
        NodeAgentController nodeAgentController = new NodeAgentController();
        ResponseEntity<?> response = nodeAgentController.startLocalHost(port);
        assert(response.getStatusCode() == HttpStatus.OK);
        assert(!((ResourceId) response.getBody()).getHyperlink().isEmpty());
    }

    private void stopLocalHost() {
        NodeAgentController nodeAgentController = new NodeAgentController();
        ResponseEntity<?> response = nodeAgentController.stopLocalHost(port);
        assert(response.getStatusCode() == HttpStatus.OK);
        assert(response.getBody() == null);
    }
}
