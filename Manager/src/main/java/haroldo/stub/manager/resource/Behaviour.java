package haroldo.stub.manager.resource;

public class Behaviour {
  private int responseTimeMS = 100;
  private int scalability = 10;
  private boolean enabled = true;

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

  public boolean isEnabled() {
    return enabled;
  }

  public void setEnabled(boolean enabled) {
    this.enabled = enabled;
  }
}
