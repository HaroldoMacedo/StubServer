package haroldo.stub.script.in;

import haroldo.stub.script.ScriptException;

public class ApiInException extends ScriptException {
    public ApiInException(String message) {
        super(message);
    }
    public ApiInException(int apiCount, String message) {
        super("Api " + apiCount + ": " + message);
    }
}
