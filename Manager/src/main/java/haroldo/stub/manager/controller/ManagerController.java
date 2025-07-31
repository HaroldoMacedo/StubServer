package haroldo.stub.manager.controller;

import haroldo.stub.manager.model.APIs;
import haroldo.stub.manager.resource.Behaviour;
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
  public ResponseEntity<Integer> putService(@RequestBody Service service) {
    Service newService = APIs.addOrReplace(service);
    if (newService == null)
      return ResponseEntity.badRequest().build();

    return ResponseEntity.ok(newService.getId());
  }

  @GetMapping("/stub/manager/service/{serviceId}")
  @ResponseBody
  public ResponseEntity<Service> getService(@PathVariable(name = "serviceId") Integer id) {
    return Optional.ofNullable(APIs.getService(id))
            .map(ResponseEntity::ok)
            .orElseGet(() -> ResponseEntity.notFound().build());
  }

  @PutMapping("/stub/manager/service/{serviceId}/behaviour")
  @ResponseBody
  public ResponseEntity<Behaviour> putServiceBehaviour(@PathVariable(name = "serviceId") int id,
                                                       @RequestParam(name = "responsetime", required = false) Integer responseTimeMS,
                                                       @RequestParam(name = "scalability", required = false) Integer scalability) {
    Behaviour behaviour = APIs.getBehaviour(id);
    if (behaviour == null)
      ResponseEntity.notFound().build();
    behaviour.setResponseTimeMS(responseTimeMS);
    behaviour.setScalability(scalability);

    return ResponseEntity.ok(behaviour);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler
  public String handleBadRequestException(MethodArgumentTypeMismatchException ex) {
    return "Erro: BAD REQUEST - " + ex.getMessage();
  }

  @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
  @ExceptionHandler
  public String handleException(Exception ex) {
    return "Erro: BAD REQUEST - " + ex.getMessage();
  }

}

