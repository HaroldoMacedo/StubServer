package haroldo.stub.response;

public class Response {
    private String message = "Empty";
    private long latencyMs = 0;

    public Response(){}

    public Response(String message, long latencyMs) {
        this.message = message;
        this.latencyMs = latencyMs;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public long getLatencyMs() {
        return latencyMs;
    }

    public void setLatencyMs(long latencyMs) {
        this.latencyMs = latencyMs;
    }
}
