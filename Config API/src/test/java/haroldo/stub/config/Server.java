package haroldo.stub.config;

import haroldo.stub.config.model.Service;
import haroldo.stub.config.resource.API;
import haroldo.stub.config.resource.Attribute;
import haroldo.stub.config.resource.ResourceId;
import haroldo.stub.config.response.ManagerError;

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
