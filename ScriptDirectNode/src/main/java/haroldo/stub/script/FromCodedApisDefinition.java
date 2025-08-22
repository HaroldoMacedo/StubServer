package haroldo.stub.script;

import haroldo.stub.script.in.ApiDefinition;
import haroldo.stub.script.in.ApiInException;
import haroldo.stub.script.in.ScriptIn;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FromCodedApisDefinition implements ScriptIn {

    private final ApiDefinition[] apiDefinitions;
    private int nextDefinition = 0;

    public FromCodedApisDefinition() {
        List<ApiDefinition> apis = new ArrayList<>();

        for (int i = 1; i < 10; i++) {
            var apiDefinition = new ApiDefinitionImpl("Api-" + i, "/api" + i + "/", 100 + i);
            apis.add(apiDefinition);
            for (int j = 0; j < 20; j++) {
                addDefinitions(apiDefinition, i + j);
            }
        }

        apiDefinitions = apis.toArray(new ApiDefinition[0]);
    }

    private final Random random = new Random(System.currentTimeMillis());
    private void addDefinitions(ApiDefinitionImpl apiDefinition, int number) {
        for (int method = 0; method < 4; method++) {
            int rnd = random.nextInt(100);
            if (rnd > 90)
                apiDefinition.addResponse(new DefaultDefinitionImpl(method, "Mesg " + number, 10 + rnd));
            else if (rnd < 10)
                apiDefinition.addResponse(new DefaultDefinitionImpl(method, "{\"message\": \"Message " + number + "\"}", 100000 + number));
            else
                apiDefinition.addResponse(new DefaultDefinitionImpl(method, "{\"message\": \"Message " + number + "\"}", 10 + rnd));
        }
    }

    @Override
    public boolean hasNext() throws ApiInException {
        return nextDefinition < apiDefinitions.length;
    }

    @Override
    public ApiDefinition getNext() throws ApiInException {
        return apiDefinitions[nextDefinition++];
    }

    static class ApiDefinitionImpl implements ApiDefinition {
        String name;
        String uri;
        int maxThroughput;
        List<Definition> definitions = new ArrayList<>();

        public ApiDefinitionImpl(String name, String uri, int maxThroughput) {
            this.name = name;
            this.uri = uri;
            this.maxThroughput = maxThroughput;
        }

        public void addResponse(Definition definition) {
            definitions.add(definition);
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public String getUri() {
            return uri;
        }

        @Override
        public int getMaxThroughputTPS() {
            return maxThroughput;
        }

        @Override
        public List<Definition> getDefinitions() {
            return definitions;
        }
    }
}
