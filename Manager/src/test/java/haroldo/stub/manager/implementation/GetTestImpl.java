package haroldo.stub.manager.implementation;

import haroldo.stub.manager.GetTest;
import haroldo.stub.manager.Server;
import haroldo.stub.manager.model.Service;
import haroldo.stub.manager.resource.API;
import haroldo.stub.manager.resource.Attribute;

public class GetTestImpl implements GetTest {
  private final Server server;
  public GetTestImpl(Server server) {
    this.server = server;
  }

  @Override
  public void getServiceTest() {
    Service service = server.getService(0);
    assert (service.getResourceId().getId() == 0);
    assert (service.getResourceId().getResourceName().equals("service"));

    API api = service.getApi();
    assert (api.getResourceId().getId() == 0);
    assert (api.getResourceId().getResourceName().equals("api"));
    assert (api.getUri().equals("/default"));
    assert (api.getName().equals("api-0"));
    Attribute attribute = service.getAttributes();
    assert (attribute.getResourceId().getResourceName().equals("attribute"));
    assert (attribute.getResponseMessage().equals(Attribute.ATTRIBUTES_DEFAULT_MESSAGE));
    assert (attribute.getPortNumber() == 80);
    assert (attribute.getScalability() == 10);
    assert (attribute.getResponseTimeMS() == 100);
  }

  @Override
  public void getAttributeTest() {
    Attribute getResponse = server.getAttribute(0);
    assert (getResponse.getResourceId().getResourceName().equals("attribute"));
    assert (getResponse.getResponseMessage().equals(Attribute.ATTRIBUTES_DEFAULT_MESSAGE));
    assert (getResponse.getPortNumber() == 80);
    assert (getResponse.getResponseTimeMS() == 100);
    assert (getResponse.getScalability() == 10);
  }

  @Override
  public void getApiTest() {
    API getResponse = server.getApi(0);
    assert (getResponse.getResourceId().getResourceName().equals("api"));
    assert (getResponse.getResourceId().getId() == 0);
    assert (getResponse.getUri().equals("/default"));
    assert (getResponse.getName().equals("api-0"));
  }
}
