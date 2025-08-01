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

  public static void putAPI(Service service) throws APIsException {
    putAPI(service, new Behaviour());
  }

  public static void putAPI(Service service, Behaviour behaviour) throws APIsException {
    putAPI(new API(service, behaviour));
  }

  public static void putAPI(API api) throws APIsException {
    // TODO: Check existing URI.
    apis.put(api.getService().getId(), api);
  }

  public static Service addOrReplace(Service service) throws APIsException {
    API api = new API(service);
    putAPI(api);
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
