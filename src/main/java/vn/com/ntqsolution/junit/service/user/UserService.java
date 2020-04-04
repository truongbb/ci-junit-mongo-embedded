package vn.com.ntqsolution.junit.service.user;

import vn.com.ntqsolution.junit.entity.UserEntity;

import java.util.List;

public interface UserService {

  List<UserEntity> findAll();

  List<UserEntity> findByUsername(String username);

}
