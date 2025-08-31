package haroldo.stub.script;

import haroldo.stub.api.Api;
import haroldo.stub.api.DefaultApi;
import haroldo.stub.runtime.DeployableApplication;
import haroldo.stub.node.Node;
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
    public ApiConfigHandle configApi(String name, String uri, int maxThroughputTPS){
        definitions = new ArrayList<>();
        this.name = name;
        this.uri = uri;
        this.maxThroughputTPS = maxThroughputTPS;

        return new ConfigHandle();
    }

    @Override
    public int commit() throws ApiOutException {
        Api api = new DefaultApi(uri, 100);
        for (Definition definition : definitions) {
            switch (definition.getMethod()) {
                case Api.GET -> api.addGetResponse(definition.getMessage(), definition.getLatencyMs());
                case Api.POST -> api.addPostResponse(definition.getMessage(), definition.getLatencyMs());
                case Api.PUT -> api.addPutResponse(definition.getMessage(), definition.getLatencyMs());
                case Api.DELETE -> api.addDeleteResponse(definition.getMessage(), definition.getLatencyMs());
                default -> throw new ApiOutException("Method " + definition.getMethod() + " not recognized");
            }
        }

        int id = Node.deployApplication(port, new DeployableApplication(name, api, maxThroughputTPS));
        deployedApplicationIdList.add(id);
        definitions = null;

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
