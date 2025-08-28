package haroldo.stub.metrics;

import java.util.HashMap;
import java.util.Map;

public class UsageMetrics {
    Map<Integer, DeployedApplicationUsageMetric> usageMetricList = new HashMap<>();

    synchronized DeployedApplicationUsageMetric getMetricForDeployedApplicationId(int id) {
        DeployedApplicationUsageMetric metric;
        metric = usageMetricList.get(id);
        if (metric == null) {
            metric = new DeployedApplicationUsageMetric();
            usageMetricList.put(id, metric);
        }

        return metric;
    }
}
