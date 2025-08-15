package haroldo.stub0.config.http;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import haroldo.stub0.config.Server;
import haroldo.stub0.config.model.Service;
import haroldo.stub0.config.resource.API;
import haroldo.stub0.config.resource.Attribute;
import haroldo.stub0.config.resource.Resource;
import haroldo.stub0.config.resource.ResourceId;
import haroldo.stub0.config.response.ManagerError;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class HttpServer implements Server {
  private final RestTemplate restTemplate = new RestTemplate();
  private static final String HTTP_HOST = "http://localhost:8080";

  @Override
  public Service getService(int id) {
    ResponseEntity<?> response = getServer("/stub/manager/service/" + id);
    assert (response.getStatusCode() == HttpStatus.OK);
    assert (response.hasBody());

    return (Service) getResource(response, Service.class);
  }

  @Override
  public Attribute getAttribute(int id) {
    ResponseEntity<?> response = getServer("/stub/manager/attribute/" + id);
    assert (response.getStatusCode() == HttpStatus.OK);
    assert (response.hasBody());

    return (Attribute) getResource(response, Attribute.class);
  }

  @Override
  public API getApi(int id) {
    ResponseEntity<?> response = getServer("/stub/manager/api/" + id);
    assert (response.getStatusCode() == HttpStatus.OK);
    assert (response.hasBody());

    return (API) getResource(response, API.class);
  }

  private ResponseEntity<?> getServer(String uri) {
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

  @Override
  public ResourceId startService(int id) {
    // TODO: Implementation needed
    return null;
  }

  @Override
  public ManagerError startServiceBadRequest(int id) {
    // TODO: Implementation needed
    return null;
  }

  @Override
  public ResourceId postApi(API api) {
    // TODO: Implementation needed
    return null;
  }

  @Override
  public ManagerError postApiBadRequest(API api) {
    // TODO: Implementation needed
    return null;
  }

  @Override
  public ResourceId putAttribute(Attribute attribute) {
    // TODO: Implementation needed
    return null;
  }
}
