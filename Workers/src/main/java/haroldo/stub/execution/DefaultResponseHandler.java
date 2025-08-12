package haroldo.stub.execution;

public class DefaultResponseHandler implements ResponseHandler {

    private final Response response;

    public DefaultResponseHandler(Response response) {
        this.response = response;
    }
    @Override
    public Response getNextResponse() {
        return response;
    }
}
