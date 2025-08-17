package haroldo.stub.api.resource;

public class ResourceId {
  private final Integer id;
  private final String resourceName;
  private final String hyperlink;

  public ResourceId() {
    this.id = 0;
    this.resourceName = "default";
    this.hyperlink = "/default";
  }

  public ResourceId(String resourceName, String uri, int id) {
    this.id = id;
    this.resourceName = resourceName;
    this.hyperlink = uri + "/" + id;
  }

  public final String getResourceName(){
    return resourceName;
  }

  public final Integer getId(){
    return id;
  }

  public String getHyperlink(){
    return hyperlink;
  }

  @Override
  public String toString() {
      return "{\n" +
              "\"name\": " + resourceName + ",\n" +
              "\"id\": " + id + ",\n" +
              "\"link\": " + hyperlink + ",\n" +
              "}";
  }

  @Override
  public boolean equals(Object obj) {
    if (!(obj instanceof ResourceId))
      return false;

    return id.equals(((ResourceId) obj).id);
  }

  @Override
  public int hashCode() {
    return id;
  }

}
