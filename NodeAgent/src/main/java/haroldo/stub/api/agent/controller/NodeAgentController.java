package haroldo.stub.api.agent.controller;

import haroldo.stub.api.Api;
import haroldo.stub.api.DefaultApi;
import haroldo.stub.api.resource.ResourceId;
import haroldo.stub.api.resource.haroldo.stub.api.Body;
import haroldo.stub.api.resource.haroldo.stub.api.Header;
import haroldo.stub.api.resource.haroldo.stub.api.RestResponse;
import haroldo.stub.node.DeployableApplication;
import haroldo.stub.node.Node;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class NodeAgentController {
    private static final String CONTEXT = "/stub/execution";

    @PutMapping(CONTEXT + "/server/port/{port}")
    public RestResponse startLocalHost(@PathVariable(name = "port") int port) {
        System.out.println("Request to start localhost at " + port);

        ResourceId resourceId = new ResourceId("Server",   CONTEXT + "/server/port/", port);
        try {
            Node.startListener(port);
        } catch (IOException e) {
            return new RestResponse(new Header(202), new Body("{\"message\": \"Port already in use\", \n" + resourceId + "}"));
        }

        System.out.println("Localhost server started using port " + port);
        Body body = new Body("{\n\"message\": \"Server started\", " + resourceId  + "}");
        return new RestResponse(new Header(200), body);
    }

    @DeleteMapping(  CONTEXT + "/server/port/{port}")
    public RestResponse stopLocalHost(@PathVariable(name = "port") int port) {
        System.out.println("Request to stop localhost at " + port);

        Node.stopListener(port);

        System.out.println("Localhost server stopped at " + port);
        return new RestResponse(new Header(), new Body("{\"message\": \"Server stopped\"}"));
    }

    @PutMapping(  CONTEXT + "/server/port/{port}/application/{application}")
    public RestResponse deployLocalHostApplication(@PathVariable(name = "port") int port, @PathVariable(name = "application") String application) {
        System.out.println("Request to deploy application " + application + " in port " + port);

        Api api = new DefaultApi("/hello");
        DeployableApplication deployableApplication = new DeployableApplication(application, api, 10);
        Node.deployApplication(port, deployableApplication);

        ResourceId resourceId = new ResourceId("Application", CONTEXT + "/server/port/" + port + "/application", 1);
        Body body = new Body("{\n\"message\": \"Application deployed\", " + resourceId  + "}");
        return new RestResponse(new Header(200), body);
    }

    @DeleteMapping(  CONTEXT + "/server/port/{port}/application/{application}")
    public String undeployLocalHostApplication(@PathVariable(name = "port") int port, @PathVariable(name = "application") String application) {
        System.out.println("Request to un-deploy application " + application + " in the server of port " + port);
// TODO: Gets an applicaiton ID instead of name.

        Node.undeployApplication(port, application);

        return "Application '" + application + "' un-deployed!";
    }

    @PutMapping(  CONTEXT + "application/{application}")
    public String startApplicationAtLocalHost(@PathVariable(name = "application") String application) {
        System.out.println("Request to start application " + application);
// TODO: Gets an applicaiton ID instead of name.

        if(!Node.startApplication(8081, application))   //  TODO: Relate application with the port
            return "Error to start application " + application;


        return "Application '" + application + "' started!";
    }

    @DeleteMapping(  CONTEXT + "application/{application}")
    public String stopLocalHostApplication(@PathVariable(name = "application") String application) {
        System.out.println("Request to stop application " + application);
// TODO: Gets an applicaiton ID instead of name.

        Node.stopApplication(8081, application);    //  TODO: Relate application with the port

        return "Application '" + application + "' stopped!";
    }

}
