package haroldo.stub.manager.resource;

public class API implements ResourceId {
  private static int nextId = 0;

  private final Resource resource;
  private String name = "default";
  private String uri;

  protected API() {
    this("/default");
  }

  public API(String uri) {
    this(uri, "api-" + nextId);
  }

  public API(String uri, String name) {
    this.resource = new Resource("api", nextId++);
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
  public Resource getResource() {
    return resource;
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
