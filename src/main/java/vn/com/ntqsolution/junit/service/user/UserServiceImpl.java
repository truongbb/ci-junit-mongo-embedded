package vn.com.ntqsolution.junit.service.user;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.com.ntqsolution.junit.entity.UserEntity;
import vn.com.ntqsolution.junit.repository.user.UserRepositoryJpa;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserServiceImpl implements UserService {

  @Autowired
  UserRepositoryJpa userRepositoryJpa;

  @Override
  public List<UserEntity> findAll() {
    return userRepositoryJpa.findAll();
  }

  @Override
  public List<UserEntity> findByUsername(String username) {
    List<UserEntity> users = findAll();
    return users.stream().filter(user -> user.getUsername().trim().contains(username)).collect(Collectors.toList());
  }

}
