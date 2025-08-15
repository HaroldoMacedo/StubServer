package haroldo.stub0.api.execution.controller;

import haroldo.stub.api.Api;
import haroldo.stub.api.DefaultApi;
import haroldo.stub.node.DeployableApplication;
import haroldo.stub.node.Node;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class LocalServerController {

    @PutMapping("/stub/execution/server/port/{port}")
    public String startLocalHost(@PathVariable(name = "port") int port) {
        System.out.println("Request to start localhost at " + port);

        try {
            Node.startListener(port);
        } catch (IOException e) {
            return "Port already in use";
        }

        System.out.println("Localhost server started using port " + port);
        return "Server started";
    }

    @DeleteMapping("/stub/execution/server/port/{port}")
    public String stopLocalHost(@PathVariable(name = "port") int port) {
        System.out.println("Request to stop localhost at " + port);

        Node.stopListener(port);

        System.out.println("Localhost server stopped at " + port);
        return "Server stopped";
    }

    @PutMapping("/stub/execution/server/port/{port}/application/{application}")
    public String deployLocalHostApplication(@PathVariable(name = "port") int port, @PathVariable(name = "application") String application) {
        System.out.println("Request to deploy application " + application + " in port " + port);

        Api api = new DefaultApi("/hello");
        DeployableApplication deployableApplication = new DeployableApplication(application, api, 10);
        Node.deployApplication(port, deployableApplication);

        return "Application '" + application + "' deployed!";
    }

    @DeleteMapping("/stub/execution/server/port/{port}/application/{application}")
    public String undeployLocalHostApplication(@PathVariable(name = "port") int port, @PathVariable(name = "application") String application) {
        System.out.println("Request to un-deploy application " + application + " in the server of port " + port);

        Node.undeployApplication(port, application);

        return "Application '" + application + "' un-deployed!";
    }

    @PutMapping("/stub/execution/application/{application}")
    public String startApplicationAtLocalHost(@PathVariable(name = "application") String application) {
        System.out.println("Request to start application " + application);

        if(!Node.startApplication(8081, application))
            return "Error to start application " + application;


        return "Application '" + application + "' started!";
    }

    @DeleteMapping("/stub/execution/application/{application}")
    public String stopLocalHostApplication(@PathVariable(name = "application") String application) {
        System.out.println("Request to stop application " + application);

        Node.stopApplication(8081, application);

        return "Application '" + application + "' stopped!";
    }

}
