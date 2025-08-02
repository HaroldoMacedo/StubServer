package haroldo.stub.manager.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import haroldo.stub.manager.Server;
import haroldo.stub.manager.model.Service;
import haroldo.stub.manager.resource.API;
import haroldo.stub.manager.resource.Attribute;
import haroldo.stub.manager.resource.Resource;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class ServerCall implements Server {
  private final RestTemplate restTemplate = new RestTemplate();
  private static final String HTTP_HOST = "http://localhost:8080";

  @Override
  public Service getService(int id) {
    ResponseEntity<?> response = callServer("/stub/manager/service/" + id);
    assert (response.getStatusCode() == HttpStatus.OK);
    assert (response.hasBody());

    return (Service) getResource(response, Service.class);
  }

  @Override
  public Attribute getAttribute(int id) {
    ResponseEntity<?> response = callServer("/stub/manager/attribute/" + id);
    assert (response.getStatusCode() == HttpStatus.OK);
    assert (response.hasBody());

    return (Attribute) getResource(response, Attribute.class);
  }

  @Override
  public API getApi(int id) {
    ResponseEntity<?> response = callServer("/stub/manager/api/" + id);
    assert (response.getStatusCode() == HttpStatus.OK);
    assert (response.hasBody());

    return (API) getResource(response, API.class);
  }

  private ResponseEntity<?> callServer(String uri) {
    ResponseEntity<?> response = null;
    try {
      String url = HTTP_HOST + uri;

      var headers = new HttpHeaders();
      var request = new HttpEntity<>(headers);
      String builder = UriComponentsBuilder.fromUriString(url).toUriString();
      response = restTemplate.exchange(builder, HttpMethod.GET, request, String.class);
    } catch (HttpClientErrorException e) {
      System.err.printf("Error calling server: '%s'\n", e.getMessage());
    }

    return response;
  }

  private Resource getResource(ResponseEntity<?> response, Class clazz) {
    String body = response.getBody().toString();
    try {
      return (Resource) new ObjectMapper().readValue(body, clazz);
    } catch (JsonProcessingException e) {
      throw new RuntimeException(e);
    }
  }

}
