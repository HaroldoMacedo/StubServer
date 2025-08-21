package haroldo.stub.script.in;

public class ApiInException extends Exception {
    public ApiInException(int apiCount, String message) {
        super("Api " + apiCount + ": " + message);
    }
}
