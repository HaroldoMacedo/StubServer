package haroldo.stub.metrics;

import org.junit.jupiter.api.Test;

import java.util.Random;

public class AvgStdDevTest {
    @Test
    public void stdDevTest()
    {
        Random rand = new Random(0);
        AvgStdDev avgStdDev = new AvgStdDev();

        final int numberOfValues = 1000000;
        long[] values = new long[numberOfValues];
        for (int i = 0; i < numberOfValues; i++) {
            values[i] = Math.round(rand.nextGaussian(2000, 200));
        }

        long ns = System.nanoTime();
        for (int i = 0; i < numberOfValues; i++) {
            avgStdDev.addValue(values[i]);
        }
        ns = System.nanoTime() - ns;

        System.out.printf("AvgStdDev: %.3fms for %d values or around %3.0fns per value\n",
                ns/1000000.0, numberOfValues, ((float)ns) / numberOfValues);
        System.out.println(avgStdDev);

        assert(Math.abs(avgStdDev.getAvg() - 2000) <= 2);
        assert(Math.abs(avgStdDev.getStdDev() - 200) <= 4);
        assert(avgStdDev.getMin() <= avgStdDev.getAvg());
        assert (avgStdDev.getMax() >= avgStdDev.getAvg());
    }
}
