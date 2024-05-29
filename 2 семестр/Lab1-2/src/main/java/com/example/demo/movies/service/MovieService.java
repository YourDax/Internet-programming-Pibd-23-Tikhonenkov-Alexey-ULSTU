package com.example.demo.movies.service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.core.error.NotFoundException;
import com.example.demo.movies.model.MovieEntity;
import com.example.demo.movies.repository.MovieRepository;

@Service
public class MovieService {
    private final MovieRepository repository;

    public MovieService(MovieRepository repository) {
        this.repository = repository;
    }

    public List<MovieEntity> getAll(Long typeId, Long ageratingId) {
        if (Objects.equals(typeId, 0L) && Objects.equals(ageratingId, 0L) ) {
            return repository.getAll();
        }
        if (Objects.equals(ageratingId, 0L)) {
            return repository.getAll().stream()
                .filter(item -> item.getType().getId().equals(typeId))
                .toList();
                
        }
        if (Objects.equals(typeId, 0L)) {
            return repository.getAll().stream()
                .filter(item -> item.getAgeRating().getId().equals(ageratingId))
                .toList();
        }
        else
        return repository.getAll().stream()
                .filter(item -> item.getType().getId().equals(typeId))
                .filter(item -> item.getAgeRating().getId().equals(ageratingId))
                .toList();
    }
    

    public MovieEntity get(Long id) {
        return Optional.ofNullable(repository.get(id))
                .orElseThrow(() -> new NotFoundException(id));
    }

    public MovieEntity create(MovieEntity entity) {
        return repository.create(entity);
    }

    public MovieEntity update(Long id, MovieEntity entity) {
        final MovieEntity existsEntity = get(id);
        existsEntity.setTitle(entity.getTitle());
        existsEntity.setType(entity.getType());
        existsEntity.setPoster(entity.getPoster());
        existsEntity.setDescription(entity.getDescription());
        existsEntity.setReleaseDate(entity.getReleaseDate());
        existsEntity.setCountry(entity.getCountry());
        existsEntity.setDirector(entity.getDirector());
        existsEntity.setAgeRating(entity.getAgeRating());
        existsEntity.setVideo(entity.getVideo());
        return repository.update(existsEntity);
    }

    public MovieEntity delete(Long id) {
        final MovieEntity existsEntity = get(id);
        return repository.delete(existsEntity);
    }

    public void deleteAll() {
        repository.deleteAll();
    }
}
