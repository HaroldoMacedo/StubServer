package haroldo.stub.api.agent;

import haroldo.stub.api.resource.ResourceId;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpRequest;

public class NodeAgentTest {

    private static final String nodeAgentPort = "8090";
    static {
        Main.main(new String[] {nodeAgentPort});
    }

    private static final String CONTEXT = "http://localhost:" + nodeAgentPort + "/stub/execution";
    private static final int port = 8080;

    @Test
    void startWorkerTest() throws IOException, InterruptedException {
        System.out.println("Starting woker at port " + port);

        ResponseEntity<?> response = callPutHost("/server/port/" + port);
        assert(response.getStatusCode() == HttpStatus.OK);
        assert(((ResourceId)response.getBody()).getHyperlink().length() > 0);
    }

    @Test
    void startLocalHostTest() throws IOException, InterruptedException {    // TODO Repeated test from above!
        System.out.println("Starting woker at port " + port);

        ResponseEntity<?> response = callPutHost("/server/port/" + port);
        assert(response.getStatusCode() == HttpStatus.OK);
        assert(((ResourceId)response.getBody()).getHyperlink().length() > 0);
    }

    ResponseEntity<?> callGetHost(String uri) throws IOException, InterruptedException {
        RestTemplate r = new RestTemplate();
        return r.getForEntity(CONTEXT + uri, null);
    }

    ResponseEntity<?> callPutHost(String uri) throws IOException, InterruptedException {

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
