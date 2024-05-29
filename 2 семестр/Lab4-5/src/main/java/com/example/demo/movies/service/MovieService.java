package com.example.demo.movies.service;

import java.util.List;
import java.util.stream.StreamSupport;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.core.errors.NotFoundException;
import com.example.demo.movies.model.MovieEntity;
import com.example.demo.movies.repository.MovieRepository;
import com.example.demo.users.model.UserEntity;
import com.example.demo.users.service.UserService;

@Service
public class MovieService {

    private final MovieRepository repository;

    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }

    public MovieEntity get(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NotFoundException(MovieEntity.class, id));
    }

    public void checkName(String name) {
        if (repository.findByNameIgnoreCase(name).isPresent()) {
            throw new IllegalArgumentException(
                    String.format("Movie with name %s is already exists", name));
        }
    }

    @Transactional(readOnly = true)
    public List<MovieEntity> getAll(long categorieId) {
        if (categorieId <= 0L) {
            return StreamSupport.stream(repository.findAll().spliterator(), false).toList();
        } else {
            return repository.findByCategorieId(categorieId);
        }
    }

    @Transactional(readOnly = true)
    public Page<MovieEntity> getAll(long categorieId, int page, int size) {
        final Pageable pageable = PageRequest.of(page, size, Sort.by("id"));
        if (categorieId <= 0L) {
            return repository.findAll(pageable);
        } else {
            return repository.findByCategorieId(categorieId, pageable);
        }
    }

    @Transactional(readOnly = true)
    public MovieEntity get(long id) {
        return repository.findOneById(id)
                .orElseThrow(() -> new NotFoundException(MovieEntity.class, id));
    }

    @Transactional
    public MovieEntity create(MovieEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity is null");
        }
        return repository.save(entity);
    }

    @Transactional
    public List<MovieEntity> createAll(List<MovieEntity> entities) {
        if (entities == null || entities.isEmpty()) {
            throw new IllegalArgumentException("Movies list is null or empty");
        }
        return StreamSupport.stream(repository.saveAll(entities).spliterator(), false).toList();
    }

    @Transactional
    public MovieEntity update(Long id, MovieEntity entity) {
        final MovieEntity exisEntity = get(id);
        exisEntity.setCategorie(entity.getCategorie());
        exisEntity.setName(entity.getName());
        exisEntity.setDescription(entity.getDescription());
        exisEntity.setDuration(entity.getDuration());
        exisEntity.setImage(entity.getImage());
        return repository.save(exisEntity);
    }

    @Transactional
    public MovieEntity delete(Long id) {
        final MovieEntity exisEntity = get(id);
        repository.delete(exisEntity);
        return exisEntity;
    }
}
