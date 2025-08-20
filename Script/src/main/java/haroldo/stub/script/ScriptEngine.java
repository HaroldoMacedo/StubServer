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
        for (ApiDefinition apiDefinition : scriptIn.getApis()) {
            inCount++;
            try {
                String name = apiDefinition.getName();
                int maxThroughput = apiDefinition.getMaxThroughputTPS();

                if (name == null || name.isBlank()) throw new ApiInException(inCount, "Name must be provided");
                if (maxThroughput <= 0) throw new ApiInException(inCount, "Maximum trhoughput " + maxThroughput +
                        " is invalid. It must be > 0!");

                try {
                    ApiConfig apiConfig = scriptOut.configApi(name, maxThroughput);
                    copyResponses(name, apiDefinition, apiConfig);
                    apiConfig.commit();
                } catch (ApiOutException o) {
                    System.out.println("Api " + apiDefinition.getName() + " ignored!: " + o.getMessage());
                }
            } catch (ApiInException i) {
                System.err.println(i.getMessage());
            }
        }
    }

    private void copyResponses(String apiName, ApiDefinition api, ApiConfig apiConfig) throws ApiOutException {
        int configCount = 0;
        for (Definition defintion : api.getDefinitions()) {
            configCount++;
            if (defintion.getMessage().length() < 10) {
                System.err.println(apiName + " - Config " + configCount + " ignored! Message too small with only " +
                        defintion.getMessage().length() + ". (min 10 chars)");
                continue;
            }
            if (defintion.getLatencyMs() > 60 * 1000) {
                System.err.println(apiName + " - Config " + configCount + " ignored! Latency too big with " +
                        defintion.getLatencyMs() + "ms. (max 60 seconds)");
                continue;
            }

            apiConfig.addResponse(defintion);
        }


    }
}