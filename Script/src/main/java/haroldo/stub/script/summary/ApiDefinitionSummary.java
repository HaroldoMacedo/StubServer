package haroldo.stub.script.summary;

import haroldo.stub.script.Definition;
import haroldo.stub.script.in.ApiDefinition;

import java.util.List;

public class ApiDefinitionSummary implements ApiDefinition {

    private final ApiDefinition apiDefinition;

    private int defintionCount = 0;
    private int configuredCount = 0;

    public ApiDefinitionSummary(ApiDefinition apiDefinition) {
        this.apiDefinition = apiDefinition;
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

    public String getFirstMessage() {
        return apiDefinition.getDefinitions().getFirst().getMessage();
    }

    public long getFirstLatencyMs() {
        return apiDefinition.getDefinitions().getFirst().getLatencyMs();
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
