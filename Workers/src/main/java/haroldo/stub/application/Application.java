package haroldo.stub.application;

import com.sun.net.httpserver.HttpHandler;
import haroldo.stub.api.Api;

public interface Application {
    String getName();
    Api getApi();
    HttpHandler getHttpHandler();
}
