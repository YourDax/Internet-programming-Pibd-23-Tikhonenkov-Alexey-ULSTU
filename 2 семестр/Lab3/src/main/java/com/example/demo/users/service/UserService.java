package com.example.demo.users.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.StreamSupport;

import com.example.demo.core.errors.NotFoundException;
import com.example.demo.users.model.UserEntity;
import com.example.demo.users.repository.UserRepository;

@Service
public class UserService {
    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<UserEntity> getAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).toList();
    }

    @Transactional(readOnly = true)
    public UserEntity get(Integer id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    @Transactional
    public UserEntity create(UserEntity entity) {
        return repository.save(entity);
    }

    @Transactional
    public UserEntity update(Integer id, UserEntity entity) {
        final UserEntity existsentity = get(id);
        existsentity.setUsername(entity.getUsername());
        existsentity.setPassword(entity.getPassword());
        existsentity.setIsAdmin(entity.getIsAdmin());
        return repository.save(existsentity);
    }

    @Transactional
    public UserEntity delete(Integer id) {
        final UserEntity existsentity = get(id);
        repository.delete(existsentity);
        return existsentity;
    }

}
