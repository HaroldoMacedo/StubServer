package haroldo.stub0.config.implementation;

import haroldo.stub0.config.PostTest;
import haroldo.stub0.config.Server;
import haroldo.stub0.config.resource.API;
import haroldo.stub0.config.resource.Attribute;
import haroldo.stub0.config.resource.ResourceId;
import haroldo.stub0.config.response.ManagerError;

public class PostTestImpl implements PostTest {
  private final Server server;

  public PostTestImpl(Server server) {
    this.server = server;
  }

  @Override
  public void startServiceTest() {
    ResourceId resourceId = server.startService(0);
    assert (resourceId.getId() == 0);
    assert (resourceId.getResourceName().equals("service"));
  }

  @Override
  public void startUnexistingServiceTest() {
    ManagerError response = server.startServiceBadRequest(54321);
//    assert (response.getErrorCode() > 0); //  TODO: Create response error codes.
    assert (response.getErrorMessage() != null);
  }

  @Override
  public void startImpossibleIdServiceTest() {
    ManagerError response = server.startServiceBadRequest(-1);
//    assert (response.getErrorCode() > 0); //  TODO: Create response error codes.
    assert (response.getErrorMessage() != null);
  }

  @Override
  public void addApi0Test() {
    String uri = "/default";
    API api = new API(uri);

    ManagerError managerError = server.postApiBadRequest(api);
//    assert (response.getErrorCode() > 0); //  TODO: Create response error codes.
    assert (managerError.getErrorMessage() != null);
  }

  @Override
  public void addApiTest() {
    String uri = "/add/service/test-addservice";
    API api = new API(uri);

    ResourceId resourceId = server.postApi(api);
    assert (resourceId.getResourceName().equals("api"));
  }

  @Override
  public void add10ApisTest() {
    String uri = "/add/service/test-addservices";

    for (int i = 0; i < 10; i++) {
      API api = new API(uri + i);
      ResourceId resourceId = server.postApi(api);
      assert (resourceId.getResourceName().equals("api"));
    }
  }

  @Override
  public void addDuplicatedApiTest() {
    String uri = "/add/service/test-duplicatedUri";
    API api = new API(uri);

    //  Adding API.
    ResourceId resourceId = server.postApi(api);
    assert (resourceId.getResourceName().equals("api"));

    //  Trying to add the same API (URI).
    api = new API(uri);
    ManagerError managerError = server.postApiBadRequest(api);
//    assert (response.getErrorCode() > 0); //  TODO: Create response error codes.
    assert (managerError.getErrorMessage() != null);
  }

  @Override
  public void addAttributeTest() {
    Attribute attribute = new Attribute();

    ResourceId resourceId = server.putAttribute(attribute);
    assert (resourceId.getResourceName().equals("attribute"));
  }

  @Override
  public void addDuplicatedAttributeTest() {
    Attribute attribute = new Attribute();

    ResourceId resourceId = server.putAttribute(attribute);
    assert (resourceId.getResourceName().equals("attribute"));
    resourceId = server.putAttribute(attribute);
    assert (resourceId.getResourceName().equals("attribute"));
  }

}
