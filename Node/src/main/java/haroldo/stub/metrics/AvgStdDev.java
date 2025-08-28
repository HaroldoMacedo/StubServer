package haroldo.stub.metrics;

public class AvgStdDev {
    private int count;
    private long sum;
    private final MaxMin maxMin = new MaxMin();
    private float avg = 0;
    private float stdDevSquare = 0;

    public synchronized void addTimeMs(long timeMs) {
        maxMin.addSample(timeMs);
        count++;
        sum += timeMs;
        calvAvgAndStdDev(timeMs);
    }

    private void calvAvgAndStdDev(long timeMs) {
        if (count == 0)
            return;

        float newAvg = (float) sum / count;
        if (count == 1) {
            stdDevSquare = (timeMs - newAvg) * (timeMs - avg);
            return;
        }

        stdDevSquare =  ((count - 2) * stdDevSquare + (timeMs - newAvg) * (timeMs - avg)) / (count - 1);
        avg = newAvg;
    }

    public float getAvg() {
        return avg;
    }

    public float getStdDev() {
        return (float) Math.sqrt(stdDevSquare);
    }

    public long getMax() {
        return maxMin.getMax();
    }

    public long getMin() {
        return maxMin.getMin();
    }

    @Override
    public String toString() {
        return "Avg = " + (int)getAvg() + ", Std Dev = " + (int)getStdDev() + ", " + maxMin;
    }
}
