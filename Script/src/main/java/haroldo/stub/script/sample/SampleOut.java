package haroldo.stub.script.sample;

import haroldo.stub.script.out.ApiOut;
import haroldo.stub.script.out.ScriptOut;

public class SampleOut implements ScriptOut {

    @Override
    public ApiOut configApi(String name, int maxThroughputMs) {
        return null;
    }
}
