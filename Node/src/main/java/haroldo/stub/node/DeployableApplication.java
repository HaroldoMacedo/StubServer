package haroldo.stub.node;

import com.sun.net.httpserver.HttpHandler;
import haroldo.stub.api.Api;

public class DeployableApplication {
    private int id = 0;
    private final String name;
    private final String uri;
    private final HttpHandler httpHandler;

    public DeployableApplication(String name, Api api, int maxThroughPutPerSecond) {
        this.name = name;
        this.uri = api.getUri();
        this.httpHandler = new StubHttpHandler(maxThroughPutPerSecond, api);
    }

    public int getId() {
        return id;
    }

    void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getUri() {
        return uri;
    }

    public HttpHandler getApplicationHandle() {
        return httpHandler;
    }
}