package haroldo.stub.manager.model;

import haroldo.stub.manager.resource.API;
import haroldo.stub.manager.resource.Attribute;
import haroldo.stub.manager.resource.Resource;
import haroldo.stub.manager.resource.ResourceId;

public class Service implements ResourceId {
  private static int nextId = 0;

  private final Resource resource;
  private API api;
  private Attribute attribute;

  Service(API api, Attribute attribute){
    this.resource = new Resource("service", nextId++);
    this.api = api;
    this.attribute = attribute;
  }

  @Override
  public Resource getResource() {
    return resource;
  }

  public API getApi() {
    return api;
  }

  public void setApi(API api) {
    this.api = api;
  }

  public Attribute getAttributes() {
    return attribute;
  }

  public void setAttributes(Attribute attribute) {
    this.attribute = attribute;
  }

  @Override
  public boolean equals(Object obj) {
    return resource.equals(obj);
  }

  @Override
  public int hashCode() {
    return resource.hashCode();
  }
}
