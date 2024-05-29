package com.example.demo.movies.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.movies.model.MovieEntity;

public interface MovieRepository extends CrudRepository<MovieEntity, Integer> {

    Optional<MovieEntity> findByNameIgnoreCase(String name);

    Optional<MovieEntity> findByCategoriesIdAndId(Integer categoriesId, Integer id);

    List<MovieEntity> findByCategoriesId(Integer categoriesId);

    @Query("SELECT COUNT(DISTINCT v.user.id) FROM ViewedEntity v WHERE v.movie.id = :movieId")
    Integer getCountViews(@Param("movieId") Integer movieId);

}