package vn.com.ntqsolution.junit.repository.user;

import vn.com.ntqsolution.junit.entity.UserEntity;

import java.util.List;

public interface UserRepository {

  List<UserEntity> findByAgeGte(int age);

}
