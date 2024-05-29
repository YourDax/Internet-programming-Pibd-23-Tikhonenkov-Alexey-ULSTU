package com.example.demo.ageratings.repository;

import org.springframework.stereotype.Repository;

import com.example.demo.ageratings.model.AgeRatingEntity;
import com.example.demo.core.repository.MapRepository;

@Repository
public class AgeRatingRepository extends MapRepository<AgeRatingEntity> {
}
