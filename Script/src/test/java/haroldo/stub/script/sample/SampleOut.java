package haroldo.stub.script.sample;

import haroldo.stub.script.Definition;
import haroldo.stub.script.in.ApiDefinition;
import haroldo.stub.script.out.ApiConfigHandle;
import haroldo.stub.script.out.ApiOutException;
import haroldo.stub.script.out.ScriptOut;

public class SampleOut implements ScriptOut {

    @Override
    public ApiConfigHandle configApi(String name, String uri, int maxThroughputTPS){

        System.out.println("\n-------------------- " + name + " with limit of "
                + maxThroughputTPS + "TPS --------------------");
        return new ConfigHandle(name, uri);
    }

    @Override
    public void commit() throws ApiOutException {

    }

    @Override
    public void rollback() {

    }

    static class ConfigHandle implements ApiConfigHandle {
        String name;
        String uri;
        ConfigHandle(String name, String uri){
            this.name = name;
            this.uri = uri;
        }

        @Override
        public void addResponse(Definition definition) throws ApiOutException {
            System.out.println(name + " (" + uri + ") - message of size "
                    + definition.getMessage().length() + " bytes to respond after latency of "
                    + definition.getLatencyMs() + "ms");
        }
    }
}
