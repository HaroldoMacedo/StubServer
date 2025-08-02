package haroldo.stub.manager.response;

import haroldo.stub.manager.resource.ResourceId;

public class ManagerResponse {
  private final ResourceId resourceId;

  public ManagerResponse(ResourceId resourceId) {
    this.resourceId = resourceId;
  }

  public ResourceId getResourceId() {
    return resourceId;
  }
}

