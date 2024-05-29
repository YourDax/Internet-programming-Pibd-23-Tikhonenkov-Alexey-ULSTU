package com.example.demo.movies.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.movies.model.MovieEntity;

public interface MovieRepository extends CrudRepository<MovieEntity, Long>,
        PagingAndSortingRepository<MovieEntity, Long> {

    Optional<MovieEntity> findByNameIgnoreCase(String name);

    Optional<MovieEntity> findOneById(long id);

    List<MovieEntity> findByCategorieId(long categorieId);

    Page<MovieEntity> findByCategorieId(long categorieId, Pageable pageable);

}