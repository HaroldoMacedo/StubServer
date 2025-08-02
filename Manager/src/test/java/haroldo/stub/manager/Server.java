package haroldo.stub.manager;

import haroldo.stub.manager.model.Service;
import haroldo.stub.manager.resource.API;
import haroldo.stub.manager.resource.Attribute;

public interface Server {

  public Service getService(int id);
  public Attribute getAttribute(int id);
  public API getApi(int id);
}
