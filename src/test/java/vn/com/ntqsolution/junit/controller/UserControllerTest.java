package vn.com.ntqsolution.junit.controller;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import vn.com.ntqsolution.junit.entity.UserEntity;
import vn.com.ntqsolution.junit.service.user.UserService;
import vn.com.ntqsolution.junit.web.rest.UserResource;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RunWith(SpringRunner.class)
@WebMvcTest(UserResource.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  UserService userService;

  @Before
  public void setup() {
    List<UserEntity> users = new ArrayList<>();

    for (int i = 1; i <= 10; i++) {
      UserEntity userEntity = new UserEntity();
      userEntity.setUserId(i + "");
      userEntity.setUsername("user" + i);
      userEntity.setPassword("123456" + i);
      users.add(userEntity);
    }
    Mockito.when(userService.findAll()).thenReturn(users);

    Mockito.when(userService.findByUsername("5"))
      .thenReturn(
        users.stream()
          .filter(user -> user.getUsername().trim().contains("5"))
          .collect(Collectors.toList())
      );
  }

  @Test
  public void countUserTest() throws Exception {
    this.mockMvc.
      perform(MockMvcRequestBuilders.get("/user/count-all").accept(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.content().string("10"));
    //      .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.is(5)));
  }

  @Test
  public void findByUsernameTest() throws Exception {
    this.mockMvc.
      perform(MockMvcRequestBuilders.get("/user/find-by-username?username=5").accept(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
      .andExpect(MockMvcResultMatchers.jsonPath("$[0].userId", Matchers.is("5")));
  }

  @Test
  public void findAll() throws Exception {
    String expectedJson = "[" +
      "{\"id\":null,\"userId\":\"1\",\"username\":\"user1\",\"password\":\"1234561\",\"bir\":null}," +
      "{\"id\":null,\"userId\":\"2\",\"username\":\"user2\",\"password\":\"1234562\",\"bir\":null}," +
      "{\"id\":null,\"userId\":\"3\",\"username\":\"user3\",\"password\":\"1234563\",\"bir\":null}," +
      "{\"id\":null,\"userId\":\"4\",\"username\":\"user4\",\"password\":\"1234564\",\"bir\":null}," +
      "{\"id\":null,\"userId\":\"5\",\"username\":\"user5\",\"password\":\"1234565\",\"bir\":null}," +
      "{\"id\":null,\"userId\":\"6\",\"username\":\"user6\",\"password\":\"1234566\",\"bir\":null}," +
      "{\"id\":null,\"userId\":\"7\",\"username\":\"user7\",\"password\":\"1234567\",\"bir\":null}," +
      "{\"id\":null,\"userId\":\"8\",\"username\":\"user8\",\"password\":\"1234568\",\"bir\":null}," +
      "{\"id\":null,\"userId\":\"9\",\"username\":\"user9\",\"password\":\"1234569\",\"bir\":null}," +
      "{\"id\":null,\"userId\":\"10\",\"username\":\"user10\",\"password\":\"12345610\",\"bir\":null}" +
      "]";

    this.mockMvc.
      perform(MockMvcRequestBuilders.get("/user/all").accept(MediaType.APPLICATION_JSON))
      .andExpect(MockMvcResultMatchers.status().isOk())
      .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(10)))
      .andExpect(MockMvcResultMatchers.content().json(expectedJson));
  }

}
