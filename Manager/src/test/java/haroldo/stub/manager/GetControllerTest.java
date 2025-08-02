package haroldo.stub.manager;

import haroldo.stub.manager.model.Service;
import haroldo.stub.manager.resource.API;
import haroldo.stub.manager.resource.Attribute;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class GetControllerTest {
  EmulateServer server = new EmulateServer();

  @Test
  public void getServiceTest() {
    Service service = (Service) (server.getService(0)).getBody();
    assert (service.getResource().getId() == 0);
    assert (service.getResource().getResourceName().equals("service"));

    API api = service.getApi();
    assert (api.getResource().getId() == 0);
    assert (api.getResource().getResourceName().equals("api"));
    assert (api.getUri().equals("/default"));
    assert (api.getName().equals("api-0"));
    Attribute attribute = service.getAttributes();
    assert (attribute.getResource().getResourceName().equals("attribute"));
    assert (attribute.getResponseMessage().equals(Attribute.ATTRIBUTES_DEFAULT_MESSAGE));
    assert (attribute.getPortNumber() == 80);
    assert (attribute.getScalability() == 10);
    assert (attribute.getResponseTimeMS() == 100);
  }

  @Test
  public void getAttributeTest() {
    Attribute getResponse = (Attribute) (server.getAttribute(0).getBody());
    assert (getResponse.getResource().getResourceName().equals("attribute"));
    assert (getResponse.getResponseMessage().equals(Attribute.ATTRIBUTES_DEFAULT_MESSAGE));
    assert (getResponse.getPortNumber() == 80);
    assert (getResponse.getResponseTimeMS() == 100);
    assert (getResponse.getScalability() == 10);
  }

  @Test
  public void getApiTest() {
    API getResponse = (API) (server.getApi(0).getBody());
    assert (getResponse.getResource().getResourceName().equals("api"));
    assert (getResponse.getResource().getId() == 0);
    assert (getResponse.getUri().equals("/default"));
    assert (getResponse.getName().equals("api-0"));
  }


//  private API addDefaultTestApi() {
//    String uri = "/add/api/test-getApi";
//    int id = random.nextInt(100000);
//    int portNumber = 443;
//    String apiName = "GET Api";
//    String responseMsg = "This is a GET test!";
//
//    //  Create api.
//    API api = new API(id, uri);
//    api.setName(apiName);
//
//    ManagerResponse managerResponse = (ManagerResponse)(server.postApi(api)).getBody();
//    assert(managerResponse.getApiID() == id);
//
//    return api;
//  }

//  private API addDefaultTestApiAttributes(int AttributesId) {
//    String uri = "/add/api/test-getApi";
//    int id = random.nextInt(100000);
//    String apiName = "GET Api";
//
//    //  Create api.
//    API api = new API(id, uri);
//    api.setName(apiName);
//
//    ManagerResponse managerResponse = (ManagerResponse)(server.startApi(api)).getBody();
//    assert(managerResponse. == id);
//
//    return api;
//  }
}
