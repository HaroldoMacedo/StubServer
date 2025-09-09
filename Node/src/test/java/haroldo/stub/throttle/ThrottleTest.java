package haroldo.stub.throttle;

import haroldo.stub.application.DeployException;
import haroldo.stub.application.NonFunctionaRequirements;
import haroldo.stub.metrics.AvgStdDev;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class ThrottleTest implements Runnable {

    Throttle throttle;

    @Test
    public void throughput20TpsLatency10MsTest() throws DeployException {
        runTest(1000, 100, 20);
    }

    @Test
    public void throughput20TpsLatency10MsLoad21TPSTest() throws DeployException {
        runTest(1000, 10, 20, 21);
    }

    @Test
    public void throughput1TpsLatency10MsTest() throws DeployException {
        runTest(100, 10, 1);
    }

    @Test
    public void throughput2TpsLatency10MsTest() throws DeployException {
        runTest(100, 10, 2);
    }

    @Test
    public void latency00MsTest() throws DeployException {
        runTest(100, 0, 10);
    }

    @Test
    public void latencyNegativeTest()  {
        try {
            runTest(200, -10, 10);
        } catch (DeployException e) {
            assert (true);
            return;
        }
        assert (false);
    }

    @Test
    public void throughput00TpsTest()  {
        try {
            runTest(200, 10, 0);
        } catch (DeployException e) {
            assert (true);
            return;
        }
        assert (false);
    }

    private void runTest(int maxTransactions, long latencyMS, int maxTrhoughputTPS) throws DeployException {
        runTest(maxTransactions, latencyMS, maxTrhoughputTPS, 0);
    }

    private AvgStdDev statResponseTimeMS;
    private void runTest(int maxTransactions, long latencyMS, int maxTrhoughputTPS, int loadTPS) throws DeployException {
        try {
            statResponseTimeMS = new AvgStdDev();
            long ms = System.currentTimeMillis();
            NonFunctionaRequirements nfr = new NonFunctionaRequirements(latencyMS, maxTrhoughputTPS);
            long waitTime = 0;
            if (loadTPS > 0)
                waitTime = 1000 / loadTPS;

            System.out.println("Starting " + maxTransactions + " transactions " +
                    "with max throughput of " + maxTrhoughputTPS + "TPS and latency of " + latencyMS + "ms");
            Thread[] threads = new Thread[maxTransactions];
            throttle = new Throttle(nfr);

            for (int i = 0; i < maxTransactions; i++) {
                threads[i] = Thread.startVirtualThread(this);
                Thread.sleep(waitTime);
            }

            System.out.println("Waiting for " + maxTransactions + " transactions to finish");
            for (int i = 0; i < maxTransactions; i++) {
                threads[i].join(120 * 1000);
            }

            System.out.println("Done");
            ms = System.currentTimeMillis() - ms;
            System.out.println("Total time was " + ms + "ms");
            System.out.println("Avg Latency = " + statResponseTimeMS.getAvg() + "ms | Std Dev = " + statResponseTimeMS.getStdDev());
            int throughput = Math.round((float) 1000 * maxTransactions / (ms + 1000));
            System.out.println("Throughput = " + throughput + "TPS");

            assert (Math.abs(nfr.getMaxThroughputTPS()) - throughput < 2);
        } catch (InterruptedException e) {
            e.printStackTrace();
            assert (false);
        }
    }

    @Override
    public void run() {
        long ms = System.currentTimeMillis();
        try {
            throttle.toMaxThroughput();
        } catch (InterruptedException e) {
            e.printStackTrace();
            assert (false);
        }

        ms = System.currentTimeMillis() - ms;
        statResponseTimeMS.addValue(ms);
    }

}
