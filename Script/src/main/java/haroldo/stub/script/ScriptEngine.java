package haroldo.stub.script;

import haroldo.stub.api.Response;
import haroldo.stub.script.in.ApiConfig;
import haroldo.stub.script.in.ScriptIn;
import haroldo.stub.script.out.ApiOut;
import haroldo.stub.script.out.ApiOutException;
import haroldo.stub.script.out.ScriptOut;

public class ScriptEngine {
    private final ScriptIn scriptIn;
    private final ScriptOut scriptOut;

    public ScriptEngine(ScriptIn scriptIn, ScriptOut scriptOut) {
        this.scriptIn = scriptIn;
        this.scriptOut = scriptOut;
    }

    void run() {
        for (ApiConfig api : scriptIn.getApis()) {
            String name = api.getName();
            int maxThroughput = api.getMaxThroughputMs();

            try {
                ApiOut apiOut = scriptOut.configApi(name, maxThroughput);
                for (Response response : api.getResponses()) {
                    //  TODO: Validate response information.
                    apiOut.addResponse(response);
                }
                apiOut.commit();

            } catch (ApiOutException o) {
                System.out.println("Api " + api.getName() + " ignored!: " + o.getMessage());
            }

        }
    }
}
