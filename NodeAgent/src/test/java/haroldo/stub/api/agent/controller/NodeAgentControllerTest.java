package haroldo.stub.api.agent.controller;

import haroldo.stub.api.agent.model.Application;
import haroldo.stub.api.agent.model.Listener;
import haroldo.stub.api.agent.model.ResourceId;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class NodeAgentControllerTest {

    private final NodeAgentController nodeAgentController = new NodeAgentController();

    @Test
    void startAndStopListenerTest() {
        int port = 8082;
        System.out.println("Test start - Start and stop of Listener at port " + port);

        startListener(port);
        stopListener(port);

        System.out.println("Test end  - Start and stop of Listener at port " + port);
    }

    @Test
    void deployAndUndeployApplicationTest() {
        int port = 8083;
        String appName = "Alo!";
        System.out.println("Test start - Deploy application test");

        startListener(port);
        int appId = deployApplication(port, appName);
        undeployApplication(appId);
        stopListener(port);

        System.out.println("Test end  - Deploy application test");
    }

    @Test
    void startAndStopApplicationTest() {
        int port = 8083;
        String appName = "Alo!";
        System.out.println("Test start - Deploy application test");

        startListener(port);
        int appId = deployApplication(port, appName);
        startApplication(appId);

        stopApplication(appId);
        stopListener(port);

        System.out.println("Test end  - Deploy application test");
    }

    private void startListener(int port) {
        ResponseEntity<?> response = nodeAgentController.startListener(new Listener(), port);
        assert (response.getStatusCode() == HttpStatus.OK);

        ResourceId resourceId = (ResourceId) response.getBody();
        assert (resourceId != null);
        assert (!resourceId.getHyperlink().isEmpty());
    }

    private void stopListener(int port) {
        ResponseEntity<?> response = nodeAgentController.stopListener(port);
        assert (response.getStatusCode() == HttpStatus.OK);
        assert (response.getBody() == null);
    }

    private int deployApplication(int port, String appName) {
        Application application = new Application(appName, "/test/");
        application.setLatencyMs(1000);
        application.setMaxThroughtputTPS(20);
        application.setResponseMessage("This is the response message");
        ResponseEntity<?> response = nodeAgentController.deployApplication(port, application);
        assert (response.getStatusCode() == HttpStatus.OK);

        ResourceId resourceId = (ResourceId) response.getBody();
        assert (resourceId != null);
        assert (!resourceId.getHyperlink().isEmpty());

        return resourceId.getId();
    }

    private void undeployApplication(int appId) {
        ResponseEntity<?> response = nodeAgentController.undeployApplication(Integer.toString(appId));
        assert (response.getStatusCode() == HttpStatus.OK);
        assert(response.getBody() == null);
    }

    private void startApplication(int appId) {
        ResponseEntity<?> response = nodeAgentController.startApplication(appId);
        assert(response.getStatusCode() == HttpStatus.OK);

        ResourceId resourceId = (ResourceId) response.getBody();
        assert (resourceId != null);
        assert (!resourceId.getHyperlink().isEmpty());
    }

    private void stopApplication(int appId) {
        ResponseEntity<?> response = nodeAgentController.stopApplication(appId);
        assert(response.getStatusCode() == HttpStatus.OK);
        assert(response.getBody() == null);
    }
}
