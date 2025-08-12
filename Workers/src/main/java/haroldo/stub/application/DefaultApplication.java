package haroldo.stub.application;

import com.sun.net.httpserver.HttpHandler;
import haroldo.stub.api.Api;
import haroldo.stub.api.ApiResponse;
import haroldo.stub.worker.DefaultHttpHandler;

public class DefaultApplication implements Application {
    private final String name;
    private final Api api;
    private final HttpHandler httpHandler;
    private final int hashCode;

    public DefaultApplication(String name, Api api, ApiResponse apiResponse) {
        this.name = name;
        this.api = api;
        this.httpHandler = new DefaultHttpHandler(api.getMaxThroughPutPerSecond(), apiResponse);
        hashCode = api.getUri().chars().sum();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Api getApi() {
        return api;
    }

    @Override
    public HttpHandler getHttpHandler() {
         return httpHandler;
    }


    //    @Override
    public boolean equals(Object obj) {
        if (obj instanceof String str)
            return name.equals(str);

        if (!(obj instanceof DefaultApplication app))
            return false;

        return this.getApi().getUri().equals(app.getApi().getUri()) || this.name.equals(app.name);
    }

//    @Override
    public int hashCode() {
        return hashCode;
    }
}
