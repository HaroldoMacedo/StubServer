package haroldo.stub.manager.direct;

import haroldo.stub.manager.GetTest;
import haroldo.stub.manager.implementation.GetTestImpl;
import org.junit.jupiter.api.Test;

public class GetDirectTest implements GetTest {
  private GetTestImpl get = new GetTestImpl(new EmulateServer());


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
