package haroldo.stub.script;

import haroldo.stub.application.*;
import haroldo.stub.node.Node;
import haroldo.stub.operation.OperationImpl;
import haroldo.stub.script.definition.Definition;
import haroldo.stub.script.out.ApiConfigHandle;
import haroldo.stub.script.out.ApiOutException;
import haroldo.stub.script.out.ScriptOut;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ToStandaAloneNode implements ScriptOut {
    private final int port;
    String name;
    String uri;
    int maxThroughputTPS;
    List<Integer> deployedApplicationIdList = new ArrayList<>();
    List<Definition> definitions = null;

    public ToStandaAloneNode(int port) {
        this.port = port;
        Node.createListener(port);
    }

    @Override
    public ApiConfigHandle configApi(String name, String uri, int maxThroughputTPS) {
        definitions = new ArrayList<>();
        this.name = name;
        this.uri = uri;
        this.maxThroughputTPS = maxThroughputTPS;

        return new ConfigHandle();
    }

    @Override
    public int commit() throws ApiOutException {
        int id;
        try {
            var operation = new OperationImpl("Hello!", "GET", "/hello");
            var nfrs = new NonFunctionaRequirements(100, 10);
            var messageGenerator = new DefaultMessageGenerator("Hello World!");
            id = Node.deployApplication(port, operation, nfrs, messageGenerator);
            deployedApplicationIdList.add(id);
            definitions = null;
        } catch (DeployException d) {
            throw new ApiOutException(d.getMessage());
        }

        return id;
    }

    @Override
    public void rollback() {
        definitions = null;
    }

    @Override
    public void startListener() throws ApiOutException {
        try {
            Node.startListener(port);
        } catch (IOException e) {
            throw new ApiOutException("Couldn't start the listener at port " + port + " - " + e.getMessage());
        }
    }

    @Override
    public void startApplications() throws ApiOutException {
        for (int id : deployedApplicationIdList) {
            Node.startApplication(id);
        }
    }

    @Override
    public void setProperties(Properties properties) {
    }

    class ConfigHandle implements ApiConfigHandle {

        @Override
        public void addResponse(Definition definition) throws ApiOutException {
            definitions.add(definition);
        }
    }

}
