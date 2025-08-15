package haroldo.stub0.model;

import java.util.ArrayList;
import java.util.List;

public class TestModel {
    private final String name;
    private List<TestMessage> testMessageList = new ArrayList<>();
    private float errorPercentage = 0;
    private float timeoutPercentage = 0;

    public TestModel(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public List<TestMessage> getTestMessageList() {
        return testMessageList;
    }

    public void setTestMessageList(List<TestMessage> testMessageList) {
        this.testMessageList = testMessageList;
    }

    public float getErrorPercentage() {
        return errorPercentage;
    }

    public void setErrorPercentage(float errorPercentage) {
        this.errorPercentage = errorPercentage;
    }

    public float getTimeoutPercentage() {
        return timeoutPercentage;
    }

    public void setTimeoutPercentage(float timeoutPercentage) {
        this.timeoutPercentage = timeoutPercentage;
    }
}
