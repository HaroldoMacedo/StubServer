package haroldo.stub.application;

import haroldo.stub.metrics.AvgStdDev;
import haroldo.stub.operation.Operation;
import haroldo.stub.throttle.Throttle;

public class DeployedApplicationImpl implements DeployedApplication, MessageGenerator {
    private final Operation operation;
    private final MessageGenerator messageGenerator;
    private boolean isStarted = false;

    private final Throttle throttle;

    private final AvgStdDev statMaxThroughputPerSec = new AvgStdDev();
    private final AvgStdDev statResponseTimeMS = new AvgStdDev();

    private int id;
    public final String uriRegEx;

    private Long timestampSecondOfLastResponse = 0L;
    private int countResponses = 0;

    public DeployedApplicationImpl(Operation operation, NonFunctionaRequirements nfrs,
                                   MessageGenerator messageGenerator) throws DeployException {
        if (operation.getHttpMethod() < 0 || operation.getHttpMethod() >= 4)
            throw new DeployException("Method '" + operation.getHttpMethod() + "' no allowed. " +
                    "Permitted methods number are: GET -> 0, POST -> 1, PUT -> 2, DELETE -> 3");
        String uri = operation.getUri();
        if (uri.charAt(0) != '/')
            throw new DeployException("URI must start with '/'");
        if (uri.charAt(uri.length() - 1) != '/')
            throw new DeployException("URI must end with '/'");
        if (uri.length() < 2)
            throw new DeployException("URI must have at least 2 characters");
        if (nfrs.getAvgLatencyMS() < 0)
            throw new DeployException("Latency must not be a negative number");
        if (nfrs.getMaxThroughputTPS() <= 0)
            throw new DeployException("Max Throughput must be a positive number");

        this.operation = operation;
        this.messageGenerator = messageGenerator;
        this.throttle = new Throttle(nfrs);
        this.uriRegEx = operation.getUri().replaceAll("/\\{.*?\\}/", "/[0-9]*/");
    }

    @Override
    public void setStarted() {
        isStarted = true;
    }

    @Override
    public void setStopped() {
        isStarted = false;
    }

    @Override
    public boolean isStarted() {
        return isStarted;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getNextMessage() {
        return messageGenerator.getNextMessage();
    }

    @Override
    public String getOperationName() {
        return "";
    }

    @Override
    public int getHttpMethod() {
        return operation.getHttpMethod();
    }

    @Override
    public String getUri() {
        return operation.getUri();
    }

    @Override
    public void throttleMaxThroughput() throws InterruptedException {
        throttle.toMaxThroughput();
    }

    @Override
    public long setIncomingRequestStart() {
        return System.currentTimeMillis();
    }

    @Override
    public void setIncomingRequestEnd(long requestId) {
        long now = System.currentTimeMillis();
        statResponseTimeMS.addValue(now - requestId);
        calculateThroughputTPS(now);
    }

    private void calculateThroughputTPS(long now) {
        synchronized (timestampSecondOfLastResponse) {
            if (now - timestampSecondOfLastResponse < 1000) {
                countResponses++;
            } else {
                statMaxThroughputPerSec.addValue(countResponses);
                countResponses = 0;
                timestampSecondOfLastResponse = now - (now % 1000);
            }
        }
    }
}
