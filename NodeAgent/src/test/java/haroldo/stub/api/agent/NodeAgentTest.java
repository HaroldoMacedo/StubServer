package haroldo.stub.api.agent;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class NodeAgentTest {

    private static final String nodeAgentPort = "8090";
    static {
        Main.main(new String[] {nodeAgentPort});
    }

    private static final String url = "http://localhost:" + nodeAgentPort + "/stub/execution";
    private static final int port = 8080;

    @Test
    void startWorkerTest() throws IOException, InterruptedException {
        System.out.println("Starting woker at port " + port);

        HttpResponse<?> response = callPutHost("/server/port/8081", HttpRequest.BodyPublishers.noBody());
        assert (response.statusCode() == 200);
        assert(response.body().equals("Server started"));
    }

    @Test
    void startLocalHostTest() throws IOException, InterruptedException {    // TODO Repeated test from above!
        System.out.println("Starting woker at port " + port);

        HttpResponse<?> response = callPutHost("/server/port/8082", HttpRequest.BodyPublishers.noBody());
        assert (response.statusCode() == 200);
        assert(response.body().equals("Server started"));
    }

    HttpResponse<?>  callGetHost(String uri) throws IOException, InterruptedException {
        return callHost(HttpRequest.newBuilder().uri(URI.create(url + uri)).build());
    }

    HttpResponse<?>  callPutHost(String uri, HttpRequest.BodyPublisher body) throws IOException, InterruptedException {
        return callHost(HttpRequest.newBuilder().uri(URI.create(url + uri)).PUT(body).build());
    }

    HttpResponse<?>  callPostHost(String uri, HttpRequest.BodyPublisher body) throws IOException, InterruptedException {
        return callHost(HttpRequest.newBuilder().uri(URI.create(url + uri)).POST(body).build());
    }

    HttpResponse<?>  callDeleteHost(String uri) throws IOException, InterruptedException {
        return callHost(HttpRequest.newBuilder().uri(URI.create(url + uri)).DELETE().build());
    }

    HttpResponse<?>  callHost(HttpRequest request) throws IOException, InterruptedException {
        return HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
    }
}
