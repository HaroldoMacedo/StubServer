package haroldo.stub0.config.resource;

public class Attribute implements Resource {
  private static int nextId = 0;

  private final ResourceId resourceId;
  public static final String ATTRIBUTES_DEFAULT_MESSAGE = "{message: Hello World!}";
  private int portNumber = 80;
  private String responseMessage = ATTRIBUTES_DEFAULT_MESSAGE;
  private int responseTimeMS = 100;
  private int scalability = 10;

  public Attribute() {
    this.resourceId = new ResourceId("attribute", nextId++);
  }

  //  public Attribute(int id) throws Exception {
//    super("attribute", id);
//    if (id <= 0)
//      throw new Exception("");
//  }
//
  @Override
  public ResourceId getResourceId() {
    return resourceId;
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

  @Override
  public boolean equals(Object obj) {
    return resourceId.equals(obj);
  }

  @Override
  public int hashCode() {
    return resourceId.hashCode();
  }
}
