package haroldo.stub.manager;

import haroldo.stub.manager.model.Manager;
import haroldo.stub.manager.resource.API;
import haroldo.stub.manager.resource.Attribute;
import haroldo.stub.manager.resource.ResourceId;
import haroldo.stub.manager.response.ManagerError;
import org.junit.jupiter.api.Test;

public class PostControllerTest {
  EmulateServer server = new EmulateServer();

  public PostControllerTest() {
    new Manager();  // Instatiated to create initial components Id = 0.
  }

  @Test
  public void startServiceTest() {
    ResourceId resourceId = (ResourceId)(server.startService(0).getBody());
    assert (resourceId.getId() == 0);
    assert (resourceId.getResourceName().equals("service"));
  }

  @Test
  public void startUnexistingServiceTest() {
    ManagerError response = (ManagerError)(server.startServiceBadRequest(54321).getBody());
//    assert (response.getErrorCode() > 0); //  TODO: Create response error codes.
    assert (response.getErrorMessage() != null);
  }

  @Test
  public void startImpossibleIdServiceTest() {
    ManagerError response = (ManagerError)(server.startServiceBadRequest(-1).getBody());
//    assert (response.getErrorCode() > 0); //  TODO: Create response error codes.
    assert (response.getErrorMessage() != null);
  }

  @Test
  public void addApi0Test() {
    String uri = "/default";
    API api = new API(uri);

    ManagerError managerError = (ManagerError)(server.postApiBadRequest(api)).getBody();
//    assert (response.getErrorCode() > 0); //  TODO: Create response error codes.
    assert(managerError.getErrorMessage() != null);
  }

  @Test
  public void addApiTest() {
    String uri = "/add/service/test-addservice";
    API api = new API(uri);

    ResourceId resourceId = (ResourceId)(server.postApi(api)).getBody();
    assert (resourceId.getResourceName().equals("api"));
  }

  @Test
  public void add10ApisTest() {
    String uri = "/add/service/test-addservices";

    for (int i = 0; i < 10; i++) {
      API api = new API(uri + i);
      ResourceId resourceId = (ResourceId)(server.postApi(api)).getBody();
      assert (resourceId.getResourceName().equals("api"));
    }
  }

  @Test
  public void addDuplicatedApiTest() {
    String uri = "/add/service/test-duplicatedUri";
    API api = new API(uri);

    //  Adding API.
    ResourceId resourceId = (ResourceId)(server.postApi(api)).getBody();
    assert (resourceId.getResourceName().equals("api"));

    //  Trying to add the same API (URI).
    api = new API(uri);
    ManagerError managerError = (ManagerError)(server.postApiBadRequest(api)).getBody();
//    assert (response.getErrorCode() > 0); //  TODO: Create response error codes.
    assert(managerError.getErrorMessage() != null);
  }

  @Test
  public void addAttributeTest() {
    Attribute attribute = new Attribute();

    ResourceId resourceId = (ResourceId)(server.putAttribute(attribute)).getBody();
    assert (resourceId.getResourceName().equals("attribute"));
  }

  @Test
  public void addDuplicatedAttributeTest() {
    Attribute attribute = new Attribute();

    ResourceId resourceId = (ResourceId)(server.putAttribute(attribute)).getBody();
    assert (resourceId.getResourceName().equals("attribute"));
    resourceId = (ResourceId)(server.putAttribute(attribute)).getBody();
    assert (resourceId.getResourceName().equals("attribute"));
  }

}
