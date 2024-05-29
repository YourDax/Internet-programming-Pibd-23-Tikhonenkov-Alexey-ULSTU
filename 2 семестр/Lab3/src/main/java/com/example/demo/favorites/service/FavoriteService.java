package com.example.demo.favorites.service;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.core.errors.NotFoundException;
import com.example.demo.favorites.model.FavoriteEntity;
import com.example.demo.favorites.repository.FavoriteRepository;

@Service
public class FavoriteService {
    private final FavoriteRepository repository;

    public FavoriteService(FavoriteRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<FavoriteEntity> getAll(Integer userId) {
        if (userId == 0) {
            return StreamSupport.stream(repository.findAll().spliterator(), false).toList();
        }
        return repository.findByUserId(userId);
    }

    @Transactional(readOnly = true)
    public FavoriteEntity get(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    @Transactional
    public FavoriteEntity create(FavoriteEntity entity) {
        return repository.save(entity);
    }

    @Transactional
    public FavoriteEntity update(Integer id, FavoriteEntity entity) {
        final FavoriteEntity exisEntity = get(id);
        exisEntity.setUser(entity.getUser());
        exisEntity.setMovie(entity.getMovie());
        return repository.save(exisEntity);
    }

    @Transactional
    public FavoriteEntity delete(Integer id) {
        final FavoriteEntity exisEntity = get(id);
        repository.delete(exisEntity);
        return exisEntity;
    }
}
