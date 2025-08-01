package haroldo.stub.manager.resource;

public class API {
  private final Integer id;
  private static int nextId = 0;
  private String name = "default";
  private String uri;

  protected API() {
    this("/default");
  }

  public API(String uri) {
    this(uri, "api-" + nextId);
  }

  public API(String uri, String name) {
    this.id = nextId++;
    this.uri = uri;
    this.name = name;
  }

  public Integer getId() {
    return id;
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
}
