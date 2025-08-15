package haroldo.stub0.config.model;

import haroldo.stub0.config.resource.API;
import haroldo.stub0.config.resource.Attribute;
import haroldo.stub0.config.resource.Resource;
import haroldo.stub0.config.resource.ResourceId;

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
