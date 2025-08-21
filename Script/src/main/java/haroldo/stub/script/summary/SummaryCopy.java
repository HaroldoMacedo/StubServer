package haroldo.stub.script.summary;

import haroldo.stub.script.Definition;
import haroldo.stub.script.in.ApiDefinition;

import java.util.ArrayList;
import java.util.List;

public class SummaryCopy {
    //  TODO: Create a summary of the configuration copy stating the amount of configurations done and errors.

    private int countApiIn;
    private final List<ApiDefinitionSummary> apiDefinitionSummaryList = new ArrayList<>();
    private ApiDefinitionSummary currentApiDefinitionSummary;

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

    public List<Definition> getDefinitions(){
        return currentApiDefinitionSummary.getDefinitions();
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
