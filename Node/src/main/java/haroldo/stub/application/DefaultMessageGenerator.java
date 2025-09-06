package haroldo.stub.application;

public class DefaultMessageGenerator implements MessageGenerator{

    private final String message;

    public DefaultMessageGenerator(String message) {
        this.message = message;
    }
    @Override
    public String getNextMessage() {
        return message;
    }
}
