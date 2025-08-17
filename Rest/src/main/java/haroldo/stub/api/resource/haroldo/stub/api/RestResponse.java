package haroldo.stub.api.resource.haroldo.stub.api;

public class RestResponse {
    private Header header;
    private Body body;

    public RestResponse() {
        setHeader(new Header());
        setBody(new Body());
    }

    public RestResponse(Header header, Body body) {
        setHeader(header);
        setBody(body);
    }

    public Header getHeader() {
        return header;
    }

    public void setHeader(Header header) {
        this.header = header;
    }

    public Body getBody() {
        return body;
    }

    public void setBody(Body body) {
        this.body = body;
        header.setBodySize(body.length());
    }

    public int getHttpCode() {
        return header.getHttpCode().getHttpCode();
    }

    @Override
    public String toString() {
        return header.toString() + "\n\n" + body.toString();
    }
}
