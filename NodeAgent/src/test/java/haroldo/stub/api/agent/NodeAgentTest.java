package haroldo.stub.api.agent;

import haroldo.stub.api.agent.haroldo.stub.api.agent.model.Application;
import haroldo.stub.api.agent.haroldo.stub.api.agent.model.Listener;
import haroldo.stub.api.agent.haroldo.stub.api.agent.model.ResourceId;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

public class NodeAgentTest {

    private static final String nodeAgentPort = "8090";

    static {
        Main.main(new String[]{nodeAgentPort});
    }

    private static final String HTTPHOST = "http://localhost:" + nodeAgentPort;
    private static final String CONTEXT = HTTPHOST + "/stub/execution";

    @Test
    void startAndStopListenerTest() {
        int port = 8080;
        System.out.println("Test start - Start and stop of Listener at port " + port);

        ResourceId listenerResource = startListener(port);
        callDeleteHyperLinkNoReturn(listenerResource.getHyperlink());    //  Stop listener.

        System.out.println("Test end  - Start and stop of Listener at port " + port);
    }

    @Test
    void deployAndUndeploytApplicationTest() {
        int port = 8082;
        System.out.println("Test start - Deploy and Undeploy application test");

        ResourceId listenerResource = startListener(port);
        ResourceId applicationDeployResource = deployApplication(port, getDefaultApplication());
        callDeleteHyperLinkNoReturn(applicationDeployResource.getHyperlink());  //  Undeploy application.

        callDeleteHyperLinkNoReturn(listenerResource.getHyperlink());    //  Stop listener.

        System.out.println("Test end  - Deploy and Undeploy application test");
    }

    @Test
    void startAndStopApplicationTest() {    // TODO Repeated test from above!
        int port = 8085;
        System.out.println("Test start - Start and stop application test");

        ResourceId listenerResource = startListener(port);
        ResourceId applicationDeployResource = deployApplication(port, getDefaultApplication());
        ResourceId applicationResource = startApplication(applicationDeployResource.getId());
        callDeleteHyperLinkNoReturn(applicationResource.getHyperlink());    //  Stop application.

        callDeleteHyperLinkNoReturn(applicationDeployResource.getHyperlink());  //  Undeploy application.
        callDeleteHyperLinkNoReturn(listenerResource.getHyperlink());    //  Stop listener.
        System.out.println("Test start - Start and stop application test");
    }

    private ResourceId startListener(int port) {
        RequestEntity<?> requestEntity = RequestEntity.put(getContextUri(CONTEXT, "/listener/port/" + port)).body(new Listener());
        ResponseEntity<?> response = new RestTemplate().exchange(requestEntity, ResourceId.class);

        assert (response.getStatusCode() == HttpStatus.OK);
        ResourceId resourceId = (ResourceId) response.getBody();
        assert (!resourceId.getHyperlink().isEmpty());

        return resourceId;
    }

    private void callDeleteHyperLinkNoReturn(String hyperlink) {
        RequestEntity<?> requestEntity = RequestEntity.delete(getContextUri(HTTPHOST, hyperlink)).build();
        ResponseEntity<?> response = new RestTemplate().exchange(requestEntity, ResourceId.class);

        assert (response.getStatusCode() == HttpStatus.OK);
        assert (!response.hasBody());
    }

    private ResourceId deployApplication(int port, Application application) {
        RequestEntity<?> requestEntity = RequestEntity.put(getContextUri(CONTEXT, "/listener/port/" + port + "/application")).body(application);
        ResponseEntity<?> response = new RestTemplate().exchange(requestEntity, ResourceId.class);
        assert (response.getStatusCode() == HttpStatus.OK);
        ResourceId resourceId = (ResourceId) response.getBody();
        assert (!resourceId.getHyperlink().isEmpty());

        return resourceId;
    }

    private ResourceId startApplication(int applicationId) {
        RequestEntity<?> requestEntity = RequestEntity.put(getContextUri(CONTEXT, "/application/" + applicationId)).build();
        ResponseEntity<?> response = new RestTemplate().exchange(requestEntity, ResourceId.class);
        assert (response.getStatusCode() == HttpStatus.OK);
        ResourceId resourceId = (ResourceId) response.getBody();
        assert (!resourceId.getHyperlink().isEmpty());

        return resourceId;
    }

    private Application getDefaultApplication() {
        Application application = new Application("Alo", "/alo/");
        application.setLatencyMs(250);
        application.setMaxThroughtputTPS(10);
        application.setResponseMessage("Alo Mundo!");

        return application;
    }

    private URI getContextUri(String context, String uri) {
        try {
            return new URI(context + uri);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
