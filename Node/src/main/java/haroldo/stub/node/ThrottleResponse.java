package haroldo.stub.node;

import java.util.Random;

class ThrottleResponse implements Runnable {
    final Runnable command;
    final long sleepTimeMS;

    ThrottleResponse(long sleepTimeMS, Runnable command) {
        this.sleepTimeMS = sleepTimeMS;
        this.command = command;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(sleepTimeMS);
            Thread thread = Thread.startVirtualThread(command);

            thread.join(50000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
