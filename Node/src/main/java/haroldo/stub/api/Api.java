package haroldo.stub.api;

public interface Api {

    int GET = 0;
    int POST = 1;
    int PUT = 2;
    int DELETE = 3;

    String getUri();
    Response getNextGetResponse();
    void addGetResponse(String message, long latencyMs);
    Response getNextPostResponse();
    void addPostResponse(String message, long latencyMs);
    Response getNextPutResponse();
    void addPutResponse(String message, long latencyMs);
    Response getNextDeleteResponse();
    void addDeleteResponse(String message, long latencyMs);
}
