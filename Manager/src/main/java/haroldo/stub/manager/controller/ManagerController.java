package haroldo.stub.manager.controller;

import haroldo.stub.manager.model.APIs;
import haroldo.stub.manager.model.APIsException;
import haroldo.stub.manager.resource.Behaviour;
import haroldo.stub.manager.response.ManagerError;
import haroldo.stub.manager.response.ManagerResponse;
import haroldo.stub.manager.resource.Service;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.Optional;

@RestController
public class ManagerController {

  @PostMapping("/stub/manager/service")
  @ResponseBody
  public ResponseEntity<?> postService(@RequestBody Service service) {
    try {
      Service newService = APIs.addService(service);
      return ResponseEntity.ok(new ManagerResponse(service.getId()));
    } catch (APIsException e) {
      System.err.println("Error: " + e.getMessage());
      return ResponseEntity.badRequest().body(new ManagerError(e.getMessage()));
    }
  }

  @PutMapping("/stub/manager/service/{serviceId}/behaviour")
  @ResponseBody
  public ResponseEntity<Behaviour> putServiceBehaviour(@PathVariable(name = "serviceId") int id,
                                                       @RequestParam(name = "responsetime", required = false) Integer responseTimeMS,
                                                       @RequestParam(name = "scalability", required = false) Integer scalability) {
    Behaviour behaviour = APIs.getServiceBehaviour(id);
    if (behaviour == null)
      ResponseEntity.notFound().build();
    behaviour.setResponseTimeMS(responseTimeMS);
    behaviour.setScalability(scalability);

    return ResponseEntity.ok(behaviour);
  }

  @GetMapping("/stub/manager/service/{serviceId}")
  @ResponseBody
  public ResponseEntity<?> getService(@PathVariable(name = "serviceId") Integer id) {
    return Optional.ofNullable(APIs.getService(id))
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @GetMapping("/stub/manager/service/{serviceId}/behaviour")
  @ResponseBody
  public ResponseEntity<?> getBehaviour(@PathVariable(name = "serviceId") Integer id) {
    return Optional.ofNullable(APIs.getServiceBehaviour(id))
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

