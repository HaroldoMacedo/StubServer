package haroldo.stub.script.sample;

import haroldo.stub.script.Definition;

public class SampleDefinition implements Definition {

    private String message;
    private long latencyMs;

    public SampleDefinition(String message, long latencyMs) {
        this.message = message;
        this.latencyMs = latencyMs;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public long getLatencyMs() {
        return latencyMs;
    }
}
