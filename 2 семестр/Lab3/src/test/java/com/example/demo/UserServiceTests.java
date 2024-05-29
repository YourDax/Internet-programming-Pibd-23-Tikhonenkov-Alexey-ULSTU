package com.example.demo;

import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.core.errors.NotFoundException;
import com.example.demo.users.model.UserEntity;
import com.example.demo.users.service.UserService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class UserServiceTests {

    @Autowired
    private UserService userService;

    private UserEntity firstUser;

    @BeforeEach
    void createData() {
        removeData();

        firstUser = userService.create(new UserEntity("Yourdax", "123", true));
        userService.create(new UserEntity("Ivan", "789", false));
        userService.create(new UserEntity("Nikita", "456", false));

    }

    @AfterEach
    void removeData() {
        userService.getAll().forEach(item -> userService.delete(item.getId()));
    }

    @Test
    void getTest() {
        Assertions.assertThrows(NotFoundException.class, () -> userService.get(0));
    }

    @Test
    @Order(1)
    void createTest() {

        Assertions.assertEquals(firstUser, userService.get(firstUser.getId()));
        Assertions.assertEquals(3, userService.getAll().size());
    }

    @Test
    @Order(2)
    void update() {
        final String newPassword = "000";
        final UserEntity existEntity = userService.get(firstUser.getId());
        final String oldPassword = existEntity.getPassword();
        final UserEntity entity = new UserEntity(existEntity.getUsername(), newPassword,
                existEntity.getIsAdmin());
        final UserEntity newEntity = userService.update(firstUser.getId(), entity);
        Assertions.assertEquals(newPassword, newEntity.getPassword());
        Assertions.assertNotEquals(oldPassword, newEntity.getPassword());

    }

    @Test
    @Order(3)
    void delete() {
        userService.delete(firstUser.getId());
        Assertions.assertEquals(2, userService.getAll().size());
    }
}
