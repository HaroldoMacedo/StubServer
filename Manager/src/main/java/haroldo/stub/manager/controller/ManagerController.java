package haroldo.stub.manager.controller;

import haroldo.stub.manager.model.Manager;
import haroldo.stub.manager.model.Service;
import haroldo.stub.manager.model.ServicesException;
import haroldo.stub.manager.response.ManagerError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Optional;

@RestController
public class ManagerController {
  public static final String BASE_URI = "/stub/manager";

  @PutMapping(BASE_URI + "/service/{serviceId}")
  @ResponseBody
  public ResponseEntity<?> startService(@PathVariable(name = "serviceId") int serviceId) {
    try {
      Manager.startService(serviceId);
      return ResponseEntity.ok("{status: \"OK\"");
    } catch (ServicesException e) {
      System.err.println("Error: " + e.getMessage());
      return ResponseEntity.badRequest().body(new ManagerError(e.getMessage()));
    }
  }
//
//  @PostMapping(BASE_URI + "/service")
//  @ResponseBody
//  public ResponseEntity<?> postService(@RequestBody Service service) {
//    try {
//      Manager.addService(service.getApi(), service.getAttributes());
//      return ResponseEntity.ok(new ManagerResponse(serviceId));
//    } catch (ServicesException e) {
//      System.err.println("Error: " + e.getMessage());
//      return ResponseEntity.badRequest().body(new ManagerError(e.getMessage()));
//    }
//  }


//  @PutMapping(BASE_URI + "/attributes/{attributesId}")
//  @ResponseBody
//  public ResponseEntity<Integer> putAttributes(@PathVariable(name = "attributesId") int attributesId,
//                                                @RequestBody Atributes atributes) {
//    if (atributes == null)
//      ResponseEntity.notFound().build();
//    Integer id = Manager.addAttributes(atributes);
//    return ResponseEntity.ok(id);
//  }

  @GetMapping(BASE_URI + "/api/{apiId}")
  @ResponseBody
  public ResponseEntity<?> getService(@PathVariable(name = "apiId") Integer apiId) {
    return Optional.ofNullable(Manager.getService(apiId))
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping(BASE_URI + "/attributes/{attributesId}")
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

