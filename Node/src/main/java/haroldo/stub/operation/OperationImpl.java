package haroldo.stub.operation;

import java.util.Arrays;
import java.util.List;

public class OperationImpl implements Operation {
    private final String operationName;
    private final int httpMethod;
    private final String uri;

    private final static String[] methodNameArray = {"GET", "POST", "PUT", "DELETE"};
    private final static List<String> methodNameList = Arrays.asList(methodNameArray);

    public OperationImpl(String operationName, int httpMethod, String uri) {
        this.operationName = operationName;
        this.httpMethod = httpMethod;
        this.uri = uri;
    }

    public OperationImpl(String operationName, String httpMethodName, String uri) {
        this.operationName = operationName;
        this.httpMethod =  methodNameList.indexOf(httpMethodName);
        this.uri = uri;
    }

    @Override
    public String getOperationName() {
        return operationName;
    }

    @Override
    public int getHttpMethod() {
        return httpMethod;
    }

    @Override
    public String getUri() {
        return uri;
    }
}
