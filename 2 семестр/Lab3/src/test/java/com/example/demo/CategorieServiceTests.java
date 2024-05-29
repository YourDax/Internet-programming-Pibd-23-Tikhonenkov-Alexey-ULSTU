package com.example.demo;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import com.example.demo.categories.model.CategorieEntity;
import com.example.demo.categories.service.CategorieService;
import com.example.demo.core.errors.NotFoundException;

@SpringBootTest
class CategorieServiceTests {
    @Autowired
    private CategorieService categorieService;

    private CategorieEntity categorie;

    @BeforeEach
    void createData() {
        removeData();

        categorie = categorieService.create(new CategorieEntity("Ужасы", "null"));
        categorieService.create(new CategorieEntity("Боевик", "null"));

    }

    @AfterEach
    void removeData() {
        categorieService.getAll().forEach(item -> categorieService.delete(item.getId()));
    }

    @Test
    void getTest() {
        Assertions.assertThrows(NotFoundException.class, () -> categorieService.get(0));
    }

    @Test
    void createTest() {
        Assertions.assertEquals(2, categorieService.getAll().size());
        Assertions.assertEquals(categorie, categorieService.get(categorie.getId()));
    }

    @Test
    void createNotUniqueTest() {
        final CategorieEntity nonUniqueCategorie = new CategorieEntity("Ужасы", "null");
        Assertions.assertThrows(IllegalArgumentException.class, () -> categorieService.create(nonUniqueCategorie));
    }

    @Test
    void createNullableTest() {
        final CategorieEntity nullableCategorie = new CategorieEntity(null, "null");
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> categorieService.create(
                nullableCategorie));
    }

    @Test
    void updateTest() {
        final String newName = "TEST";
        final String oldName = categorie.getName();
        final CategorieEntity cat = new CategorieEntity(newName, categorie.getImage());
        final CategorieEntity newEntity = categorieService.update(categorie.getId(), cat);
        Assertions.assertEquals(newName, newEntity.getName());
        Assertions.assertNotEquals(oldName, newEntity.getName());
    }

    @Test
    void deleteTest() {
        categorieService.delete(categorie.getId());
        Assertions.assertEquals(1, categorieService.getAll().size());

        Assertions.assertNotEquals(3, categorieService.getAll().size());
    }
}
