package com.example.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.core.error.NotFoundException;
import com.example.demo.types.model.TypeEntity;
import com.example.demo.types.service.TypeService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class TypeServiceTests {
	@Autowired
	private TypeService typeService;

	@Test
	void getTest() {
		Assertions.assertThrows(NotFoundException.class, () -> typeService.get(0L));
	}

	@Test
	@Order(1)
	void createTest() {
		typeService.deleteAll();

		typeService.create(new TypeEntity(null, "Фильм"));
		final TypeEntity last = typeService.create(new TypeEntity(null, "Сериал"));

		Assertions.assertEquals(2, typeService.getAll().size());
		Assertions.assertEquals(last, typeService.get(2L));
	}

	@Test
	@Order(2)
	void updateTest() {
		final String test = "TEST";
		final TypeEntity entity = typeService.get(2L);
		final String oldName = entity.getName();
		final TypeEntity newEntity = typeService.update(2L, new TypeEntity(1L, test));

		Assertions.assertEquals(2, typeService.getAll().size());
		Assertions.assertEquals(newEntity, typeService.get(2L));
		Assertions.assertEquals(test, newEntity.getName());
		Assertions.assertNotEquals(oldName, newEntity.getName());
	}

	@Test
	@Order(3)
	void deleteTest() {
		typeService.delete(2L);
		Assertions.assertEquals(1, typeService.getAll().size());
		final TypeEntity last = typeService.get(1L);
		Assertions.assertEquals(1L, last.getId());

		final TypeEntity newEntity = typeService.create(new TypeEntity(null, "Сериал"));
		Assertions.assertEquals(2, typeService.getAll().size());
		Assertions.assertEquals(3L, newEntity.getId());
	}
}
