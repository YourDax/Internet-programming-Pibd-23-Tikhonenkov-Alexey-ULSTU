package com.example.demo.viewed.service;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.core.errors.NotFoundException;
import com.example.demo.viewed.model.ViewedEntity;
import com.example.demo.viewed.repository.ViewedRepository;

@Service
public class ViewedService {
    private final ViewedRepository repository;

    public ViewedService(ViewedRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<ViewedEntity> getAll(Integer userId) {
        if (userId == 0) {
            return StreamSupport.stream(repository.findAll().spliterator(), false).toList();
        }
        return repository.findByUserId(userId);
    }

    @Transactional(readOnly = true)
    public ViewedEntity get(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    @Transactional
    public ViewedEntity create(ViewedEntity entity) {
        return repository.save(entity);
    }

    @Transactional
    public ViewedEntity update(Integer id, ViewedEntity entity) {
        final ViewedEntity exisEntity = get(id);
        exisEntity.setUser(entity.getUser());
        exisEntity.setMovie(entity.getMovie());
        return repository.save(exisEntity);
    }

    @Transactional
    public ViewedEntity delete(Integer id) {
        final ViewedEntity exisEntity = get(id);
        repository.delete(exisEntity);
        return exisEntity;
    }
}
