package haroldo.stub.api.agent.controller;

import haroldo.stub.api.Api;
import haroldo.stub.api.DefaultApi;
import haroldo.stub.api.agent.haroldo.stub.api.agent.model.Application;
import haroldo.stub.api.agent.haroldo.stub.api.agent.model.Listener;
import haroldo.stub.api.resource.ResourceId;
import haroldo.stub.node.DeployableApplication;
import haroldo.stub.node.Node;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class NodeAgentController {
    private static final String CONTEXT = "/stub/execution";

    @PutMapping(CONTEXT + "/listener/port/{port}")
    public ResponseEntity<?> startListener(@RequestBody Listener listener, @PathVariable(name="port") int port) {
        System.out.println("Request to start localhost at " + port);

        try {
            Node.startListener(port);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        System.out.println("Localhost server started using port " + port);
        return new ResponseEntity<>(new ResourceId(CONTEXT + "/listener/port", port), HttpStatus.OK);
    }

    @DeleteMapping(CONTEXT + "/listener/port/{port}")
    public ResponseEntity<?> stopListener(@PathVariable(name = "port") int port) {
        System.out.println("Request to stop localhost at " + port);

        Node.stopListener(port);

        System.out.println("Localhost server stopped at " + port);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PutMapping(CONTEXT + "/listener/port/{port}/application")
    public ResponseEntity<?> deployApplication(@PathVariable(name = "port") int port, @RequestBody Application application) {
        System.out.println("Request to deploy application " + application.getName() + " in port " + port);

        Api api = new DefaultApi(application.getUri(), application.getResponseMessage(), application.getLatencyMs());
        DeployableApplication deployableApplication = new DeployableApplication(application.getName(), api, application.getMaxThroughtputTPS());
        int id = Node.deployApplication(port, deployableApplication);

        ResourceId resourceId = new ResourceId(CONTEXT + "/listener/application", id);
        return new ResponseEntity<>(resourceId, HttpStatus.OK);
    }

    @DeleteMapping(CONTEXT + "/listener/application/{applicationId}")
    public ResponseEntity<?> undeployApplication(@PathVariable(name = "applicationId") String applicationId) {
        System.out.println("Request to un-deploy application " + applicationId);
        int id = Integer.parseInt(applicationId);

        if (! Node.undeployApplication(id))
            return new ResponseEntity<>("No application un-deployed!", HttpStatus.ACCEPTED);     //  TODO: Fix this return.

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PutMapping(CONTEXT + "/application/{applicationId}")
    public ResponseEntity<?> startApplication(@PathVariable(name = "applicationId") int applicationId) {
        System.out.println("Request to start application " + applicationId);

        if(!Node.startApplication(applicationId))
            return new ResponseEntity<>("Error to start application " + applicationId, HttpStatus.NOT_FOUND);     //  TODO: Fix this return.

        ResourceId resourceId = new ResourceId(CONTEXT + "/application", applicationId);
        return new ResponseEntity<>(resourceId, HttpStatus.OK);
    }

    @DeleteMapping(CONTEXT + "/application/{applicationId}")
    public ResponseEntity<?> stopApplication(@PathVariable(name = "applicationId") int applicationId) {
        System.out.println("Request to stop application " + applicationId);     //  TODO: Fix this return.

        Node.stopApplication(applicationId);

        return new ResponseEntity<>(null, HttpStatus.OK);
    }

}
