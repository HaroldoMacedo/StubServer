package haroldo.stub.config.emulate;

import haroldo.stub.config.PostTest;
import haroldo.stub.config.implementation.PostTestImpl;
import haroldo.stub.config.model.Manager;
import org.junit.jupiter.api.Test;

public class PostDirectTest implements PostTest {
  private PostTestImpl post = new PostTestImpl(new EmulateServer());

  PostDirectTest() {
    //  To load the class before SpringBoot
    //  to create initial components with Id = 0.
    new Manager();
  }

  @Test
  @Override
  public void startServiceTest() {
    post.startServiceTest();
  }

  @Test
  @Override
  public void startUnexistingServiceTest() {
    post.startUnexistingServiceTest();
  }

  @Test
  @Override
  public void startImpossibleIdServiceTest() {
    post.startImpossibleIdServiceTest();
  }

  @Test
  @Override
  public void addApi0Test() {
    post.addApi0Test();
  }

  @Test
  @Override
  public void addApiTest() {
    post.addApiTest();
  }

  @Test
  @Override
  public void add10ApisTest() {
    post.add10ApisTest();
  }

  @Test
  @Override
  public void addDuplicatedApiTest() {
    post.addDuplicatedApiTest();
  }

  @Test
  @Override
  public void addAttributeTest() {
    post.addAttributeTest();
  }

  @Test
  @Override
  public void addDuplicatedAttributeTest() {
    post.addDuplicatedAttributeTest();
  }

}
