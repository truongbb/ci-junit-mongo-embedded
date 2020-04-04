package vn.com.ntqsolution.junit.web.rest;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import vn.com.ntqsolution.junit.entity.UserEntity;
import vn.com.ntqsolution.junit.service.user.UserService;

import java.util.List;

@RestController
@RequestMapping("/user")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResource {

  @Autowired
  UserService userService;

  @GetMapping("/all")
  public ResponseEntity<List<UserEntity>> getAll() {
    return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
  }

  @GetMapping("/count-all")
  public ResponseEntity<Integer> countAll() {
    return new ResponseEntity<>(Integer.valueOf(userService.findAll().size()), HttpStatus.OK);
  }

  @GetMapping("/find-by-username")
  public ResponseEntity<List<UserEntity>> findByUsername(@RequestParam("username") String username) {
    return new ResponseEntity<>(userService.findByUsername(username), HttpStatus.OK);
  }

}
