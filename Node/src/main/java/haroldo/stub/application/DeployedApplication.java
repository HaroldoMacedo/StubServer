package haroldo.stub.application;

import haroldo.stub.operation.Operation;

public interface DeployedApplication extends Operation, MessageGenerator {

    void setStarted();
    void setStopped();
    boolean isStarted();

    void setId(int id);
    int getId();

    void throttleMaxThroughput() throws  InterruptedException;

    long setIncomingRequestStart();
    void setIncomingRequestEnd(long requestId);
}
