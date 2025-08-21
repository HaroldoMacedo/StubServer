package haroldo.stub.script.sample;

import haroldo.stub.script.DefaultDefinitionImpl;
import haroldo.stub.script.Definition;
import haroldo.stub.script.in.ApiDefinition;
import haroldo.stub.script.in.ScriptIn;

import java.util.ArrayList;
import java.util.List;

public class SampleIn implements ScriptIn {

    private final ApiDefinition[] apiDefinitions;
    private int nextDefinition = 0;

    public SampleIn() {
        List<ApiDefinition> apis = new ArrayList<>();

        for (int i = 1; i < 10; i++) {
            var apiDefinition = new ApiDefinitionImpl("Api-" + i, "/api" + i + "/", 100 + i);
            apis.add(apiDefinition);
            for (int j = 0; j < 20; j++) {
                if (j == i + 1)
                    apiDefinition.addResponse(new DefaultDefinitionImpl(0, "Mesg " + i * j, 10 + i + j));
                else if (j == i + 3)
                    apiDefinition.addResponse(new DefaultDefinitionImpl(0, "{\"message\": \"Message " + i * j + "\"}", 100000 + i + j));
                else
                    apiDefinition.addResponse(new DefaultDefinitionImpl(0, "{\"message\": \"Message " + i * j + "\"}", 10 + i + j));
            }
        }

        apiDefinitions = apis.toArray(new ApiDefinition[0]);
    }

    @Override
    public boolean hasNext() {
        return nextDefinition < apiDefinitions.length;
    }

    @Override
    public ApiDefinition getNext() {
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
