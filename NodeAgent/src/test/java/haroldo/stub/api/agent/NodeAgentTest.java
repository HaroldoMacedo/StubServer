package haroldo.stub.api.agent;

import haroldo.stub.api.resource.ResourceId;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;

public class NodeAgentTest {

    private static final String nodeAgentPort = "8090";

    static {
        Main.main(new String[]{nodeAgentPort});
    }

    private static final String CONTEXT = "http://localhost:" + nodeAgentPort + "/stub/execution";
    private static int port = 8080;

    @Test
    void startWorkerTest() {
        System.out.println("Starting woker at port " + port);

        ResponseEntity<?> response = callPutHost("/server/port/" + port);
        assert (response.getStatusCode() == HttpStatus.OK);
        assert (!((ResourceId) response.getBody()).getHyperlink().isEmpty());
    }

    @Test
    void startLocalHostTest() {    // TODO Repeated test from above!
        port = 8082;
        System.out.println("Starting woker at port " + port);

        ResponseEntity<?> response = callPutHost("/server/port/" + port);
        assert (response.getStatusCode() == HttpStatus.OK);
        assert (!((ResourceId) response.getBody()).getHyperlink().isEmpty());
    }

    ResponseEntity<?> callGetHost(String uri) {
        RestTemplate r = new RestTemplate();
        return r.getForEntity(CONTEXT + uri, String.class);
    }

    ResponseEntity<?> callPutHost(String uri) {

        RequestEntity<?> req = new RequestEntity<>(HttpMethod.PUT, getContextUri(uri));
        ResponseEntity<?> response = new RestTemplate().exchange(req, ResourceId.class);

        return response;
    }

    private URI getContextUri(String uri) {
        try {
            return new URI(CONTEXT + uri);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    ResponseEntity<?> callPostHost(String uri, HttpRequest.BodyPublisher body) throws IOException, InterruptedException {
        return callHost(HttpRequest.newBuilder().uri(URI.create(CONTEXT + uri)).POST(body).build());
    }

    ResponseEntity<?> callDeleteHost(String uri) throws IOException, InterruptedException {
        return callHost(HttpRequest.newBuilder().uri(URI.create(CONTEXT + uri)).DELETE().build());
    }

    ResponseEntity<?> callHost(HttpRequest request) throws IOException, InterruptedException {
        return null;
    }
}
