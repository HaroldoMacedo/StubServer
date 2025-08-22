package haroldo.stub.script.out;

public interface ScriptOut {
    ApiConfigHandle configApi(String name, String uri, int maxThroughputTPS);
    int commit() throws ApiOutException;
    void rollback();
    void startListener() throws ApiOutException;
    void startApplications() throws ApiOutException;
}
