package haroldo.stub.script.definition;

public class DefaultDefinitionImpl implements Definition {

    private final int method;
    private final String message;
    private final long latencyMs;

    public DefaultDefinitionImpl(int method, String message, long latencyMs) {
        this.method = method;
        this.message = message;
        this.latencyMs = latencyMs;
    }

    @Override
    public int getMethod() {
        return method;
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
