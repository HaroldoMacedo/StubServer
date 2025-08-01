package haroldo.stub.manager;

import haroldo.stub.manager.controller.ManagerController;
import haroldo.stub.manager.resource.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class EmulateServer {
  ManagerController controller = new ManagerController();

  ResponseEntity<?> postService(Service service) {
    return postService(service, HttpStatus.OK);
  }

  ResponseEntity<?> postServiceBadRequest(Service service) {
    return postService(service, HttpStatus.BAD_REQUEST);
  }


  ResponseEntity<?> postService(Service service, HttpStatus code) {
    ResponseEntity<?> response = controller.postService(service);
    assert(response.getStatusCode() == code);
    assert(response.hasBody());

    return response;
  }

  ResponseEntity<?> getService(int id) {
    ResponseEntity<?> response = controller.getService(id);
    assert(response.getStatusCode() == HttpStatus.OK);
    assert(response.hasBody());

    return response;
  }

}
