package haroldo.stub.script.out;

import haroldo.stub.script.definition.Definition;

public interface ApiConfigHandle {
    void addResponse(Definition response) throws ApiOutException;
}
