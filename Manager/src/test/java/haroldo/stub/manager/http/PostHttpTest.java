package haroldo.stub.manager.http;

import haroldo.stub.manager.PostTest;
import haroldo.stub.manager.direct.EmulateServer;
import haroldo.stub.manager.implementation.GetTestImpl;
import haroldo.stub.manager.implementation.PostTestImpl;
import haroldo.stub.manager.model.Manager;
import org.junit.jupiter.api.Test;

public class PostHttpTest implements PostTest {
  private PostTestImpl post = new PostTestImpl(new ServerCall());

  PostHttpTest() {
    new Manager();  // Instatiated to create initial components Id = 0.
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
