package haroldo.stub.api.resource.haroldo.stub.api;

public class Header {

    private int bodySize = 0;
    private String contentType = "text/plain";
    private HttpCode httpCode = new HttpCode(200);

    public Header() {
        setHttpCode(new HttpCode(200));
    }

    public Header(HttpCode httpCode) {
        setHttpCode(httpCode);
    }

    public Header(int code) {
        setHttpCode(new HttpCode(code));
    }

    public HttpCode getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(HttpCode httpCode) {
        this.httpCode = httpCode;
    }

    public void setHttpCode(int code) {
        setHttpCode(new HttpCode(code));
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public int getBodySize() {
        return bodySize;
    }

    public void setBodySize(int bodySize) {
        this.bodySize = bodySize;
    }

    @Override
    public String toString() {
        return "HTTP/1.1 " + httpCode.getHttpCode() + " " + httpCode.getHttpCodeName() + " \n" +
                "Content-Type: " + contentType + " \n" +
                "Content-Length: " + bodySize;
    }
}
