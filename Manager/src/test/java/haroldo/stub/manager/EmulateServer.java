package haroldo.stub.manager;

import haroldo.stub.manager.controller.ManagerController;
import haroldo.stub.manager.resource.API;
import haroldo.stub.manager.resource.Attribute;
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

  ResponseEntity<?> getAttribute(int attributesId) {
    ResponseEntity<?> response = controller.getAttributes(attributesId);
    assert(response.getStatusCode() == HttpStatus.OK);
    assert(response.hasBody());

    return response;
  }

  ResponseEntity<?> getApi(int apiId) {
    ResponseEntity<?> response = controller.getApi(apiId);
    assert(response.getStatusCode() == HttpStatus.OK);
    assert(response.hasBody());

    return response;
  }

  ResponseEntity<?> postApi(API api){
    return postApi(api, HttpStatus.OK);
  }

  ResponseEntity<?> postApiBadRequest(API api){
    return postApi(api, HttpStatus.BAD_REQUEST);
  }

  ResponseEntity<?> postApi(API api, HttpStatus code){
    ResponseEntity response = controller.postApi(api);
    assert(response.getStatusCode() == code);
    assert(response.hasBody());

    return response;
  }

  ResponseEntity<?> putAttribute(Attribute attribute){
    return putAttribute(attribute, HttpStatus.OK);
  }

  ResponseEntity<?> putAttribute(Attribute attribute, HttpStatus code){
    ResponseEntity response = controller.putAttribute(attribute);
    assert(response.getStatusCode() == code);
    assert(response.hasBody());

    return response;
  }
}
