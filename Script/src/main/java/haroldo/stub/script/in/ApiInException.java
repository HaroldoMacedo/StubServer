package haroldo.stub.script.in;

public class ApiInException extends RuntimeException {
    public ApiInException(int apiCount, String message) {
        super("Api " + apiCount + ": " + message);
    }
}
