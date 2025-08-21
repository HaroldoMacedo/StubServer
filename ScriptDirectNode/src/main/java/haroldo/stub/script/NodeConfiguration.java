package haroldo.stub.script;

import haroldo.stub.api.Api;
import haroldo.stub.api.DefaultApi;
import haroldo.stub.node.DeployableApplication;
import haroldo.stub.node.Node;
import haroldo.stub.script.out.ApiConfigHandle;
import haroldo.stub.script.out.ApiOutException;
import haroldo.stub.script.out.ScriptOut;

import java.util.ArrayList;
import java.util.List;

public class NodeConfiguration implements ScriptOut {
    private final int port;
    String name;
    String uri;
    int maxThroughputTPS;
    List<Definition> definitions = null;

    public NodeConfiguration(int port) {
        this.port = port;
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
        Api api = new DefaultApi(uri);
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
        definitions = null;

        return id;
    }

    @Override
    public void rollback() {
        definitions = null;
    }

    class ConfigHandle implements ApiConfigHandle {

        @Override
        public void addResponse(Definition definition) throws ApiOutException {
            definitions.add(definition);
        }
    }
}
