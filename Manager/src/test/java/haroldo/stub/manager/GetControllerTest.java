package haroldo.stub.manager;

import haroldo.stub.manager.resource.Service;
import haroldo.stub.manager.response.ManagerResponse;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class GetControllerTest {
  EmulateServer server = new EmulateServer();
  Random random = new Random(System.currentTimeMillis());

  @Test
  public void getServiceTest() {
    String uri = "/add/service/test-getService";
    int id = random.nextInt(100000);
    int portNumber = 443;
    String serviceName = "GET Service";
    String responseMsg = "This is a GET test!";

    //  Create service.
    Service service = new Service(id, uri);
    service.setResponseMessage(responseMsg);
    service.setPortNumber(portNumber);
    service.setName(serviceName);

    ManagerResponse managerResponse = (ManagerResponse)(server.postService(service)).getBody();
    assert(managerResponse.getServiceID() == id);

    Service getResponse = (Service)(server.getService(id)).getBody();
    assert(getResponse.getId() == id);
    assert(getResponse.getUri().equals(uri));
    assert(getResponse.getResponseMessage().equals(responseMsg));
    assert(getResponse.getName().equals(serviceName));
    assert(getResponse.getPortNumber() == portNumber);
  }
}
