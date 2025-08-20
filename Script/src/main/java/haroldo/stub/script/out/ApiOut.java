package haroldo.stub.script.out;

import haroldo.stub.api.Response;

public interface ApiOut {
    void addResponse(Response response) throws ApiOutException;
    void commit() throws ApiOutException;
}
