package haroldo.stub0.config.http;

import haroldo.stub0.config.PostTest;
import haroldo.stub0.config.implementation.PostTestImpl;
import haroldo.stub0.config.model.Manager;
import org.junit.jupiter.api.Test;

public class PostHttpTest implements PostTest {
  private PostTestImpl post = new PostTestImpl(new HttpServer());

  PostHttpTest() {
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
