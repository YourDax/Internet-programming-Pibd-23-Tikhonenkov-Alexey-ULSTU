package com.example.demo.categories.repository;

import java.util.Optional;
import java.util.List;


import org.springframework.data.repository.CrudRepository;

import com.example.demo.categories.model.CategorieEntity;

public interface CategorieRepository extends CrudRepository<CategorieEntity, Integer> {
    Optional<CategorieEntity> findByNameIgnoreCase(String name);
    List<CategorieEntity> findAllById(List<Integer> ids);
}
