package haroldo.stub.manager.response;

public class ManagerResponse {
  private Integer serviceID;

  public ManagerResponse(Integer serviceID) {
    this.serviceID = serviceID;
  }

  public Integer getServiceID() {
    return serviceID;
  }

  public void setServiceID(Integer serviceID) {
    this.serviceID = serviceID;
  }
}
