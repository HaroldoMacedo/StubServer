package haroldo.stub.api.execution.controller;

import haroldo.stub.api.Api;
import haroldo.stub.api.ApiImpl;
import haroldo.stub.api.ApiResponse;
import haroldo.stub.api.DefaultApiResponse;
import haroldo.stub.application.Application;
import haroldo.stub.application.DefaultApplication;
import haroldo.stub.worker.Server;
import haroldo.stub.worker.ServerException;
import org.springframework.web.bind.annotation.*;

@RestController
public class LocalServerController {

//    @PutMapping("/server/localhost/port/{port}")
    @GetMapping("/server/localhost/port/{port}")
    public String startLocalHost(@PathVariable(name="port") int port) {
        Server server = new Server(port);
        try {
            server.startServer();
        } catch (ServerException e) {
            return "Port already in use";
        }
        return "Server started";
    }

    @DeleteMapping("/server/localhost/port/{port}")
    public String stopLocalHost(@PathVariable(name="port") int port) {
        Server server = new Server(port);
        try {
            server.stopServer();
            return "Server stopped";
        } catch (ServerException e) {
            return e.getMessage();
        }
    }

//    @PutMapping("/server/localhost/port/{port}/application/{application}")
    @GetMapping("/server/localhost/port/{port}/application/{application}")
    public String startLocalHostApplication(@PathVariable(name="port") int port, @PathVariable(name="application") String application) {
        Api helloApi = new ApiImpl("/hello", 20);
        ApiResponse helloApiResponse = new DefaultApiResponse("Hello World!", 200);
        Application helloApplication = new DefaultApplication(application, helloApi, helloApiResponse);

        Server server = new Server(port);
        server.addOrReplace(helloApplication);
        try {
            server.startApplication(application);
        } catch (ServerException e) {
            return e.getMessage();
        }

        return "Application '" + application + "' started!";
    }

}
