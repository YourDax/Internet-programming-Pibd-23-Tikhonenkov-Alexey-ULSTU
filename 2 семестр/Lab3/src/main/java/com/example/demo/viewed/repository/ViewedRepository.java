package com.example.demo.viewed.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import com.example.demo.viewed.model.ViewedEntity;

public interface ViewedRepository extends CrudRepository<ViewedEntity, Integer> {

    List<ViewedEntity> findByUserId(Integer userId);

    Optional<ViewedEntity> findOneByUserIdAndId(Integer userId, Integer id);

}
