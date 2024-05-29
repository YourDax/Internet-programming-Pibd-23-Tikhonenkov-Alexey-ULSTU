package com.example.demo;

import com.example.demo.categories.model.CategorieEntity;
import com.example.demo.core.errors.NotFoundException;
import com.example.demo.movies.model.MovieEntity;
import com.example.demo.movies.repository.MovieRepository;
import com.example.demo.movies.service.MovieService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MovieServiceTest {

    @Mock
    private MovieRepository movieRepository;

    @InjectMocks
    private MovieService movieService;

    @Test
    public void testCreateMovie() {
        MovieEntity movie = new MovieEntity();
        when(movieRepository.save(any(MovieEntity.class))).thenReturn(movie);

        MovieEntity createdMovie = movieService.create(movie);

        assertNotNull(createdMovie);
        verify(movieRepository, times(1)).save(any(MovieEntity.class));
    }

    @Test
    public void testDeleteMovie() {
        MovieEntity movie = new MovieEntity();
        when(movieRepository.findById(anyLong())).thenReturn(Optional.of(movie));

        MovieEntity deletedMovie = movieService.delete(1L);

        assertNotNull(deletedMovie);
        verify(movieRepository, times(1)).delete(any(MovieEntity.class));
    }
}
