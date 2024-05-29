package com.example.demo.favorites.repository;

import java.util.Optional;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.favorites.model.FavoriteEntity;

public interface FavoriteRepository extends CrudRepository<FavoriteEntity, Integer> {
    List<FavoriteEntity> findByUserId(Integer userId);

    Optional<FavoriteEntity> findOneByUserIdAndId(Integer userId, Integer id);
}
