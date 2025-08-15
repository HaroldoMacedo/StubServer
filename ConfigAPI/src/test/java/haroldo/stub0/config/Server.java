package haroldo.stub0.config;

import haroldo.stub0.config.model.Service;
import haroldo.stub0.config.resource.API;
import haroldo.stub0.config.resource.Attribute;
import haroldo.stub0.config.resource.ResourceId;
import haroldo.stub0.config.response.ManagerError;

public interface Server {

  Service getService(int id);
  Attribute getAttribute(int id);
  API getApi(int id);

  ResourceId startService(int id);
  ManagerError startServiceBadRequest(int id);
  ResourceId postApi(API api);
  ManagerError postApiBadRequest(API api);
  ResourceId putAttribute(Attribute attribute);
}
