package haroldo.stub.application;

import haroldo.stub.operation.Operation;

public interface DeployedApplication extends Operation, MessageGenerator {

    void setId(int id);
    int getId();

    void throttleMaxThroughput() throws  InterruptedException;

    long requestStart();
    void requestEnd(long requestId);
}
