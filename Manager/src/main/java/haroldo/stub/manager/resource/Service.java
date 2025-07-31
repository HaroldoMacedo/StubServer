package haroldo.stub.manager.resource;

public class Service {
  private int id;
  private String name = "default";
  private String uri = "/stub/manager/service/default";
  private int portNumber = 80;
  private int responseTimeMS = 100;
  private int scalability = 10;
  private String responseMessage = "Hello World!";

  public static final Service service0 = new Service();

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
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

  public int getResponseTimeMS() {
    return responseTimeMS;
  }

  public void setResponseTimeMS(int responseTimeMS) {
    this.responseTimeMS = responseTimeMS;
  }

  public int getScalability() {
    return scalability;
  }

  public void setScalability(int scalability) {
    this.scalability = scalability;
  }

  public String getResponseMessage() {
    return responseMessage;
  }

  public void setResponseMessage(String responseMessage) {
    this.responseMessage = responseMessage;
  }
}
