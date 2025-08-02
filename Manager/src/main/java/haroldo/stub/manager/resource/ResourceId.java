package haroldo.stub.manager.resource;

public class ResourceId {
  private static final String BASE_URI = "/stub/manager/";
  private final Integer id;
  private final String resourceName;
  private final String hyperlink;

  public ResourceId() {
    this.id = 0;
    this.resourceName = "default";
    this.hyperlink = "/default";
  }

  public ResourceId(String resourceName, int id) {
    this.id = id;
    this.resourceName = resourceName;
    this.hyperlink = BASE_URI + resourceName + "/" + id;
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
