package haroldo.stub.api;

public class DefaultApi implements Api {
    private final String uri;
    private final Response[] responses = new Response[4];

    public DefaultApi() {
        this("/hello", "Hello World!", 100);
    }

    public DefaultApi(String uri, String message, long latencyMS) {
        this.uri = uri;
        setGetResponse(message, latencyMS);
        setPostResponse(message, latencyMS);
        setPutResponse(message, latencyMS);
        setDeleteResponse(message, latencyMS);
    }

    @Override
    public String getUri() {
        return uri;
    }

    @Override
    public Response getGetResponse() {
        return responses[Api.GET];
    }

    @Override
    public void setGetResponse(String message, long latencyMs) {
        responses[Api.GET] = new Response(message, latencyMs);
    }

    @Override
    public Response getPostResponse() {
        return responses[Api.POST];
    }

    @Override
    public void setPostResponse(String message, long latencyMs) {
        responses[Api.POST] = new Response(message, latencyMs);
    }

    @Override
    public Response getPutResponse() {
        return responses[Api.PUT];
    }

    @Override
    public void setPutResponse(String message, long latencyMs) {
        responses[Api.PUT] = new Response(message, latencyMs);
    }

    @Override
    public Response getDeleteResponse() {
        return responses[Api.DELETE];
    }

    @Override
    public void setDeleteResponse(String message, long latencyMs) {
        responses[Api.DELETE] = new Response(message, latencyMs);
    }
}
