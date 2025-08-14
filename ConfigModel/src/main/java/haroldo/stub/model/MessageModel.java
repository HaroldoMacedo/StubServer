package haroldo.stub.model;

public class MessageModel {
    private String message;
    private int minResponseTimeMs;
    private int maxResponseTimeMs;
    private int meanResponseTimeMs;
    private int stdDevResponseTimeMs;
    private double sizeResponseTimeMs;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getMinResponseTimeMs() {
        return minResponseTimeMs;
    }

    public void setMinResponseTimeMs(int minResponseTimeMs) {
        this.minResponseTimeMs = minResponseTimeMs;
    }

    public int getMaxResponseTimeMs() {
        return maxResponseTimeMs;
    }

    public void setMaxResponseTimeMs(int maxResponseTimeMs) {
        this.maxResponseTimeMs = maxResponseTimeMs;
    }

    public int getMeanResponseTimeMs() {
        return meanResponseTimeMs;
    }

    public void setMeanResponseTimeMs(int meanResponseTimeMs) {
        this.meanResponseTimeMs = meanResponseTimeMs;
    }

    public int getStdDevResponseTimeMs() {
        return stdDevResponseTimeMs;
    }

    public void setStdDevResponseTimeMs(int stdDevResponseTimeMs) {
        this.stdDevResponseTimeMs = stdDevResponseTimeMs;
    }

    public double getSizeResponseTimeMs() {
        return sizeResponseTimeMs;
    }

    public void setSizeResponseTimeMs(double sizeResponseTimeMs) {
        this.sizeResponseTimeMs = sizeResponseTimeMs;
    }
}
