package haroldo.stub.api;

public class DefaultApi implements Api {
    private final String uri;
    private final ApiResponse apiResponse;

    public DefaultApi(String uri) {
        this.uri = uri;
        apiResponse = new ApiResponse("Hello World!",  100);
    }

    public DefaultApi(String uri, String defaultMessage, long avgResponseTimeMs) {
        this.uri = uri;
        apiResponse = new ApiResponse(defaultMessage,  avgResponseTimeMs);
    }

    @Override
    public String getUri() {
        return uri;
    }

    @Override
    public ApiResponse getApiResponse() {
        return apiResponse;
    }
}
