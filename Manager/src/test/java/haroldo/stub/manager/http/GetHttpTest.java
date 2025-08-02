package haroldo.stub.manager.http;

import haroldo.stub.manager.GetTest;
import haroldo.stub.manager.implementation.GetTestImpl;
import org.junit.jupiter.api.Test;

public class GetHttpTest implements GetTest {
  private GetTestImpl get = new GetTestImpl(new ServerCall());

  @Test
  @Override
  public void getServiceTest() {
    get.getServiceTest();
  }

  @Test
  @Override
  public void getAttributeTest() {
    get.getAttributeTest();
  }

  @Test
  @Override
  public void getApiTest() {
    get.getApiTest();
  }

}
