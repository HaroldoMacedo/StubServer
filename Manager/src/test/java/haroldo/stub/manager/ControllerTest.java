package haroldo.stub.manager;

import haroldo.stub.manager.controller.ManagerController;
import haroldo.stub.manager.resource.Service;
import org.junit.jupiter.api.Test;

public class ControllerTest {
  @Test
  public void addServiceTest() {
    ManagerController controller = new ManagerController();
    controller.postService(new Service("/add/service/test"));
  }
}
