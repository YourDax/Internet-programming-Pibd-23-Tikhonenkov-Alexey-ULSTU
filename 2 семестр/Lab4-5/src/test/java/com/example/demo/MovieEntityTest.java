package com.example.demo;

import org.junit.jupiter.api.Test;

import com.example.demo.categories.model.CategorieEntity;
import com.example.demo.movies.model.MovieEntity;

import static org.junit.jupiter.api.Assertions.*;

public class MovieEntityTest {

    @Test
    public void testMovieEntityEqualsAndHashCode() {
        CategorieEntity category = new CategorieEntity("Action", "image.jpg");

        MovieEntity movie1 = new MovieEntity(category, "Test Movie", "Description", "120 min", "image.jpg");
        MovieEntity movie2 = new MovieEntity(category, "Test Movie", "Description", "120 min", "image.jpg");

        assertEquals(movie1, movie2);
        assertEquals(movie1.hashCode(), movie2.hashCode());

        movie2.setName("Another Movie");
        assertNotEquals(movie1, movie2);
        assertNotEquals(movie1.hashCode(), movie2.hashCode());
    }

    @Test
    public void testMovieEntitySettersAndGetters() {
        CategorieEntity category = new CategorieEntity("Action", "image.jpg");

        MovieEntity movie = new MovieEntity();
        movie.setCategorie(category);
        movie.setName("Test Movie");
        movie.setDescription("Description");
        movie.setDuration("120 min");
        movie.setImage("image.jpg");

        assertEquals(category, movie.getCategorie());
        assertEquals("Test Movie", movie.getName());
        assertEquals("Description", movie.getDescription());
        assertEquals("120 min", movie.getDuration());
        assertEquals("image.jpg", movie.getImage());
    }
}
