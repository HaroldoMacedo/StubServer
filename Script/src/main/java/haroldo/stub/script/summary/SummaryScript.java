package haroldo.stub.script.summary;

import haroldo.stub.script.in.ApiDefinition;

import java.util.ArrayList;
import java.util.List;

public class SummaryScript {
    //  TODO: Create a summary of the configuration copy stating the amount of configurations done and errors.

    private int countApiIn;
    private final List<ApiDefinitionSummary> apiDefinitionSummaryList = new ArrayList<>();
    private ApiDefinitionSummary currentApiDefinitionSummary;

    public void addDeployId(int deployId) {
        currentApiDefinitionSummary.setDeployId(deployId);
    }

    public void incrementCountApiIn() {
        countApiIn++;
    }

    public int getCountApiIn() {
        return countApiIn;
    }

    public List<ApiDefinitionSummary> getApiDefinitionSummaryList() {
        return apiDefinitionSummaryList;
    }

    public void addApiDefinitionSummary(ApiDefinition apiDefinition) {
        currentApiDefinitionSummary = new ApiDefinitionSummary(apiDefinition);
        this.apiDefinitionSummaryList.add(currentApiDefinitionSummary);
    }

    public void incrementCurrentApiDefintionCount() {
        currentApiDefinitionSummary.incrementDefintionCount();
    }

    public void incrementCurrentApiConfiguredCount() {
        currentApiDefinitionSummary.incrementConfiguredCount();
    }

    public int getCurrentApiConfiguredCount() {
        return currentApiDefinitionSummary.getConfiguredCount();
    }
}
