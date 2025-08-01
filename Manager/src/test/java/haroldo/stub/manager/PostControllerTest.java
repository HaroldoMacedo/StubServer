package haroldo.stub.manager;

import haroldo.stub.manager.controller.ManagerController;
import haroldo.stub.manager.resource.Service;
import haroldo.stub.manager.response.ManagerError;
import haroldo.stub.manager.response.ManagerResponse;
import org.junit.jupiter.api.Test;

import java.util.Random;

public class PostControllerTest {
  EmulateServer server = new EmulateServer();
  Random random = new Random(System.currentTimeMillis());

  @Test
  public void addServiceTest() {
    String uri = "/add/service/test-addservice";
    int id = random.nextInt(100000);
    Service service = new Service(id, uri);

    ManagerResponse managerResponse = (ManagerResponse)(server.postService(service)).getBody();
    assert(managerResponse.getServiceID() == id);
  }

  @Test
  public void add10ServicesTest() {
    String uri = "/add/service/test-addservices";

    for (int i = 0; i < 10; i++) {
      int id = random.nextInt(100000);
      Service service = new Service(id + i, uri + id + i);
      ManagerResponse managerResponse = (ManagerResponse)(server.postService(service)).getBody();
      assert (managerResponse.getServiceID() == id + i);
    }
  }

  @Test
  public void duplicatedUriTest() {
    String uri = "/add/service/test-duplicatedUri";
    int id = random.nextInt(100000);
    Service service = new Service(id, uri);
    ManagerController controller = new ManagerController();

    //  First POST.
    ManagerResponse managerResponse = (ManagerResponse)(server.postService(service)).getBody();
    assert(managerResponse.getServiceID() == id);

    //  Second POST.
    ManagerError managerError = (ManagerError)(server.postServiceBadRequest(service)).getBody();
    assert(managerError.getErrorCode() == 0);
    assert(managerError.getErrorMessage() != null);
  }
}
