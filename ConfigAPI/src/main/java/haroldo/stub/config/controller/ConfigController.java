package haroldo.stub.config.controller;

import haroldo.stub.config.model.Manager;
import haroldo.stub.config.model.Service;
import haroldo.stub.config.model.ServicesException;
import haroldo.stub.config.resource.API;
import haroldo.stub.config.resource.Attribute;
import haroldo.stub.config.response.ManagerError;
import haroldo.stub.config.response.ManagerResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Optional;

@RestController
public class ConfigController {
  public static final String BASE_URI = "/stub/manager";

  @PutMapping(BASE_URI + "/service/{serviceId}")
  @ResponseBody
  public ResponseEntity<?> startService(@PathVariable(name = "serviceId") int serviceId) {
    try {
      Service responseService = Manager.startService(serviceId);
      return ResponseEntity.ok(new ManagerResponse(responseService).getResource().getResourceId());
    } catch (ServicesException e) {
      System.err.println("Error: " + e.getMessage());
      return ResponseEntity.badRequest().body(new ManagerError(e.getMessage()));
    }
  }

  @PostMapping(BASE_URI + "/api")
  @ResponseBody
  public ResponseEntity<?> postApi(@RequestBody API api) {
    try {
      API responseApi = Manager.addApi(api);
      return ResponseEntity.ok(new ManagerResponse(responseApi).getResource().getResourceId());
    } catch (ServicesException e) {
      System.err.println("Error: " + e.getMessage());
      return ResponseEntity.badRequest().body(new ManagerError(e.getMessage()));
    }
  }

  @PutMapping(BASE_URI + "/attribute")
  @ResponseBody
  public ResponseEntity<?> putAttribute(@RequestBody Attribute attribute) {
    if (attribute == null)
      ResponseEntity.notFound().build();
    Attribute responseAttribute = Manager.putAttribute(attribute);
    return ResponseEntity.ok(new ManagerResponse(responseAttribute).getResource().getResourceId());
  }

  @GetMapping(BASE_URI + "/service/{serviceId}")
  @ResponseBody
  public ResponseEntity<?> getService(@PathVariable(name = "serviceId") Integer serviceId) {
    return Optional.ofNullable(Manager.getService(serviceId))
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping(BASE_URI + "/api/{apiId}")
  @ResponseBody
  public ResponseEntity<?> getApi(@PathVariable(name = "apiId") Integer apiId) {
    return Optional.ofNullable(Manager.getApi(apiId))
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
  }


  @GetMapping(BASE_URI + "/attribute/{attributesId}")
  @ResponseBody
  public ResponseEntity<?> getAttributes(@PathVariable(name = "attributesId") Integer attributesId) {
    return Optional.ofNullable(Manager.getAttributes(attributesId))
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler
  public String handleBadRequestException(MethodArgumentTypeMismatchException ex) {
    return "Erro: BAD REQUEST - " + ex.getMessage();
  }

  @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
  @ExceptionHandler
  public String handleException(Exception ex) {
    return "Erro: Not Acceptable - " + ex.getMessage();
  }
}

