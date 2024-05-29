package com.example.demo.movies.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.core.repository.MapRepository;
import com.example.demo.movies.model.MovieEntity;

@Repository
public class MovieRepository extends MapRepository<MovieEntity> {
}
