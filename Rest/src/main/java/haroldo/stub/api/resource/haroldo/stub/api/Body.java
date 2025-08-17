package haroldo.stub.api.resource.haroldo.stub.api;

public class Body {
    private String body;

    public Body() {
        this.body = "";
    }

    public Body(String body) {
        this.body = body;
    }

    public byte[] getBytes() {
        return body.getBytes();
    }

    public int length() {
        return body.getBytes().length;
    }

    @Override
    public String toString() {
        return body;
    }
}
