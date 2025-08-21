package haroldo.stub.script;

import haroldo.stub.script.in.ApiDefinition;
import haroldo.stub.script.in.ApiInException;
import haroldo.stub.script.in.ScriptIn;
import haroldo.stub.script.out.ApiConfigHandle;
import haroldo.stub.script.out.ApiOutException;
import haroldo.stub.script.out.ScriptOut;
import haroldo.stub.script.summary.SummaryCopy;

public class ScriptEngine {
    private final ScriptIn scriptIn;
    private final ScriptOut scriptOut;
    private final SummaryCopy summaryCopy = new SummaryCopy();

    public ScriptEngine(ScriptIn scriptIn, ScriptOut scriptOut) {
        this.scriptIn = scriptIn;
        this.scriptOut = scriptOut;
    }

    public SummaryCopy copyApisInToOut() {
        try {
            while (scriptIn.hasNext()) {
                copyOneApi();
            }
        } catch (ApiInException in) {
            System.err.println("Copy interrupted by error while reading the API definition.");
        }

        return summaryCopy;
    }

    private void copyOneApi()  {
        try {
            ApiDefinition apiDefinition = scriptIn.getNext();
            validateApi(apiDefinition);

            summaryCopy.incrementCountApiIn();
            summaryCopy.addApiDefinitionSummary(apiDefinition);

            try {
                ApiConfigHandle apiConfigHandle = scriptOut.configApi(apiDefinition.getName(), apiDefinition.getUri(), apiDefinition.getMaxThroughputTPS());
                copyResponses(apiDefinition, apiConfigHandle);
                scriptOut.commit();
            } catch (ApiOutException o) {
                scriptOut.rollback();
                System.out.println("Api " + apiDefinition.getName() + " ignored!: " + o.getMessage());
            }
        } catch (ApiInException i) {
            System.err.println(i.getMessage());
        }
    }

    private void copyResponses(ApiDefinition apiDefinition, ApiConfigHandle apiConfigHandle) throws ApiOutException {
        for (Definition defintion : apiDefinition.getDefinitions()) {
            summaryCopy.incrementCurrentApiDefintionCount();

            if (!validateApiDefinition(apiDefinition.getName(), apiDefinition.getUri(), defintion))
                continue;

            apiConfigHandle.addResponse(defintion);
            summaryCopy.incrementCurrentApiConfiguredCount();
        }

        if (summaryCopy.getCurrentApiConfiguredCount() == 0)
            throw new ApiOutException(apiDefinition.getName() + " - There must be at least one configuration!");
    }


    private void validateApi(ApiDefinition apiDefinition) throws ApiInException {
        if (apiDefinition == null)
            throw new ApiInException(summaryCopy.getCountApiIn(), "No definition has been given");
        if (apiDefinition.getName() == null || apiDefinition.getName().isBlank())
            throw new ApiInException(summaryCopy.getCountApiIn(), "Name must be provided");
        if (apiDefinition.getUri() == null || apiDefinition.getUri().isBlank())
            throw new ApiInException(summaryCopy.getCountApiIn(), "One URI must be provided");
        if (apiDefinition.getMaxThroughputTPS() <= 0)
            throw new ApiInException(summaryCopy.getCountApiIn(), "Maximum trhoughput " + apiDefinition.getMaxThroughputTPS() +
                    " is invalid. It must be > 0!");
    }

    private boolean validateApiDefinition(String name, String uri, Definition defintion) {
        String prefix = name + " (" + uri + ") - Config " + summaryCopy.getCountApiIn() + " ignored! ";
        if (defintion.getMessage().length() < 10) {
            System.err.println(prefix + "Message too small with only " + defintion.getMessage().length() + ". (min 10 chars)");
            return false;
        }
        if (defintion.getLatencyMs() > 60 * 1000) {
            System.err.println(prefix + "Latency too big with " + defintion.getLatencyMs() + "ms. (max 60 seconds)");
            return false;
        }

        return true;
    }
}