package haroldo.stub.api;

import java.util.ArrayList;
import java.util.List;

public class DefaultApi implements Api {
    private String uri;
    private final int[] next = new int[4];
    private final List<Response> responses[] = new ArrayList[4];

    public DefaultApi() {
        for (int i = 0; i < responses.length; i++)
            responses[i] = new ArrayList<>();
    }

    public DefaultApi(String uri, String message, long latencyMS) {
        this();
        this.uri = uri;
        addGetResponse(message, latencyMS);
        addPostResponse(message, latencyMS);
        addPutResponse(message, latencyMS);
        addDeleteResponse(message, latencyMS);
    }

    @Override
    public String getUri() {
        return uri;
    }

    @Override
    public Response getNextGetResponse() {
        return nextResponse(Api.GET);
    }

    @Override
    public void addGetResponse(String message, long latencyMs) {
        responses[Api.GET].add(new Response(message, latencyMs));
    }

    @Override
    public Response getNextPostResponse() {
        return nextResponse(Api.POST);
    }

    @Override
    public void addPostResponse(String message, long latencyMs) {
        responses[Api.POST].add(new Response(message, latencyMs));
    }

    @Override
    public Response getNextPutResponse() {
        return nextResponse(Api.PUT);
    }

    @Override
    public void addPutResponse(String message, long latencyMs) {
        responses[Api.PUT].add(new Response(message, latencyMs));
    }

    @Override
    public Response getNextDeleteResponse() {
        return nextResponse(Api.DELETE);
    }

    @Override
    public void addDeleteResponse(String message, long latencyMs) {
        responses[Api.DELETE].add(new Response(message, latencyMs));
    }

    private Response nextResponse(int method){
        Response response = responses[method].get(next[method]);
        if (++next[method] >= responses[method].size())
            next[method] = 0;
        return response;
    }
}
