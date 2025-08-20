package haroldo.stub.script.sample;

import haroldo.stub.script.Definition;
import haroldo.stub.script.in.ApiDefinition;
import haroldo.stub.script.out.ApiConfig;
import haroldo.stub.script.out.ApiOutException;
import haroldo.stub.script.out.ScriptOut;

public class SampleOut implements ScriptOut {

    ApiDefinition apiDefinition;

    @Override
    public ApiConfig configApi(ApiDefinition apiDefinition) {
        this.apiDefinition = apiDefinition;

        System.out.println("\n-------------------- " + apiDefinition.getName() + " with limit of "
                + apiDefinition.getMaxThroughputTPS() + "TPS --------------------");
        return new Config();
    }

    class Config implements ApiConfig {
        @Override
        public void addResponse(Definition definition) throws ApiOutException {
            System.out.println(apiDefinition.getName() +  " (" + apiDefinition.getUri() + ") - message of size "
                    + definition.getMessage().length() + " bytes to respond after latency of "
                    + definition.getLatencyMs() + "ms");
        }

        @Override
        public void commit() throws ApiOutException {
            System.out.println("Commited!");
        }

        @Override
        public void rollback() throws ApiOutException {
            System.out.println("Rollbacked!");
        }
    }
}
