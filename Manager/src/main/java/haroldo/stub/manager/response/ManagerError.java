package haroldo.stub.manager.response;

public class ManagerError {
  private int code;
  private String message;

  public ManagerError(String message) {
    code = 0;
    this.message = message;
  }

  public int getErrorCode() {
    return code;
  }

  public void setCode(int code) {
    this.code = code;
  }

  public String getErrorMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
