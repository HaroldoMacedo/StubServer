package haroldo.stub.script.summary;

import haroldo.stub.script.Definition;
import haroldo.stub.script.in.ApiDefinition;

import java.util.List;

public class ApiDefinitionSummary {

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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("ID = " + deployId + " - " + apiDefinition.getName());
        sb.append("(\"" + apiDefinition.getUri() + "\" )");
        sb.append(" with max throughput of " + apiDefinition.getMaxThroughputTPS() + " TPS");
        sb.append(" | " + getDefintionCount() + " read configs, " + getConfiguredCount() + " applied configs");
        sb.append("\n");

        return sb.toString();
    }
}
