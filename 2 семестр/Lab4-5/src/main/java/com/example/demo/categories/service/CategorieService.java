package com.example.demo.categories.service;

import java.util.List;

import java.util.stream.StreamSupport;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.core.errors.NotFoundException;
import com.example.demo.categories.model.CategorieEntity;
import com.example.demo.categories.repository.CategorieRepository;

@Service
public class CategorieService {

    private final CategorieRepository repository;

    public CategorieService(CategorieRepository repository) {
        this.repository = repository;
    }

    private void checkName(String name) {
        if (repository.findByNameIgnoreCase(name).isPresent()) {
            throw new IllegalArgumentException(
                    String.format("Type with name %s is already exists", name));
        }
    }

    @Transactional(readOnly = true)
    public List<CategorieEntity> getAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).toList();
    }

    @Transactional(readOnly = true)
    public CategorieEntity get(long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(CategorieEntity.class, id));
    }

    @Transactional
    public CategorieEntity create(CategorieEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Сущность не найдена");
        }
        checkName(entity.getName());
        return repository.save(entity);
    }

    @Transactional
    public CategorieEntity update(Long id, CategorieEntity entity) {
        final CategorieEntity existsentity = get(id);
        existsentity.setName(entity.getName());
        existsentity.setImage(entity.getImage());
        return repository.save(existsentity);
    }

    @Transactional
    public CategorieEntity delete(Long id) {
        final CategorieEntity existsentity = get(id);
        repository.delete(existsentity);
        return existsentity;
    }

}
