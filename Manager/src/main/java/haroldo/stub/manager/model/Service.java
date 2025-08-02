package haroldo.stub.manager.model;

import haroldo.stub.manager.resource.API;
import haroldo.stub.manager.resource.Attribute;
import haroldo.stub.manager.resource.ResourceId;
import haroldo.stub.manager.resource.Resource;

public class Service implements Resource {
  private static int nextId = 0;

  private final ResourceId resourceId;
  private API api;
  private Attribute attribute;

  public Service(){
    this.resourceId = new ResourceId();
  }

  Service(API api, Attribute attribute){
    this.resourceId = new ResourceId("service", nextId++);
    this.api = api;
    this.attribute = attribute;
  }

  @Override
  public ResourceId getResourceId() {
    return resourceId;
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
    return resourceId.equals(obj);
  }

  @Override
  public int hashCode() {
    return resourceId.hashCode();
  }
}
