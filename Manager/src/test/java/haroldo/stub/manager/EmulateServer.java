package haroldo.stub.manager;

import haroldo.stub.manager.controller.ManagerController;
import haroldo.stub.manager.model.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class EmulateServer {
  ManagerController controller = new ManagerController();

  ResponseEntity<?> startService(int serviceId) {
    return startService(serviceId, HttpStatus.OK);
  }

  ResponseEntity<?> startServiceBadRequest(int serviceId) {
    return startService(serviceId, HttpStatus.BAD_REQUEST);
  }


  ResponseEntity<?> startService(int serviceId, HttpStatus code) {
    ResponseEntity<?> response = controller.startService(serviceId);
    assert(response.getStatusCode() == code);
    assert(response.hasBody());

    return response;
  }

  ResponseEntity<?> getService(int serviceId) {
    ResponseEntity<?> response = controller.getService(serviceId);
    assert(response.getStatusCode() == HttpStatus.OK);
    assert(response.hasBody());

    return response;
  }

  ResponseEntity<?> getAttributes(int attributesId) {
    ResponseEntity<?> response = controller.getAttributes(attributesId);
    assert(response.getStatusCode() == HttpStatus.OK);
    assert(response.hasBody());

    return response;
  }
}
