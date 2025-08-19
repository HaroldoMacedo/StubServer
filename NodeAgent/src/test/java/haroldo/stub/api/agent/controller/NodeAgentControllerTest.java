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
        NodeAgentController nodeAgentController = new NodeAgentController();
        ResponseEntity<?> response = nodeAgentController.startLocalHost(port);
        assert(response.getStatusCode() == HttpStatus.OK);
        assert(((ResourceId)response.getBody()).getHyperlink().length() > 0);

        response = nodeAgentController.stopLocalHost(port);
        assert(response.getStatusCode() == HttpStatus.OK);
        assert(response.getBody() == null);
        System.out.println("Test end  - Start and stop of Listener at port " + port);
    }

    @Test
    void deployApplicationTest() {
        System.out.println("Test start - Deploy application test");

        NodeAgentController nodeAgentController = new NodeAgentController();
        ResponseEntity<?> response = nodeAgentController.startLocalHost(port);
        assert(response.getStatusCode() == HttpStatus.OK);
        assert(((ResourceId)response.getBody()).getHyperlink().length() > 0);

        response = nodeAgentController.deployLocalHostApplication(port, appName);
        assert(response.getStatusCode() == HttpStatus.OK);
        assert(((ResourceId)response.getBody()).getHyperlink().length() > 0);

        System.out.println("Test end  - Deploy application test");
    }

}
