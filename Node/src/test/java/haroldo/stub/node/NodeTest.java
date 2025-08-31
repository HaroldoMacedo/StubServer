package haroldo.stub.node;

import haroldo.stub.TestUtils;
import org.junit.jupiter.api.Test;
import haroldo.stub.api.Api;
import haroldo.stub.api.DefaultApi;

import java.io.IOException;

public class NodeTest {
    private int port = 8082;
    private String uri = "/hello";
    private String appName = "Hello!";

    @Test
    public void startListenerTest() throws IOException {
        System.out.println("Start of starting listener test");
        Node.createListener(port);
        Node.startListener(port);
        assert (TestUtils.isPortOpen(port));

        Node.stopListener(port);
        assert (!TestUtils.isPortOpen(port));
        System.out.println("End of starting listener test");
    }

    @Test
    public void stopListenerTest() throws IOException {
        port = 8082;
        uri = "/alo";
        appName = "Alo Mundo!";
        System.out.println("Start of stopping listener test");
        Node.startListener(port);
        assert (TestUtils.isPortOpen(port));
        Node.stopListener(port);
        assert (!TestUtils.isPortOpen(port));
        System.out.println("End of stopping listener test");
    }

    @Test
    void doubleListenerCreationTest() {
        System.out.println("Start of double listener test");
        Node.createListener(port);
        Node.createListener(port);

        assert (!TestUtils.isPortOpen(port));

        Node.stopListener(port);
        assert (!TestUtils.isPortOpen(port));
        System.out.println("End of double listener test");
    }

    @Test
    void doubleListenerStartTest() {
        System.out.println("Start of double listener test");
        try {
            Node.startListener(port);
            Node.startListener(port);

            assert (TestUtils.isPortOpen(port));

        } catch (IOException e) {
            assert(false);
        } finally {
            Node.stopListener(port);
            assert (!TestUtils.isPortOpen(port));
        }
        System.out.println("End of double listener test");
    }

    @Test
    void deployApplicationTest() {
        System.out.println("Start of deploying application test");

        Api api = new DefaultApi(uri, "Hello World!", 100);
        DeployableApplication deployableApplication = new DeployableApplication("Hello", api, 10);

        Node.createListener(port);
        int id = Node.deployApplication(port, deployableApplication);
        Node.undeployApplication(id);
        Node.stopListener(port);

        System.out.println("End of deploy application test");
    }

    @Test
    void undeployApplicationTest() {
        System.out.println("Start of undeploying application test");

        Api api = new DefaultApi(uri, "Hello World!", 100);
        DeployableApplication deployableApplication = new DeployableApplication("Hello", api, 10);

        Node.createListener(port);
        int id = Node.deployApplication(port, deployableApplication);

        Node.undeployApplication(id);
        Node.stopApplication(id);

        Node.stopListener(port);
        System.out.println("End of undeploy application test");
    }

    @Test
    void startApplicationTest() throws IOException {
        System.out.println("Start of starting application test");
        Node.createListener(port);
        int id = createAndStartApplication(port, appName, uri);


        Node.undeployApplication(id);
        Node.stopListener(port);
        assert (!TestUtils.isEndpointResponding(port, uri));
        assert (!TestUtils.isPortOpen(port));
        System.out.println("End of start application test");
    }

    @Test
    void startApplicationTwiceTest() throws IOException {
        System.out.println("Start of starting application test");
        int id = createAndStartApplication(port, appName, uri);
        try {
            Node.startApplication(id);
            assert (false);
        } catch (IllegalArgumentException e) {
            assert (true);
        }

        Node.undeployApplication(id);
        Node.stopListener(port);
        assert (!TestUtils.isPortOpen(port));
        System.out.println("End of start application twice test");
    }

    @Test
    void stopApplicationTest() throws IOException {
        System.out.println("Start of stopping application test");
        int id = createAndStartApplication(port, appName, uri);

        Node.stopApplication(id);
        TestUtils.sleepSeconds(1);
        assert (!TestUtils.isEndpointResponding(port, uri));

        assert(Node.undeployApplication(id));

        Node.stopListener(port);
        assert (!TestUtils.isPortOpen(port));

        System.out.println("End of stop application test");
    }

    @Test
    void stopTwiceApplicationTest() throws IOException {
        System.out.println("Start of stopping application test");
        int id = createAndStartApplication(port, appName, uri);

        assert(Node.stopApplication(id));
        assert (!TestUtils.isEndpointResponding(port, uri));

        Node.stopApplication(id);
        assert (!TestUtils.isEndpointResponding(port, uri));

        assert(Node.undeployApplication(id));
        assert(!Node.undeployApplication(id));
        assert(!Node.stopApplication(id));

        Node.stopListener(port);
        assert (!TestUtils.isPortOpen(port));

        System.out.println("End of stop application test");
    }

    private int createAndStartApplication(int port, String name, String uri) throws IOException {
        assert (!TestUtils.isPortOpen(port));
        assert (!TestUtils.isEndpointResponding(port, uri));

        Api api = new DefaultApi( uri, "{\n\"message\": \"Hello World!\"\n}", 100);
        DeployableApplication deployableApplication = new DeployableApplication(name, api, 10);

        Node.startListener(port);
        assert (TestUtils.isPortOpen(port));

        int id = Node.deployApplication(port, deployableApplication);
        Node.startApplication(id);
        assert (TestUtils.isPortOpen(port));

        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        assert (TestUtils.isEndpointResponding(port, uri));

        return id;
    }

}
