package com.example.demo.categories.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.categories.model.CategorieEntity;

public interface CategorieRepository extends CrudRepository<CategorieEntity, Long> {
    Optional<CategorieEntity> findByNameIgnoreCase(String name);
}
