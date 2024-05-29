package com.example.demo;

import org.junit.jupiter.api.Test;

import com.example.demo.users.model.UserEntity;
import com.example.demo.users.model.UserRole;

import static org.junit.jupiter.api.Assertions.*;

public class UserEntityTest {

    @Test
    public void testUserEntityEqualsAndHashCode() {
        UserEntity user1 = new UserEntity("testUser", "password123");
        user1.setRole(UserRole.ADMIN);

        UserEntity user2 = new UserEntity("testUser", "password123");
        user2.setRole(UserRole.ADMIN);

        assertEquals(user1, user2);
        assertEquals(user1.hashCode(), user2.hashCode());

        user2.setRole(UserRole.USER);
        assertNotEquals(user1, user2);
        assertNotEquals(user1.hashCode(), user2.hashCode());
    }

    @Test
    public void testUserEntitySettersAndGetters() {
        UserEntity user = new UserEntity();
        user.setLogin("testUser");
        user.setPassword("password123");
        user.setRole(UserRole.ADMIN);

        assertEquals("testUser", user.getLogin());
        assertEquals("password123", user.getPassword());
        assertEquals(UserRole.ADMIN, user.getRole());
    }
}
