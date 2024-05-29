package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;

import com.example.demo.categories.model.CategorieEntity;
import com.example.demo.categories.service.CategorieService;
import com.example.demo.core.errors.NotFoundException;
import com.example.demo.viewed.model.ViewedEntity;
import com.example.demo.movies.model.MovieEntity;
import com.example.demo.movies.service.MovieService;
import com.example.demo.users.model.UserEntity;
import com.example.demo.users.service.UserService;
import com.example.demo.viewed.service.ViewedService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class MovieServiceTest {
    @Autowired
    private MovieService movieService;

    private MovieEntity movie;
    private MovieEntity movie2;

    @Autowired
    private CategorieService categorieService;

    private CategorieEntity cat1;
    private CategorieEntity cat2;

    @Autowired
    private UserService userService;

    private UserEntity user;
    private UserEntity user1;
    private UserEntity user2;
    private UserEntity user3;

    @Autowired
    private ViewedService viewedService;

    @BeforeEach
    void createData() {
        removeData();
        List<CategorieEntity> categories = new ArrayList<>();
        cat1 = categorieService.create(new CategorieEntity("Drama", "null"));
        cat2 = categorieService.create(new CategorieEntity("Boevik", "null"));

        categories.add(cat1);
        categories.add(cat2);
        
        user = userService.create(new UserEntity("1", "1", true));
        user1 = userService.create(new UserEntity("2", "2", true));
        user2 = userService.create(new UserEntity("3", "3", true));
        user3 = userService.create(new UserEntity("4", "4", true));

        movie = movieService.create(new MovieEntity(categories, "1", "1", "1", "1"));
        movie2 = movieService.create(new MovieEntity(categories, "2", "2", "2", "2"));

        viewedService.create(new ViewedEntity(user, movie));
        viewedService.create(new ViewedEntity(user1, movie));
        viewedService.create(new ViewedEntity(user2, movie));
        viewedService.create(new ViewedEntity(user3, movie));

    }

    @AfterEach
    void removeData() {
        viewedService.getAll(0).forEach(vs -> viewedService.delete(vs.getId()));
        userService.getAll().forEach(u -> userService.delete(u.getId()));
        movieService.getAll(0).forEach(item -> movieService.delete(item.getId()));
        categorieService.getAll().forEach(item -> categorieService.delete(item.getId()));
    }
    @Test
    @Order(1)
    void countCategorieTest() {
        assertEquals(2, movie.getCategories().size());
    }

    @Test
    @Order(1)
    void getTest() {
        Assertions.assertThrows(NotFoundException.class, () -> movieService.get(10));
    }

    @Test
    @Order(2)
    void createTest() {
        Assertions.assertEquals(2, movieService.getAll(cat1.getId()).size());
        
    }

    @Test
    @Order(3)
    void createNotUniqueTest() {
        List<CategorieEntity> categories = new ArrayList<>();

        categories.add(cat1);
        final MovieEntity nonUniqueCategorie = new MovieEntity(categories, "1", "1", "1",
                "1");
        Assertions.assertThrows(IllegalArgumentException.class, () -> movieService.create(nonUniqueCategorie));
    }

    @Test
    @Order(4)
    void createNullableTest() {
        List<CategorieEntity> categories = new ArrayList<>();
        categories.add(cat1);
        final MovieEntity nullableCategorie = new MovieEntity(categories, null, "1", "1",
                "1");
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> movieService.create(
                nullableCategorie));
    }

    @Test
    @Order(5)
    void updateTest() {
        final String newName = "TEST";
        final String oldName = movie.getName();
        final MovieEntity mov = new MovieEntity(movie.getCategories(),
                newName, movie.getDescription(),
                movie.getDuration(), movie.getImage());
        final MovieEntity newEntity = movieService.update(movie.getId(), mov);
        Assertions.assertEquals(newName, newEntity.getName());
        Assertions.assertNotEquals(oldName, newEntity.getName());
    }

    @Test
    @Order(6)
    void deleteTest() {
        movieService.delete(movie2.getId());
        Assertions.assertEquals(1, movieService.getAll(cat1.getId()).size());

        Assertions.assertNotEquals(3, movieService.getAll(cat1.getId()).size());
    }
}
