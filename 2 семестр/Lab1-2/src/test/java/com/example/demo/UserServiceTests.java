package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import com.example.demo.ageratings.service.AgeRatingService;
import com.example.demo.core.error.NotFoundException;
import com.example.demo.movies.service.MovieService;
import com.example.demo.types.service.TypeService;
import com.example.demo.users.model.UserEntity;
import com.example.demo.users.service.UserService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class UserServiceTests {
	@Autowired
	private TypeService typeService;

	@Autowired
	private AgeRatingService ageRatingService;

	@Autowired
	private MovieService movieService;

	@Autowired
	private UserService userService;

	@Test
	void getTest() {
		Assertions.assertThrows(NotFoundException.class, () -> userService.get(0L));
	}

	@Test
	@Order(1)
	void createTest() {
		ageRatingService.deleteAll();
		typeService.deleteAll();
		movieService.deleteAll();
		userService.deleteAll();

		userService.create(new UserEntity(null, "myemail@mail.com", "12345678"));
		userService.create(new UserEntity(null, "secondemail@mail.com", "qwwerty"));
		final var last = userService
				.create(new UserEntity(null, "thirdemail@mail.com", "ytrewq"));

		Assertions.assertEquals(3, userService.getAll().size());
		Assertions.assertEquals(last, userService.get(3L));
	}

	@Test
	@Order(2)
	void updateTest() {
        final String test = "TEST";
        final String email = "TEST";
        final String password = "***REMOVED***";
		final UserEntity entity = userService.get(3L);
		final String oldName = entity.getEmail();
		final UserEntity newEntity = userService.update(3L, new UserEntity(1L,email, password));
		Assertions.assertEquals(3, userService.getAll().size());
		Assertions.assertEquals(newEntity, userService.get(3L));
		Assertions.assertEquals(test, newEntity.getEmail());
		Assertions.assertNotEquals(oldName, newEntity.getEmail());
	}

	@Test
	@Order(3)
	void deleteTest() {
		userService.delete(3L);
		Assertions.assertEquals(2, userService.getAll().size());
		final UserEntity last = userService.get(2L);
		Assertions.assertEquals(2L, last.getId());

		final var newEntity = userService
				.create(new UserEntity(null, "thirdemail@mail.com", "ytrewq"));
		Assertions.assertEquals(3, userService.getAll().size());
		Assertions.assertEquals(4L, newEntity.getId());
	}
}
