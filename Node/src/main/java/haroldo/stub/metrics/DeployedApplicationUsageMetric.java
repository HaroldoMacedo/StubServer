package haroldo.stub.metrics;

public class DeployedApplicationUsageMetric {
    private int requestCount;
    private long sumLatencyInStub;
    private final MaxMin maxMinLatency = new MaxMin();
    private final AvgStdDev avgLatency = new AvgStdDev();
    private final MaxMin firstLastRequest = new MaxMin();

    void setLatencyMs(long timeMs) {
        synchronized (this) {
            requestCount++;
            sumLatencyInStub += timeMs;
        }
        maxMinLatency.addSample(timeMs);
        avgLatency.addValue(timeMs);
        firstLastRequest.addSample(System.currentTimeMillis());
    }

    public int getRequestCount() {
        return requestCount;
    }

    public synchronized float averageRequestLatencyMs() {
        if (requestCount == 0)
            return 0;
        return (float)sumLatencyInStub / requestCount;
    }

    public long getMaxLatency() {
        return maxMinLatency.getMax();
    }

    public long getMinLatency() {
        return maxMinLatency.getMin();
    }

    public float getAverageLatency() {
        return avgLatency.getAvg();
    }

    public float getStdDevLatency() {
        return avgLatency.getStdDev();
    }

    public MaxMin getFirstLastRequest() {
        return firstLastRequest;
    }

}
