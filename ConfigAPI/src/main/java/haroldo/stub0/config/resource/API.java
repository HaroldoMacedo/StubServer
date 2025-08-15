package haroldo.stub0.config.resource;

public class API implements Resource {
  private static int nextId = 0;

  private final ResourceId resourceId;
  private String name = "default";
  private String uri;

  protected API() {
    this("/default");
  }

  public API(String uri) {
    this(uri, "api-" + nextId);
  }

  public API(String uri, String name) {
    this.resourceId = new ResourceId("api", nextId++);
    this.uri = uri;
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUri() {
    return uri;
  }

  public void setUri(String uri) {
    this.uri = uri;
  }

  @Override
  public ResourceId getResourceId() {
    return resourceId;
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof API api))
      return false;
    return this.uri.equals(api.uri);
  }

  @Override
  public int hashCode() {
    return uri.hashCode();
  }
}
