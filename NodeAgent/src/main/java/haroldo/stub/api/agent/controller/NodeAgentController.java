package haroldo.stub.api.agent.controller;

import haroldo.stub.api.Api;
import haroldo.stub.api.DefaultApi;
import haroldo.stub.api.resource.ResourceId;
import haroldo.stub.node.DeployableApplication;
import haroldo.stub.node.Node;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class NodeAgentController {
    private static final String CONTEXT = "/stub/execution";

    @PutMapping(CONTEXT + "/server/port/{port}")
    public ResponseEntity<?> startLocalHost(@PathVariable(name = "port") int port) {
        System.out.println("Request to start localhost at " + port);

        try {
            Node.startListener(port);
        } catch (IOException e) {
            return new ResponseEntity(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        System.out.println("Localhost server started using port " + port);
        return new ResponseEntity<>(new ResourceId(CONTEXT + "/server/port/", port), HttpStatus.OK);
    }

    @DeleteMapping(CONTEXT + "/server/port/{port}")
    public ResponseEntity<?> stopLocalHost(@PathVariable(name = "port") int port) {
        System.out.println("Request to stop localhost at " + port);

        Node.stopListener(port);

        System.out.println("Localhost server stopped at " + port);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @PutMapping(CONTEXT + "/server/port/{port}/application/{application}")
    public ResponseEntity<?> deployLocalHostApplication(@PathVariable(name = "port") int port, @PathVariable(name = "application") String application) {
        System.out.println("Request to deploy application " + application + " in port " + port);

        Api api = new DefaultApi("/hello");
        DeployableApplication deployableApplication = new DeployableApplication(application, api, 10);
        Node.deployApplication(port, deployableApplication);

        ResourceId resourceId = new ResourceId(CONTEXT + "/server/port/" + port + "/application", 1);
        return new ResponseEntity<>(resourceId, HttpStatus.OK);
    }

    @DeleteMapping(CONTEXT + "/application/{applicationId}")
    public ResponseEntity<?> undeployLocalHostApplication(@PathVariable(name = "applicationId") String applicationId) {
        System.out.println("Request to un-deploy application " + applicationId);
        int id = Integer.parseInt(applicationId);

        if (! Node.undeployApplication(id))
            new ResponseEntity<>("No application un-deployed!", HttpStatus.ACCEPTED);;

        return new ResponseEntity<>("Application '" + applicationId + "' un-deployed!", HttpStatus.OK);
    }

    @PutMapping(CONTEXT + "application/{application}")
    public ResponseEntity<?> startApplicationAtLocalHost(@PathVariable(name = "application") String applicationId) {
        System.out.println("Request to start application " + applicationId);

        if(!Node.startApplication(Integer.parseInt(applicationId)))
            return new ResponseEntity<>("Error to start application " + applicationId, HttpStatus.NOT_FOUND);


        return new ResponseEntity<>("Application '" + applicationId + "' started!", HttpStatus.OK);
    }

    @DeleteMapping(CONTEXT + "application/{application}")
    public ResponseEntity<?> stopLocalHostApplication(@PathVariable(name = "application") String applicationId) {
        System.out.println("Request to stop application " + applicationId);

        Node.stopApplication(Integer.parseInt(applicationId));

        return new ResponseEntity<>("Application '" + applicationId + "' stopped!", HttpStatus.OK);
    }

}
