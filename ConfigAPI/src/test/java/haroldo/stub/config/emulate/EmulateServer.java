package haroldo.stub.config.emulate;

import haroldo.stub.config.Server;
import haroldo.stub.config.controller.ConfigController;
import haroldo.stub.config.model.Service;
import haroldo.stub.config.resource.API;
import haroldo.stub.config.resource.Attribute;
import haroldo.stub.config.resource.ResourceId;
import haroldo.stub.config.response.ManagerError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class EmulateServer implements Server {
  ConfigController controller = new ConfigController();

  @Override
  public ResourceId startService(int serviceId) {
    return (ResourceId) startService(serviceId, HttpStatus.OK).getBody();
  }

  @Override
  public ManagerError startServiceBadRequest(int serviceId) {
    return (ManagerError)startService(serviceId, HttpStatus.BAD_REQUEST).getBody();
  }

  private ResponseEntity<?> startService(int serviceId, HttpStatus code) {
    ResponseEntity<?> response = controller.startService(serviceId);
    assert (response.getStatusCode() == code);
    assert (response.hasBody());

    return response;
  }

  @Override
  public Service getService(int serviceId) {
    ResponseEntity<?> response = controller.getService(serviceId);
    assert (response.getStatusCode() == HttpStatus.OK);
    assert (response.hasBody());

    return (Service) response.getBody();
  }

  @Override
  public Attribute getAttribute(int attributesId) {
    ResponseEntity<?> response = controller.getAttributes(attributesId);
    assert (response.getStatusCode() == HttpStatus.OK);
    assert (response.hasBody());

    return (Attribute) response.getBody();
  }

  @Override
  public API getApi(int apiId) {
    ResponseEntity<?> response = controller.getApi(apiId);
    assert (response.getStatusCode() == HttpStatus.OK);
    assert (response.hasBody());

    return (API) response.getBody();
  }

  @Override
  public ResourceId postApi(API api) {
    return (ResourceId)postApi(api, HttpStatus.OK).getBody();
  }

  public ManagerError postApiBadRequest(API api) {
    return (ManagerError)postApi(api, HttpStatus.BAD_REQUEST).getBody();
  }

  ResponseEntity<?> postApi(API api, HttpStatus code) {
    ResponseEntity response = controller.postApi(api);
    assert (response.getStatusCode() == code);
    assert (response.hasBody());

    return response;
  }

  @Override
  public ResourceId putAttribute(Attribute attribute) {
    return (ResourceId)putAttribute(attribute, HttpStatus.OK).getBody();
  }

  ResponseEntity<?> putAttribute(Attribute attribute, HttpStatus code) {
    ResponseEntity response = controller.putAttribute(attribute);
    assert (response.getStatusCode() == code);
    assert (response.hasBody());

    return response;
  }
}
