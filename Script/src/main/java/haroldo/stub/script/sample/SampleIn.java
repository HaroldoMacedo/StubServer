package haroldo.stub.script.sample;

import haroldo.stub.script.in.ApiConfig;
import haroldo.stub.script.in.ScriptIn;

import java.util.List;

public class SampleIn implements ScriptIn {
    @Override
    public List<ApiConfig> getApis() {
        return List.of();
    }
}
