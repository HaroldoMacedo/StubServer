package haroldo.stub.operation;

import haroldo.stub.application.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppsDeployed {
    List<DeployedApplication> appDeployedList = new ArrayList<>();
    private final static String[] methodNameArray = {"GET", "POST", "PUT", "DELETE"};
    private final static List<String> methodsName = Arrays.asList(methodNameArray);

    public AppsDeployed() {
        System.out.println("\tStarting AppsDeployed!!");
    }

    public void deployApp(DeployedApplication newApplication) throws DeployException {
        deploy(newApplication);
    }

    public void deployApp(int method, String uri, long avgLatencyMS, int maxThroughputTPS) throws DeployException {
        Operation operation = new OperationImpl("noname", method, uri);
        MessageGenerator messageGenerator = new DefaultMessageGenerator("Hello World!");
        NonFunctionaRequirements nfrs = new NonFunctionaRequirements(avgLatencyMS, maxThroughputTPS);

        deploy(new DeployedApplicationImpl(operation, nfrs, messageGenerator));
        System.out.println("Deployed App '" + methodNameArray[method] + " " + uri + "' with latency of " + avgLatencyMS + "ms and max throughput of " + maxThroughputTPS + "TPS");
    }

    private void deploy(DeployedApplication application) throws DeployException {
        if (!appDeployedList.isEmpty()) {
            int appIndex = appDeployedList.indexOf(application);
            if (appIndex >= 0)
                throw new DeployException("Cannot deploy '" + application.getUri() + "', similar app already deployed");
        }
        appDeployedList.add(application);
    }

    public DeployedApplication getApp(String method, String uri) throws DeployException {
        int appIndex = appDeployedList.indexOf(new DummyDeployedApplication(method, uri));
        if (appIndex < 0)
            throw new DeployException("Application '" + method + " " + uri + "' does not exist");

        return appDeployedList.get(appIndex);
    }

    public int getMethodIdFromName(String methodName) {
        return methodsName.indexOf(methodName);
    }

    public DeployedApplication getApplicationById(int applicationId) {
        return appDeployedList.stream()
                .filter(deployedApplication -> deployedApplication.getId() == applicationId)
                .findFirst()
                .orElse(null);
    }


    public DeployedApplication getApplicationByName(String applicationName) {
        return appDeployedList.stream()
                .filter(deployedApplication -> deployedApplication.getOperationName().equals(applicationName))
                .findFirst()
                .orElse(null);
    }

    public void undeployApp(int applicationId) {
        appDeployedList.removeIf(deployedApplication -> deployedApplication.getId() == applicationId);
    }


    private class DummyDeployedApplication implements DeployedApplication {

        int id;
        int method;
        String uri;

        DummyDeployedApplication(String method, String uri) {
            this.uri = uri;
            this.method = getMethodIdFromName(method);
        }

        @Override
        public void setStarted() {

        }

        @Override
        public void setStopped() {

        }

        @Override
        public boolean isStarted() {
            return false;
        }

        @Override
        public int getId() {
            return id;
        }

        @Override
        public void setId(int id) {
            this.id = id;
        }

        @Override
        public String getNextMessage() {
            return "";
        }

        @Override
        public String getOperationName() {
            return "Dummy!";
        }

        @Override
        public int getHttpMethod() {
            return method;
        }

        @Override
        public String getUri() {
            return uri;
        }

        @Override
        public void throttleMaxThroughput() {

        }

        @Override
        public long setIncomingRequestStart() {
            return 0;
        }

        @Override
        public void setIncomingRequestEnd(long requestId) {

        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof DeployedApplication))
                return false;

            DeployedApplicationImpl deployedApplication = (DeployedApplicationImpl) obj;
            return uri.matches(deployedApplication.uriRegEx) && method == deployedApplication.getHttpMethod();
        }

        @Override
        public int hashCode() {
            return method;
        }

    }
}
