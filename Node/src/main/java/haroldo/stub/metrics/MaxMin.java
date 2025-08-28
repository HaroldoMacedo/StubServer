package haroldo.stub.metrics;

public class MaxMin {
    private long max = Integer.MIN_VALUE;
    private long min = Integer.MAX_VALUE;

    public synchronized void addSample(long sample) {
        if (sample < min)
            min = sample;
        if (sample > max)
            max = sample;

    }

    public long getMax() {
        if (max == Integer.MIN_VALUE)
            return 0;
        return max;
    }

    public long getMin() {
        if (min == Integer.MAX_VALUE)
            return 0;
        return min;
    }

    @Override
    public String toString() {
        return "Max = " + getMax() + ", Min = " + getMin();
    }
}
