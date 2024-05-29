package com.example.demo;

import com.example.demo.core.errors.NotFoundException;
import com.example.demo.users.model.UserEntity;
import com.example.demo.users.repository.UserRepository;
import com.example.demo.users.service.UserService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserService userService;

    @Test
    public void testCreateUser() {
        UserEntity user = new UserEntity("testUser", "password123");
        when(userRepository.save(any(UserEntity.class))).thenReturn(user);
        when(passwordEncoder.encode(anyString())).thenReturn("encodedPassword");

        UserEntity createdUser = userService.create(user);

        assertNotNull(createdUser);
        assertEquals("testUser", createdUser.getLogin());
        assertEquals("encodedPassword", createdUser.getPassword());
        verify(userRepository, times(2)).save(any(UserEntity.class));
    }

    @Test
    public void testGetUserNotFound() {
        when(userRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(NotFoundException.class, () -> userService.get(1L));
    }
}
