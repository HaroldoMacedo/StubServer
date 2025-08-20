package haroldo.stub.script;

import haroldo.stub.script.in.ApiDefinition;
import haroldo.stub.script.in.ApiInException;
import haroldo.stub.script.in.ScriptIn;
import haroldo.stub.script.out.ApiConfig;
import haroldo.stub.script.out.ApiOutException;
import haroldo.stub.script.out.ScriptOut;

public class ScriptEngine {
    private final ScriptIn scriptIn;
    private final ScriptOut scriptOut;

    public ScriptEngine(ScriptIn scriptIn, ScriptOut scriptOut) {
        this.scriptIn = scriptIn;
        this.scriptOut = scriptOut;
    }

    void copyApisInToOut() {
        int inCount = 0;
        while (scriptIn.hasNext()) {
            try {
                copyOneApi(inCount++);
            } catch (ApiInException i) {
                System.err.println(i.getMessage());
            }
        }
    }

    private void copyOneApi(int apiCount) throws ApiInException {
        ApiDefinition apiDefinition = scriptIn.getNext();
        if (apiDefinition == null)
            throw new ApiInException(apiCount, "No definition has been given");

        if (apiDefinition.getName() == null || apiDefinition.getName().isBlank()) throw new ApiInException(apiCount, "Name must be provided");
        if (apiDefinition.getUri() == null || apiDefinition.getUri().isBlank()) throw new ApiInException(apiCount, "One URI must be provided");
        if (apiDefinition.getMaxThroughputTPS() <= 0) throw new ApiInException(apiCount, "Maximum trhoughput " + apiDefinition.getMaxThroughputTPS() +
                " is invalid. It must be > 0!");

        ApiConfig apiConfig = null;
        try {
            apiConfig = scriptOut.configApi(apiDefinition);
            copyResponses(apiDefinition, apiConfig);
            apiConfig.commit();
        } catch (ApiOutException o) {
            if (apiConfig != null)
                apiConfig.rollback();
            System.out.println("Api " + apiDefinition.getName() + " ignored!: " + o.getMessage());
        }

    }

    private void copyResponses(ApiDefinition apiDefinition, ApiConfig apiConfig) throws ApiOutException {
        int configCount = 0;
        int configured = 0;
        for (Definition defintion : apiDefinition.getDefinitions()) {
            configCount++;
            if (defintion.getMessage().length() < 10) {
                System.err.println(apiDefinition.getName() + " (" + apiDefinition.getUri() + ") - Config " + configCount + " ignored! Message too small with only " +
                        defintion.getMessage().length() + ". (min 10 chars)");
                continue;
            }
            if (defintion.getLatencyMs() > 60 * 1000) {
                System.err.println(apiDefinition.getName() + " (" + apiDefinition.getUri() + ") - Config " + configCount + " ignored! Latency too big with " +
                        defintion.getLatencyMs() + "ms. (max 60 seconds)");
                continue;
            }

            apiConfig.addResponse(defintion);
            configured++;
        }

        if (configured == 0)
            throw new ApiOutException(apiDefinition.getName() + " - There must be at least one configuration!");
    }
}