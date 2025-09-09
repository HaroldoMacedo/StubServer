package haroldo.stub.throttle;

import haroldo.stub.application.NonFunctionaRequirements;
import haroldo.stub.metrics.AvgStdDev;

public class Throttle {
    private final long[] lastResponseTimestampMS;
    private int oldest = 0;

    AvgStdDev statTimeInQueueMS = new AvgStdDev();

    private final long latencyMS;

    private Integer queueSize = 0;

    public Throttle(NonFunctionaRequirements nfrs) {
        lastResponseTimestampMS = new long[nfrs.getMaxThroughputTPS()];
        this.latencyMS = nfrs.getAvgLatencyMS();
    }

    public void toMaxThroughput() throws InterruptedException {
        long sleepMS = sleepTimeMS();
        if (sleepMS <= 0)
            return;

        long queueTimeMS = sleepMS - latencyMS;
        statRequestEnterQueue(queueTimeMS);

        Thread.sleep(sleepMS);

        statRequestExitQueue(queueTimeMS);
    }

    private long sleepTimeMS() {
        long thisWakeTimestampMS;
        synchronized (lastResponseTimestampMS) {
            long now = System.currentTimeMillis();
            thisWakeTimestampMS = Math.max(now + latencyMS, lastResponseTimestampMS[oldest]);
            lastResponseTimestampMS[oldest] = Math.max(lastResponseTimestampMS[oldest], now + Math.min(latencyMS, 1000)) + 1000;
            if (++oldest >= lastResponseTimestampMS.length)
                oldest = 0;
        }
        long sleepMS = thisWakeTimestampMS - System.currentTimeMillis();
        return (sleepMS > 0 ? sleepMS : 0);
    }

    private void statRequestEnterQueue(long queueTimeMS) {
        if (queueTimeMS <= 0)
            return;

        synchronized (queueSize) {
            queueSize++;
        }

        statTimeInQueueMS.addValue(queueTimeMS - latencyMS);
    }

    private void statRequestExitQueue(long queueTimeMS) {
        if (queueTimeMS <= 0)
            return;
        synchronized (queueSize) {
            queueSize--;
        }
    }

}
