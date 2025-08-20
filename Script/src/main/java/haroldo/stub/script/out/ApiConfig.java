package haroldo.stub.script.out;

import haroldo.stub.script.Definition;

public interface ApiConfig {
    void addResponse(Definition response) throws ApiOutException;
    void commit() throws ApiOutException;
    void rollback() throws ApiOutException;
}
