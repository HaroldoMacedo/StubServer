package haroldo.stub.manager;

import haroldo.stub.manager.resource.API;
import haroldo.stub.manager.resource.Attributes;
import haroldo.stub.manager.model.Service;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class GetControllerTest {
  EmulateServer server = new EmulateServer();
  Random random = new Random(System.currentTimeMillis());

  @Test
  public void getServiceTest() {
    Service getResponse = (Service)(server.getService(0)).getBody();
    assert(getResponse.getId() == 0);
    API api = getResponse.getApi();
    assert(api.getId() == 0);
    assert(api.getUri().equals("/default"));
    assert(api.getName().equals("api-0"));
    Attributes attributes = getResponse.getAttributes();
    assert(attributes.getResponseMessage().equals(Attributes.ATTRIBUTES_DEFAULT_MESSAGE));
    assert(attributes.getPortNumber() == 80);
    assert(attributes.getScalability() == 10);
    assert(attributes.getResponseTimeMS() == 100);
  }

  @Test
  public void getApiAttributesTest() {
    Attributes getResponse = (Attributes)(server.getAttributes(0).getBody());
    assert(getResponse.getResponseMessage().equals(Attributes.ATTRIBUTES_DEFAULT_MESSAGE));
    assert(getResponse.getPortNumber() == 80);
    assert(getResponse.getResponseTimeMS() == 100);
    assert(getResponse.getScalability() == 10);
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
