package haroldo.stub.metrics;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class StubStatistics {
    private final AvgStdDev statMaxThroughputPerSec = new AvgStdDev();
    private final AvgStdDev statResponseTimeMS = new AvgStdDev();
    private int countResponses;
    private long msBase;
    private final String context;

    public StubStatistics(String context) {
        this.context = context;
    }

    public long newRequest() {
        return System.currentTimeMillis();
    }

    public synchronized void endRequest(long requestId) {
        long responseTimeMS = requestId;
        statResponseTimeMS.addValue(System.currentTimeMillis() - responseTimeMS);
        countResponsesPerSecond();
    }

    private int countToPrint = 1;

    private void countResponsesPerSecond() {
        long ms = System.currentTimeMillis();
        if (ms - msBase < 1000) {
            countResponses++;
        } else {
            statMaxThroughputPerSec.addValue(countResponses);
            countResponses = 0;
            msBase = ms - (ms % 1000);

            if (countToPrint++ > 20) {
                countToPrint = 1;
                System.out.println(now() + " - " + context + " - Throughput (TPS) = " + statMaxThroughputPerSec);
                System.out.println(now() + " - " + context + " - Latency  ms      = " + statResponseTimeMS );
            }
        }
    }

    private static final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MMM-yy HH:mm:ss.SSS");
    private String now() {
        return LocalDateTime.now().format(dateFormat);
    }
}
