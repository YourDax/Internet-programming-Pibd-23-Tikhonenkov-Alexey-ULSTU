package com.example.demo.movies.service;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.core.errors.NotFoundException;
import com.example.demo.movies.model.MovieEntity;
import com.example.demo.movies.repository.MovieRepository;

@Service
public class MovieService {

    private final MovieRepository repository;

    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }

    private void checkName(String name) {
        if (repository.findByNameIgnoreCase(name).isPresent()) {
            throw new IllegalArgumentException(
                    String.format("Type with name %s is already exists", name));
        }
    }

    @Transactional(readOnly = true)
    public List<MovieEntity> getAll(Integer categoriesId) {

        if (categoriesId == 0) {
            return StreamSupport.stream(repository.findAll().spliterator(), false).toList();
        }
        return repository.findByCategoriesId(categoriesId);
    }

    @Transactional(readOnly = true)
    public MovieEntity get(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    @Transactional
    public MovieEntity create(MovieEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Сущность не найдена");
        }
        checkName(entity.getName());

        return repository.save(entity);
    }

    @Transactional
    public MovieEntity update(Integer id, MovieEntity entity) {
        final MovieEntity exisEntity = get(id);
        exisEntity.setCategories(entity.getCategories());
        exisEntity.setName(entity.getName());
        exisEntity.setDescription(entity.getDescription());
        exisEntity.setDuration(entity.getDuration());
        exisEntity.setImage(entity.getImage());
        return repository.save(exisEntity);
    }

    @Transactional
    public MovieEntity delete(Integer id) {
        final MovieEntity exisEntity = get(id);
        repository.delete(exisEntity);
        return exisEntity;
    }
}
