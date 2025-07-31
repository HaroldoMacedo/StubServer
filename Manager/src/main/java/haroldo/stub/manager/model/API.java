package haroldo.stub.manager.model;

import haroldo.stub.manager.resource.Behaviour;
import haroldo.stub.manager.resource.Service;

public class API {
  private static final Behaviour defaultBehaviour = new Behaviour();
  private Service service;
  private Behaviour behaviour;

  API(Service service){
    this.service = service;
    this.behaviour = defaultBehaviour;
  }

  API(Service service, Behaviour behaviour){
    this.service = service;
    this.behaviour = behaviour;
  }

  public Service getService() {
    return service;
  }

  public void setService(Service service) {
    this.service = service;
  }

  public Behaviour getBehaviour() {
    return behaviour;
  }

  public void setBehaviour(Behaviour behaviour) {
    this.behaviour = behaviour;
  }
}
