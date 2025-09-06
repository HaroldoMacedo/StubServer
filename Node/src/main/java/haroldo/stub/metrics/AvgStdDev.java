package haroldo.stub.metrics;

public class AvgStdDev {
    private int count;
    private long sum;
    private final MaxMin maxMin = new MaxMin();
    private float avg = 0;
    private double variance = 0;

    public synchronized void addValue(long value) {
        maxMin.addSample(value);
        count++;
        sum += value;
        calcAverageAndVariance(value);
    }

    private void calcAverageAndVariance(long value) {
        if (count == 0)
            return;

        float newAvg = (float) sum / count;
        if (count == 1) {
            variance = (value - newAvg) * (value - avg);
            return;
        }

        variance =  ((count - 2) * variance + (value - newAvg) * (value - avg)) / (count - 1);
        avg = newAvg;
    }

    public float getAvg() {
        return avg;
    }

    public float getStdDev() {
        return (float) Math.sqrt(variance);
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
