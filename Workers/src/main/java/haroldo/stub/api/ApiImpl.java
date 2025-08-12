package haroldo.stub.api;

public class ApiImpl implements Api {
    private final String uri;
    private final int maxConcurrency;

    public ApiImpl(String uri, int maxConcurrency) {
        this.uri = uri;
        this.maxConcurrency = maxConcurrency;
    }

    @Override
    public String getUri() {
        return uri;
    }

    @Override
    public int getMaxConcurrency() {
        return maxConcurrency;
    }
}
