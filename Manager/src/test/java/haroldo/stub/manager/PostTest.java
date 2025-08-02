package haroldo.stub.manager;

import haroldo.stub.manager.resource.API;
import haroldo.stub.manager.resource.Attribute;
import haroldo.stub.manager.resource.ResourceId;
import haroldo.stub.manager.response.ManagerError;
import org.junit.jupiter.api.Test;

public interface PostTest {
  public void startServiceTest() ;
  public void startUnexistingServiceTest();
  public void startImpossibleIdServiceTest();
  public void addApi0Test();
  public void addApiTest();
  public void add10ApisTest();
  public void addDuplicatedApiTest();
  public void addAttributeTest();
  public void addDuplicatedAttributeTest();
}
