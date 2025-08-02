package haroldo.stub.manager.model;

import haroldo.stub.manager.resource.Attribute;
import haroldo.stub.manager.resource.API;
import haroldo.stub.manager.resource.Resource;

import java.util.*;

public class Manager {
  private static final Map<Integer, Service> services = new HashMap<>();
  private static final Set<API> apis = new HashSet<>();
  private static final Set<Attribute> attributes = new HashSet<>();

  static {
    API api = new API("/default");
    Attribute atr = new Attribute();

    services.put(0, new Service(api,atr));
    apis.add(api);
    attributes.add(atr);
  }

  public static Service startService(int serviceId) throws ServicesException {
    Service service = getService(serviceId);
    if (service == null)
      throw new ServicesException("Service id = '" + serviceId + "' does not exist");
    return service;
  }

  public static API addApi(String uri) throws ServicesException {
    return addApi(new API(uri));
  }

  public static API addApi(API api) throws ServicesException {
    if (apis.add(api) == false)
      throw new ServicesException("API with URI '" + api.getUri() + "' already exists!");
    return api;
  }

  public static Attribute putAttribute(Attribute attribute) {
    attributes.remove(attribute);
    attributes.add(attribute);
    return attribute;
  }

  public static Attribute getAttributes(int attributesId) {
    return (Attribute)findFromIdInList(attributes, attributesId);
  }

  public static API getApi(int apiId) {
    return (API)findFromIdInList(apis, apiId);
  }

  public static <T extends Resource> Resource findFromIdInList(Set<T> set, int id) {
    return set.stream()
            .filter(resourceId -> resourceId.getResourceId().getId() == id)
            .findFirst()
            .orElse(null);
  }

  public static Service getService(Integer serviceId) {
    return services.get(serviceId);
  }
}
