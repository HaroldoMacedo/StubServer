package haroldo.stub.script.summary;

import haroldo.stub.script.Definition;
import haroldo.stub.script.in.ApiDefinition;

import java.util.List;

public class ApiDefinitionSummary implements ApiDefinition {

    private final ApiDefinition apiDefinition;
    private int deployId;

    private int defintionCount = 0;
    private int configuredCount = 0;

    public ApiDefinitionSummary(ApiDefinition apiDefinition) {
        this.apiDefinition = apiDefinition;
    }

    public int getDeployId() {
        return deployId;
    }

    public void setDeployId(int deployId) {
        this.deployId = deployId;
    }

    @Override
    public String getName() {
        return apiDefinition.getName();
    }

    @Override
    public String getUri() {
        return apiDefinition.getUri();
    }

    @Override
    public int getMaxThroughputTPS() {
        return apiDefinition.getMaxThroughputTPS();
    }

    @Override
    public List<Definition> getDefinitions() {
        return apiDefinition.getDefinitions();
    }

    int getDefintionCount() {
        return defintionCount;
    }

    void incrementDefintionCount() {
        this.defintionCount++;
    }

    int getConfiguredCount() {
        return configuredCount;
    }

    void incrementConfiguredCount() {
        this.configuredCount++;
    }
}
