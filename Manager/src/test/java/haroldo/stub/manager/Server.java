package haroldo.stub.manager;

import haroldo.stub.manager.model.Service;
import haroldo.stub.manager.resource.API;
import haroldo.stub.manager.resource.Attribute;
import haroldo.stub.manager.resource.ResourceId;
import haroldo.stub.manager.response.ManagerError;

public interface Server {

  public Service getService(int id);
  public Attribute getAttribute(int id);
  public API getApi(int id);

  public ResourceId startService(int id);
  public ManagerError startServiceBadRequest(int id);
  public ResourceId postApi(API api);
  public ManagerError postApiBadRequest(API api);
  public ResourceId putAttribute(Attribute attribute);
}
