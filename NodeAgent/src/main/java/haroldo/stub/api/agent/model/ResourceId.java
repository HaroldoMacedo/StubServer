package haroldo.stub.api.agent.model;

public class ResourceId {
  private final Integer id;
  private final String hyperlink;

  public ResourceId() {
    this.id = 0;
    this.hyperlink = "/default";
  }

  public ResourceId(String uri, int id) {
    this.id = id;
    this.hyperlink = uri + "/" + id;
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
