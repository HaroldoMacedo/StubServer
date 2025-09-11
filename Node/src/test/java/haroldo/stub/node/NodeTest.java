package haroldo.stub.node;

import haroldo.stub.TestUtils;
import haroldo.stub.application.DefaultMessageGenerator;
import haroldo.stub.application.DeployException;
import haroldo.stub.application.MessageGenerator;
import haroldo.stub.application.NonFunctionaRequirements;
import haroldo.stub.operation.Operation;
import haroldo.stub.operation.OperationImpl;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class NodeTest {
    private int port = 8082;
    private String uri = "/hello/";
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
    void deployApplicationTest() throws DeployException {
        System.out.println("Start of deploying application test");

        Node.createListener(port);

        Operation operation = new OperationImpl("Hello", 0, uri);
        NonFunctionaRequirements nfrs = new NonFunctionaRequirements(100, 100);
        MessageGenerator messageGenerator = new DefaultMessageGenerator("Hello World!");
        int id = Node.deployApplication(port, operation, nfrs, messageGenerator);

        Node.undeployApplication(id);
        Node.stopListener(port);

        System.out.println("End of deploy application test");
    }

    @Test
    void undeployApplicationTest() throws DeployException {
        System.out.println("Start of undeploying application test");

        Node.createListener(port);

        Operation operation = new OperationImpl("Hello", 0, uri);
        NonFunctionaRequirements nfrs = new NonFunctionaRequirements(100, 100);
        MessageGenerator messageGenerator = new DefaultMessageGenerator("Hello World!");
        int id = Node.deployApplication(port, operation, nfrs, messageGenerator);

        Node.undeployApplication(id);
        Node.stopApplication(id);

        Node.stopListener(port);
        System.out.println("End of undeploy application test");
    }

    @Test
    void startApplicationTest() throws IOException, DeployException {
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
    void startTwoApplicationTest() throws IOException, DeployException {
        System.out.println("Start of starting application test");
        Node.createListener(port);
        int id1 = createAndStartApplication(port, appName, uri);
        appName = "Alo!";
        uri = "/alo/";
        int id2 = createAndStartApplication(port, appName, uri);

        Node.undeployApplication(id1);
        Node.undeployApplication(id2);
        Node.stopListener(port);
        assert (!TestUtils.isEndpointResponding(port, uri));
        assert (!TestUtils.isPortOpen(port));
        System.out.println("End of start application test");
    }

    @Test
    void startTwoApplicationSamePathTest() throws IOException, DeployException {
        System.out.println("Start of starting application test");
        Node.createListener(port);
        int id1 = createAndStartApplication(port, appName, uri);
        appName = "Hello2!";
        uri = "/hello/alo/";
        int id2 = createAndStartApplication(port, appName, uri);

        Node.undeployApplication(id1);
        Node.undeployApplication(id2);
        Node.stopListener(port);
        assert (!TestUtils.isEndpointResponding(port, uri));
        assert (!TestUtils.isPortOpen(port));
        System.out.println("End of start application test");
    }

    @Test
    void startTwoApplicationSameSubPathTest() throws IOException, DeployException {
        System.out.println("Start of starting application test");
        Node.createListener(port);
        uri = "/hello/hello/";
        int id1 = createAndStartApplication(port, appName, uri);
        appName = "Hello2!";
        uri = "/hello/alo/";
        int id2 = createAndStartApplication(port, appName, uri);

        Node.undeployApplication(id1);
        Node.undeployApplication(id2);
        Node.stopListener(port);
        assert (!TestUtils.isEndpointResponding(port, uri));
        assert (!TestUtils.isPortOpen(port));
        System.out.println("End of start application test");
    }

    @Test
    void startApplicationRegExTest() throws IOException, DeployException {
        System.out.println("Start of starting application test");
        uri = "/hello/{appId}/oi/";
        Node.createListener(port);
        int id = createAndStartApplication(port, appName, uri);

        Node.undeployApplication(id);
        Node.stopListener(port);
        assert (!TestUtils.isEndpointResponding(port, uri));
        assert (!TestUtils.isPortOpen(port));
        System.out.println("End of start application test");
    }

    @Test
    void startApplicationTwiceTest() throws IOException, DeployException {
        System.out.println("Start of starting application test");
        Node.createListener(port);
        int id = createAndStartApplication(port, appName, uri);

        assert(Node.startApplication(id));

        Node.undeployApplication(id);
        Node.stopListener(port);
        assert (!TestUtils.isPortOpen(port));
        System.out.println("End of start application twice test");
    }

    @Test
    void stopApplicationTest() throws IOException, DeployException {
        System.out.println("Start of stopping application test");
        Node.createListener(port);
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
    void stopTwiceApplicationTest() throws IOException, DeployException {
        System.out.println("Start of stopping application test");
        Node.createListener(port);
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

    private int createAndStartApplication(int port, String name, String uri) throws IOException, DeployException {
        Operation operation = new OperationImpl(name, "GET", uri);
        NonFunctionaRequirements nfrs = new NonFunctionaRequirements(100, 10);
        MessageGenerator messageGenerator = new DefaultMessageGenerator("{\n\"message\": \"Hello World!\"\n}");
        int id = Node.deployApplication(port, operation, nfrs, messageGenerator);

        Node.startListener(port);
        assert (TestUtils.isPortOpen(port));

        Node.startApplication(id);
        assert (TestUtils.isPortOpen(port));
        assert (TestUtils.isEndpointResponding(port, uri.replaceAll("/\\{.*?}/", "/1234/")));

        return id;
    }

    private String getContext(String uri) {
        int index = uri.indexOf('/', 2);
        if (index < 0)
            index = uri.length() - 1;
        return uri.substring(0, index);
    }
}
