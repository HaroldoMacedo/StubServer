package haroldo.stub.execution;

public class Response {
    private final String message;
    private final int latencyMs;

    public Response(String message, int latencyMs) {
        this.message = message;
        this.latencyMs = latencyMs;
    }

    public String getMessage() {
        return message;
    }

    public int getLatencyMs() {
        return latencyMs;
    }
}
