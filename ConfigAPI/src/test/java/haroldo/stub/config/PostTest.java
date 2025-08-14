package haroldo.stub.config;

import haroldo.stub.config.resource.API;
import haroldo.stub.config.resource.Attribute;
import haroldo.stub.config.resource.ResourceId;
import haroldo.stub.config.response.ManagerError;
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
