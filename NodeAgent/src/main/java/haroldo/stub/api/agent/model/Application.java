package haroldo.stub.api.agent.model;

public class Application {
    private String name = "Hello";
    private String uri = "/hello";

    private int latencyMs = 100;
    private String responseMessage = "Hello sunny world!";

    private int maxThroughtputTPS = 10;

    public Application() {}

    public Application(String name, String uri) {
        setName(name);
        setUri(uri);
        setMaxThroughtputTPS(maxThroughtputTPS);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public int getLatencyMs() {
        return latencyMs;
    }

    public void setLatencyMs(int latencyMs) {
        this.latencyMs = latencyMs;
    }

    public String getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public int getMaxThroughtputTPS() {
        return maxThroughtputTPS;
    }

    public void setMaxThroughtputTPS(int maxThroughtputTPS) {
        this.maxThroughtputTPS = maxThroughtputTPS;
    }
}
