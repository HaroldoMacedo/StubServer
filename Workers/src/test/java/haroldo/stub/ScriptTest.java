package haroldo.stub;

import haroldo.stub.api.Api;
import haroldo.stub.api.ApiImpl;
import haroldo.stub.api.ApiResponse;
import haroldo.stub.api.DefaultApiResponse;
import haroldo.stub.application.Application;
import haroldo.stub.application.DefaultApplication;
import haroldo.stub.worker.Server;
import haroldo.stub.worker.ServerException;
import org.junit.jupiter.api.Test;

public class ScriptTest {
    @Test
    public void run5MinutesScriptTest() throws ServerException, InterruptedException {
        Server server = new Server(8080);
        server.startServer();

        Api helloApi = new ApiImpl("/hello", 100);
        ApiResponse helloApiResponse = new DefaultApiResponse("Hello World!", 200);
        Application helloApplication = new DefaultApplication("Hello World", helloApi, helloApiResponse);

        server.addOrReplace(helloApplication);
        server.startApplication("Hello World");

        Thread.sleep(5 * 60 * 1000);

        server.stopApplication("Hello World");
        server.stopServer();
    }
}