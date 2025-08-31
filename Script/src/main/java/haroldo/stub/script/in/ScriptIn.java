package haroldo.stub.script.in;

import java.util.Properties;

public interface ScriptIn {
    boolean hasNext() throws ApiInException;
    ApiDefinition getNext() throws ApiInException;
    void setProperties(Properties properties) throws ApiInException;
}
