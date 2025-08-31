package haroldo.stub.script;

import haroldo.stub.script.definition.DefaultDefinitionImpl;
import haroldo.stub.script.in.ApiDefinition;
import haroldo.stub.script.in.ApiInException;
import haroldo.stub.script.in.ScriptIn;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Random;

public class FromCodedApisDefinition implements ScriptIn {

    private final ApiDefinition[] apiDefinitions;
    private int nextDefinition = 0;

    public FromCodedApisDefinition() {
        List<ApiDefinition> apis = new ArrayList<>();

        for (int i = 1; i < 10; i++) {
            var apiDefinition = new ApiDefinition("Api-" + i, "/api" + i + "/", 100 + i);
            apis.add(apiDefinition);
            for (int j = 0; j < 20; j++) {
                addDefinitions(apiDefinition, i + j);
            }
        }

        apiDefinitions = apis.toArray(new ApiDefinition[0]);
    }

    private final Random random = new Random(System.currentTimeMillis());
    private void addDefinitions(ApiDefinition apiDefinition, int number) {
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

    @Override
    public void setProperties(Properties properties) {
    }

}
