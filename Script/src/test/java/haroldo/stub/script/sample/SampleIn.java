package haroldo.stub.script.sample;

import haroldo.stub.script.definition.DefaultDefinitionImpl;
import haroldo.stub.script.in.ApiDefinition;
import haroldo.stub.script.in.ApiInException;
import haroldo.stub.script.in.ScriptIn;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SampleIn implements ScriptIn {

    private final ApiDefinition[] apiDefinitions;
    private int nextDefinition = 0;
    private String suffixName;
    private String suffixApi;

    public SampleIn() {
        List<ApiDefinition> apis = new ArrayList<>();

        for (int i = 1; i < 10; i++) {
            var apiDefinition = new ApiDefinition(suffixName + i, suffixApi + i + "/", 100 + i);
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

    @Override
    public void setProperties(Properties properties) throws ApiInException {
        suffixName = properties.getProperty("suffix.name");
        suffixApi =  properties.getProperty("suffix.api");
    }
}
