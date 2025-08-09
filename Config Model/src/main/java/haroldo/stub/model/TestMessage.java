package haroldo.stub.model;

public class TestMessage {
    private final MessageModel message;
    private TypeResponseTime typeResponseTime;

    public TestMessage(MessageModel message) {
        this.message = message;
    }

    public MessageModel getMessage() {
        return message;
    }

    public TypeResponseTime getTypeResponseTime() {
        return typeResponseTime;
    }

    public void setTypeResponseTime(TypeResponseTime typeResponseTime) {
        this.typeResponseTime = typeResponseTime;
    }
}
