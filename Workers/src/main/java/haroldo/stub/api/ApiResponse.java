package haroldo.stub.api;

import haroldo.stub.execution.Response;

public interface ApiResponse {
    int GET = 0;
    int POST = 1;
    int PUT = 2;
    int DELETE = 3;

    Response getResponse(int method);
    Response getGetResponse();
    Response getPostResponse();
    Response getPutResponse();
    Response getDeleteResponse();
}
