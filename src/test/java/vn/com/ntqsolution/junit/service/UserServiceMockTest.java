package vn.com.ntqsolution.junit.service;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;
import vn.com.ntqsolution.junit.entity.UserEntity;
import vn.com.ntqsolution.junit.repository.user.UserRepositoryJpa;
import vn.com.ntqsolution.junit.service.user.UserServiceImpl;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserServiceMockTest {

    @Mock
    UserRepositoryJpa userRepositoryJpa;

    @InjectMocks
    UserServiceImpl userService;

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
        Mockito.when(userRepositoryJpa.findAll()).thenReturn(users);
    }

    @Test
    public void countUserTest() {
        Assert.assertEquals(10, userService.findAll().size());
    }

    @Test
    public void findByNameTest1() {
        List<UserEntity> users = userService.findByUsername("1");
        Assert.assertEquals(2, users.size());
    }


}
