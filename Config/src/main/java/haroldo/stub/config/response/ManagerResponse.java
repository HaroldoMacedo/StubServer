package haroldo.stub.config.response;

import haroldo.stub.config.resource.Resource;

public class ManagerResponse {
  private final Resource resource;

  public ManagerResponse(Resource resource) {
    this.resource = resource;
  }

  public Resource getResource() {
    return resource;
  }
}

