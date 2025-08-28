package haroldo.stub.node;

import java.util.concurrent.Executor;

public class ExecutorVirtualThreads implements Executor {
    Thread.Builder builder = Thread.ofVirtual();

    private final long[] nextWakeMS;
    private int oldest = 0;
    private final long latencyMS;

    public ExecutorVirtualThreads(int maxThroughputPerSecond, long latencyMS) {
        nextWakeMS = new long[maxThroughputPerSecond];
        this.latencyMS = latencyMS;
    }


    @Override
    public void execute(Runnable command) {
        long sleepMs = sleepTimeMS();
        builder.start(new ThrottleResponse(sleepMs, command));
    }

    private long sleepTimeMS() {
        long wakeMs = 0;
        synchronized (nextWakeMS) {
            wakeMs = System.currentTimeMillis() + latencyMS;
            wakeMs = Math.max(wakeMs, nextWakeMS[oldest]);
            nextWakeMS[oldest] = wakeMs + 1000;
            if (++oldest >= nextWakeMS.length)
                oldest = 0;
        }
        return Math.max(0, wakeMs - System.currentTimeMillis());
    }


//    public void executeOld(Runnable command) {
//        builder.start(new WaitThreadOld(command));
//    }
//
//    class WaitThreadOld implements Runnable {
//        final Runnable command;
//
//        WaitThread(Runnable command) {
//            this.command = command;
//        }
//
//        @Override
//        public void run() {
//            int thisThreadCount;
//
//            try {
//                maxThreadsSemaphore.acquire();
//                thisThreadCount = ++countConcurrentThreads;
//
//                System.out.println("Start  - Thread # " + thisThreadCount);
//                Thread thread = Thread.startVirtualThread(command);
//                thread.join(5000);
//            } catch (InterruptedException e) {
//                throw new RuntimeException(e);
//            } finally {
//                countConcurrentThreads--;
//                maxThreadsSemaphore.release();
//            }
//            System.out.println("Finish - Thread # " + thisThreadCount);
//        }
//    }
}
