package haroldo.stub.script.in;

public interface ScriptIn {
    boolean hasNext() throws ApiInException;
    ApiDefinition getNext() throws ApiInException;
}
