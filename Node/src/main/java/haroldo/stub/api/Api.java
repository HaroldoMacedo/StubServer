package haroldo.stub.api;

public interface Api {

    int GET = 0;
    int POST = 1;
    int PUT = 2;
    int DELETE = 3;

    String getUri();
    Response getGetResponse();
    void setGetResponse(String message, long latencyMs);
    Response getPostResponse();
    void setPostResponse(String message, long latencyMs);
    Response getPutResponse();
    void setPutResponse(String message, long latencyMs);
    Response getDeleteResponse();
    void setDeleteResponse(String message, long latencyMs);
}
