package haroldo.stub.api.resource.haroldo.stub.api;

public class HttpCode {
    private int httpCode = 200;
    private String httpCodeName = "OK";

    public HttpCode() {
        setHttpCodeOK_200();
    }

    public HttpCode(int code) {
        setHttpCode(code);
    }

    public int getHttpCode() {
        return httpCode;
    }

    public String getHttpCodeName() {
        return httpCodeName;
    }

    public void setHttpCodeName(String httpCodeName) {
        this.httpCodeName = httpCodeName;
    }

    public void setHttpCode(int httpCode) {
        switch (httpCode) {
            case 200:
                this.httpCode = 200;
                this.httpCodeName = "OK";
                break;
            case 201:
                this.httpCode = 201;
                this.httpCodeName = "Created";
                break;
            case 202:
                this.httpCode = 202;
                this.httpCodeName = "Accepted";
                break;
            case 400:
                this.httpCode = 400;
                this.httpCodeName = "Bad Request";
                break;
            case 404:
                this.httpCode = 404;
                this.httpCodeName = "Not Found";
                break;
            default:
                throw new RuntimeException("Http Code " + httpCode +" not implemented!");
        }
    }

    public void setHttpCodeOK_200() {
        setHttpCode(200);
    }

    public void setHttpCodeCreated_201() {
        setHttpCode(201);
    }

    public void setHttpCodeBadRequest_400() {
        setHttpCode(400);
    }

    public void setHttpCodeNotFound_404() {
        setHttpCode(404);
    }
}
