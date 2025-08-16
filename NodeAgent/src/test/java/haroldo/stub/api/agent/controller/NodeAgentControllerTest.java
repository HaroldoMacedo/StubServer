package haroldo.stub.api.agent.controller;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class NodeAgentControllerTest {

    @Test
    void startLocalHostTest() throws IOException, InterruptedException {
        NodeAgentController nodeAgentController = new NodeAgentController();
        String response = nodeAgentController.startLocalHost(8080);
        assert(response.equals("Server started"));
    }
}
