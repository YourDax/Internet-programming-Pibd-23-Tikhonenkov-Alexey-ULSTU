package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.ageratings.model.AgeRatingEntity;
import com.example.demo.ageratings.service.AgeRatingService;
import com.example.demo.core.error.NotFoundException;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class AgeRatingServiceTests {
	@Autowired
	private AgeRatingService ageRatingService;

	@Test
	void getTest() {
		Assertions.assertThrows(NotFoundException.class, () -> ageRatingService.get(0L));
	}

	@Test
	@Order(1)
	void createTest() {
		ageRatingService.deleteAll();

		ageRatingService.create(new AgeRatingEntity(null, "12+"));
		ageRatingService.create(new AgeRatingEntity(null, "16+"));
		final AgeRatingEntity last = ageRatingService.create(new AgeRatingEntity(null, "18+"));

		Assertions.assertEquals(3, ageRatingService.getAll().size());
		Assertions.assertEquals(last, ageRatingService.get(3L));
	}

	@Test
	@Order(2)
	void updateTest() {
		final String test = "TEST";
		final AgeRatingEntity entity = ageRatingService.get(3L);
		final String oldName = entity.getName();
		final AgeRatingEntity newEntity = ageRatingService.update(3L, new AgeRatingEntity(2L, test));

		Assertions.assertEquals(3, ageRatingService.getAll().size());
		Assertions.assertEquals(newEntity, ageRatingService.get(3L));
		Assertions.assertEquals(test, newEntity.getName());
		Assertions.assertNotEquals(oldName, newEntity.getName());
	}

	@Test
	@Order(3)
	void deleteTest() {
		ageRatingService.delete(3L);
		Assertions.assertEquals(2, ageRatingService.getAll().size());
		final AgeRatingEntity last = ageRatingService.get(2L);
		Assertions.assertEquals(2L, last.getId());

		final AgeRatingEntity newEntity = ageRatingService.create(new AgeRatingEntity(null, "18+"));
		Assertions.assertEquals(3, ageRatingService.getAll().size());
		Assertions.assertEquals(4L, newEntity.getId());
	}
}
