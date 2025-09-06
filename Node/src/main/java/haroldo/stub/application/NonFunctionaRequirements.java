package haroldo.stub.application;

public class NonFunctionaRequirements {
    private long avgLatencyMS;
    private int maxThrhoughputTPS;

    public NonFunctionaRequirements(long avgLatencyMS, int maxThrhoughputTPS) {
        this.avgLatencyMS = avgLatencyMS;
        this.maxThrhoughputTPS = maxThrhoughputTPS;
    }

    public long getAvgLatencyMS() {
        return avgLatencyMS;
    }

    public void setAvgLatencyMS(long avgLatencyMS) {
        this.avgLatencyMS = avgLatencyMS;
    }

    public int getMaxThroughputTPS() {
        return maxThrhoughputTPS;
    }

    public void setMaxThrhoughputTPS(int maxThrhoughputTPS) {
        this.maxThrhoughputTPS = maxThrhoughputTPS;
    }

}
