package haroldo.stub.manager.resource;

public class Attributes {
  public static final String ATTRIBUTES_DEFAULT_MESSAGE = "{message: Hello World!}";
  private final Integer id;
  private static int nextId = 0;

  private int portNumber = 80;
  private String responseMessage = ATTRIBUTES_DEFAULT_MESSAGE;
  private int responseTimeMS = 100;
  private int scalability = 10;

  public Attributes() {
    this.id = nextId++;
  }

  public Attributes(int id) {
    if (id > 0)
      this.id = id;
    else
      this.id = nextId++;
  }

  public Integer getId() {
    return id;
  }

  public String getResponseMessage() {
    return responseMessage;
  }

  public void setResponseMessage(String responseMessage) {
    this.responseMessage = responseMessage;
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

  public void setResponseTimeMS(Integer responseTimeMS) {
    if (responseTimeMS == null) return;
    this.responseTimeMS = responseTimeMS;
  }

  public int getScalability() {
    return scalability;
  }

  public void setScalability(Integer scalability) {
    if (scalability == null) return;
    this.scalability = scalability;
  }
}
