package haroldo.stub.node;

import com.sun.net.httpserver.HttpHandler;
import haroldo.stub.api.Api;

public class DeployableApplication {
    private int id = 0;
    private final String name;
    private final Api api;
    private final HttpHandler httpHandler;

    public DeployableApplication(String name, Api api, int maxThroughPutPerSecond) {
        this.name = name;
        this.api = api;
        this.httpHandler = new DefaultHttpHandler(maxThroughPutPerSecond, api.getApiResponse());
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

    public Api getApi() {
        return api;
    }

    public HttpHandler getApplicationHandle() {
        return httpHandler;
    }
}
