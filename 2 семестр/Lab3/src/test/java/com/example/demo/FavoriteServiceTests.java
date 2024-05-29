package com.example.demo;

import org.junit.jupiter.api.TestMethodOrder;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.categories.model.CategorieEntity;
import com.example.demo.categories.service.CategorieService;
import com.example.demo.core.errors.NotFoundException;
import com.example.demo.favorites.model.FavoriteEntity;
import com.example.demo.favorites.service.FavoriteService;
import com.example.demo.movies.model.MovieEntity;
import com.example.demo.movies.service.MovieService;
import com.example.demo.users.model.UserEntity;
import com.example.demo.users.service.UserService;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
class FavoriteServiceTests {

    @Autowired
    private FavoriteService favoriteService;

    private FavoriteEntity lastFavorite;
    private FavoriteEntity lastFavorite2;

    private CategorieEntity cat1;
    private MovieEntity mov1;
    private MovieEntity mov2;
    private UserEntity us1;

    @Autowired
    private CategorieService categorieService;

    @Autowired
    private MovieService movieService;

    @Autowired
    private UserService userService;

    @BeforeEach
    void createData() {
        removeData();

        cat1 = categorieService.create(new CategorieEntity("Фэнтэзи", "123"));

        List<CategorieEntity> categories = new ArrayList<>();

        final var categorie1 = categorieService.create(new CategorieEntity(
                "Драма",  "placeholder"));
        final var categorie2 = categorieService.create(new CategorieEntity(
                "Комедия", "placeholder"));


        categories.add(categorie1);
        categories.add(categorie2);
        
        mov1 = movieService.create(new MovieEntity(categories, "Астрал", "1", "1", "1"));
        mov2 = movieService.create(new MovieEntity(categories, "Крепкий орешек","2", "2", "2"));

        us1 = userService.create(new UserEntity("Yourdax", "123", true));

        lastFavorite = favoriteService.create(new FavoriteEntity(us1, mov1));
        lastFavorite2 = favoriteService.create(new FavoriteEntity(us1, mov2));
    }

    @AfterEach
    void removeData() {
        favoriteService.getAll(0).forEach(fv -> favoriteService.delete(fv.getId()));
        userService.getAll().forEach(u -> userService.delete(u.getId()));
        movieService.getAll(0).forEach(item -> movieService.delete(item.getId()));
        categorieService.getAll().forEach(item -> categorieService.delete(item.getId()));
    }

    @Test
    void getTest() {
        Assertions.assertThrows(NotFoundException.class, () -> favoriteService.get(0));
    }

    @Test
    void createTest() {
        Assertions.assertEquals(2, favoriteService.getAll(us1.getId()).size());
    }

    @Test
    void update() {
        final FavoriteEntity fav = favoriteService.create(new FavoriteEntity(us1, mov2));

        final FavoriteEntity oldFav = favoriteService.get(lastFavorite.getId());

        final FavoriteEntity newFav = favoriteService.update(lastFavorite2.getId(), fav);

        Assertions.assertNotEquals(oldFav.getMovie().getName(),
                newFav.getMovie().getName());

    }

    @Test
    @Order(3)
    void delete() {

        favoriteService.delete(lastFavorite.getId());

        Assertions.assertEquals(1, favoriteService.getAll(us1.getId()).size());
    }
}
