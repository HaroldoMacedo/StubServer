package haroldo.stub.script.in;

import haroldo.stub.api.Response;

import java.util.List;

public interface ApiConfig {

    String getName();
    int getMaxThroughputMs();
    List<Response> getResponses();

}
