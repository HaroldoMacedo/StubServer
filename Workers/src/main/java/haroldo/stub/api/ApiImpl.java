package haroldo.stub.api;

public class ApiImpl implements Api {
    private final String uri;
    private final int maxThroughPutPerSecond;

    public ApiImpl(String uri, int maxThroughPutPerSecond) {
        this.uri = uri;
        this.maxThroughPutPerSecond = maxThroughPutPerSecond;
    }

    @Override
    public String getUri() {
        return uri;
    }

    @Override
    public int getMaxThroughPutPerSecond() {
        return maxThroughPutPerSecond;
    }
}
