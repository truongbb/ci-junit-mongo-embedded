package vn.com.ntqsolution.junit.repository.user;

import org.springframework.data.mongodb.repository.MongoRepository;
import vn.com.ntqsolution.junit.entity.UserEntity;

public interface UserRepositoryJpa extends MongoRepository<UserEntity, Integer> {

  UserEntity findByUsername(String username);

  UserEntity findById(String id);

}
