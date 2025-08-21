package haroldo.stub.script.out;

public interface ScriptOut {
    ApiConfigHandle configApi(String name, String uri, int maxThroughputTPS);
    void commit() throws ApiOutException;
    void rollback();
}
