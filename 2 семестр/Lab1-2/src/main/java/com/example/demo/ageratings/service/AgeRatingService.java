package com.example.demo.ageratings.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.ageratings.model.AgeRatingEntity;
import com.example.demo.ageratings.repository.AgeRatingRepository;
import com.example.demo.core.error.NotFoundException;

@Service
public class AgeRatingService {
    private final AgeRatingRepository repository;

    public AgeRatingService(AgeRatingRepository repository) {
        this.repository = repository;
    }

    public List<AgeRatingEntity> getAll() {
        return repository.getAll();
    }

    public AgeRatingEntity get(Long id) {
        return Optional.ofNullable(repository.get(id))
                .orElseThrow(() -> new NotFoundException(id));
    }

    public AgeRatingEntity create(AgeRatingEntity entity) {
        return repository.create(entity);
    }

    public AgeRatingEntity update(Long id, AgeRatingEntity entity) {
        final AgeRatingEntity existsEntity = get(id);
        existsEntity.setName(entity.getName());
        return repository.update(existsEntity);
    }

    public AgeRatingEntity delete(Long id) {
        final AgeRatingEntity existsEntity = get(id);
        return repository.delete(existsEntity);
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}
