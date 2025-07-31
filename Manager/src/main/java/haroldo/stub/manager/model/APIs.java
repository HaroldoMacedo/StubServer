package haroldo.stub.manager.model;

import haroldo.stub.manager.resource.Behaviour;
import haroldo.stub.manager.resource.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class APIs {
  private static final Map<Integer, API> apis = new HashMap<>();
  static {
    apis.put(0, new API(Service.service0));
  }

  public static void putAPI(Service service, Behaviour behaviour) {
    apis.put(service.getId(), new API(service, behaviour));
  }

  public static Service addOrReplace(Service service) {
    if (service.getId() <= 0)
      return null;

    API api = apis.computeIfAbsent(service.getId(), id -> new API(service));
    api.setService(service);
    return api.getService();
  }

  public static boolean exist(int serviceId) {
    return apis.containsKey(serviceId);
  }

  public static Service getService(int serviceId) {
    return Optional.ofNullable(apis.get(serviceId))
            .map(API::getService)
            .orElse(null);
  }

  public static Behaviour getBehaviour(int serviceId) {
    return Optional.ofNullable(apis.get(serviceId))
            .map(API::getBehaviour)
            .orElse(null);
  }

}
