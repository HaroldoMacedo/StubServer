package haroldo.stub.manager.resource;

public class Service {
  private final Integer id;
  private static int nextId = 0;
  private String name = "default";
  private String uri;
  private int portNumber = 80;
  private String responseMessage = "{\n  message: \"Hello World!\"\n}";

  public static final Service service0 = new Service("/stub/manager/service/default");

  protected Service() {
    this.id = nextId++;
    init("/uri-" + this.id);
  }

  public Service(String uri) {
    this.id = nextId++;
    init(uri);
  }

  public Service(int id, String uri) {
    if (id > 0)
      this.id = id;
    else
      this.id = nextId++;
    init(uri);
  }

  private void init(String uri) {
    this.uri = uri;
    this.name = "Name " + this.id;
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

  public int getPortNumber() {
    return portNumber;
  }

  public void setPortNumber(int portNumber) {
    this.portNumber = portNumber;
  }

  public String getResponseMessage() {
    return responseMessage;
  }

  public void setResponseMessage(String responseMessage) {
    this.responseMessage = responseMessage;
  }
}
