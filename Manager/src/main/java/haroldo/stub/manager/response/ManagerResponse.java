package haroldo.stub.manager.response;

import haroldo.stub.manager.resource.Resource;

public class ManagerResponse {
  private final Resource resource;

  public ManagerResponse(Resource resource) {
    this.resource = resource;
  }

  public Resource getResource() {
    return resource;
  }
}

