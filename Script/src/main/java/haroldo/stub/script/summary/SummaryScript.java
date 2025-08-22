package haroldo.stub.script.summary;

import haroldo.stub.script.in.ApiDefinition;

import java.util.ArrayList;
import java.util.List;

public class SummaryScript {
    //  TODO: Create a summary of the configuration copy stating the amount of configurations done and errors.

    private int countApiIn;
    private int countApiOut;
    private int countApiErrorIn;
    private int countApiErrorOut;
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

    public void incrementCountApiOut() {
        countApiOut++;
    }

    public int getCountApiOut() {
        return countApiOut;
    }

    public int getCountApiErrorIn() {
        return countApiErrorIn;
    }

    public void incrementCountApiErrorIn() {
        countApiErrorIn++;
    }

    public int getCountApiErrorOut() {
        return countApiErrorOut;
    }

    public void incrementCountApiErrorOut() {
        countApiErrorOut++;
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

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n");
        sb.append("-------------------- Summary Script START --------------------\n");
        sb.append("APIs read   : " + countApiIn  + " - Errors: " + countApiErrorIn + "\n");
        sb.append("APIs written: " + countApiOut + " - Errors: " + countApiErrorOut + "\n");
        sb.append("\n");
        sb.append("--- APIs Deployed ---\n");
        for (ApiDefinitionSummary apiDefinitionSummary : apiDefinitionSummaryList) {
            sb.append(apiDefinitionSummary.toString());
        }
        sb.append("-------------------- Summary Script  END  --------------------\n");

        return sb.toString();
    }
}
