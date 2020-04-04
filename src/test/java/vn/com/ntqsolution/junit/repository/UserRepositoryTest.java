package vn.com.ntqsolution.junit.repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import vn.com.ntqsolution.junit.entity.UserEntity;
import vn.com.ntqsolution.junit.repository.user.UserRepositoryJpa;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@DataMongoTest(excludeAutoConfiguration = {EmbeddedMongoAutoConfiguration.class})
class UserRepositoryTest extends BaseRepositoryTest {

  @Autowired
  private UserRepositoryJpa userRepository;

  @BeforeEach
  private void setup() {
    UserEntity user = new UserEntity();
    user.setUsername("halab");
    mongoTemplate.insert(user);
  }

  @Test
  void findByUserNameShouldReturnUserWhenUsernameExisted() {
    UserEntity foundUser = userRepository.findByUsername("halab");
    assertEquals("halab", foundUser.getUsername());
  }

  @Test
  void findByUserNameShouldReturnNoUserWhenUsernameNotExisted() {
    UserEntity queryResult = userRepository.findByUsername("apollo");
    assertFalse(queryResult != null);
  }

}
