package haroldo.stub.manager.model;

import haroldo.stub.manager.resource.API;
import haroldo.stub.manager.resource.Attributes;

public class Service {
  private final int id;
  private static int nextId = 0;
  private API api;
  private Attributes attributes;

  Service(API api, Attributes attributes){
    this.id = nextId++;
    this.api = api;
    this.attributes = attributes;
  }

  public int getId() {
    return id;
  }

  public API getApi() {
    return api;
  }

  public void setApi(API api) {
    this.api = api;
  }

  public Attributes getAttributes() {
    return attributes;
  }

  public void setAttributes(Attributes attributes) {
    this.attributes = attributes;
  }
}
