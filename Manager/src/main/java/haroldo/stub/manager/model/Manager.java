package haroldo.stub.manager.model;

import haroldo.stub.manager.resource.Attributes;
import haroldo.stub.manager.resource.API;

import java.util.*;

public class Manager {
  private static final Map<Integer, Service> services = new HashMap<>();
  private static final List<API> apis = new ArrayList<>();
  private static final List<Attributes> attributes = new ArrayList<>();
  private static int nextServiceId = 1;

  static {
    API api = new API("/default");
    Attributes atr = new Attributes();

    services.put(0, new Service(api,atr));
    apis.add(api);
    attributes.add(atr);
  }

  public static void startService(int serviceId) throws ServicesException {
    Service service = getService(serviceId);
    if (service == null)
      throw new ServicesException("Service id = '" + serviceId + "' does not exist");
  }

//  public static API addService(int apiId, int attributesId) throws ServicesException {
//    Service service = new Service(api, attributes);
//    addService(service);
//    return service.getApi();
//  }
//
//  private static void addService(Service service) throws ServicesException {
//    if (exist(service.getApi().getUri()))
//      throw new ServicesException("URI '" + service.getApi().getUri() + "' already in use!");
//    services.put(nextServiceId++, service);
//  }
//
//  private static Integer addAttributes(Atributes atributes) {
//    this.atributes.;
//
//  }

  public static Attributes getAttributes(int attributesId) {
    return attributes.stream()
            .filter(atr -> atr.getId() == attributesId)
            .findAny()
            .orElse(null);
  }

  public static Service getService(Integer serviceId) {
    return services.get(serviceId);
  }

  public static boolean exist(String uri) {
    return services.values().stream()
            .anyMatch(api -> api.getApi().getUri().equals(uri));
  }

  public static Service getService(int serviceId) {
    return services.get(serviceId);
  }

  public static Attributes getServiceAttributes(int serviceId) {
    return Optional.ofNullable(services.get(serviceId))
            .map(Service::getAttributes)
            .orElse(null);
  }

}
