package haroldo.stub.script.sample;

import haroldo.stub.script.Definition;
import haroldo.stub.script.out.ApiConfig;
import haroldo.stub.script.out.ApiOutException;
import haroldo.stub.script.out.ScriptOut;

public class SampleOut implements ScriptOut {

    String name;
    int maxThroughputTPS;

    @Override
    public ApiConfig configApi(String name, int maxThroughputTPS) {
        this.name = name;
        this.maxThroughputTPS = maxThroughputTPS;

        System.out.println("\n-------------------- " + name + " with limit of " + maxThroughputTPS + "TPS --------------------");
        return new Config();
    }

    class Config implements ApiConfig {
        @Override
        public void addResponse(Definition definition) throws ApiOutException {
            System.out.println(name + " - Adding message of size "
                    + definition.getMessage().length() + " bytes to respond after latency of "
                    + definition.getLatencyMs() + "ms");
        }

        @Override
        public void commit() throws ApiOutException {
            System.out.println("Commited!");
        }
    }
}
