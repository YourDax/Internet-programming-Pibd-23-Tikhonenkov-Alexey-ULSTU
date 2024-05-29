package com.example.demo.users.service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;

import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.example.demo.core.configurations.Constants;
import com.example.demo.core.errors.NotFoundException;
import com.example.demo.core.security.UserPrincipal;
import com.example.demo.favorites.model.UserMovieEntity;
import com.example.demo.movies.model.MovieEntity;
import com.example.demo.movies.service.MovieService;
import com.example.demo.users.model.UserEntity;
import com.example.demo.users.model.UserRole;
import com.example.demo.users.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository repository, PasswordEncoder passwordEncoder) {
        this.repository = repository;
        this.passwordEncoder = passwordEncoder;
    }

    private void checkLogin(Long id, String login) {
        final Optional<UserEntity> existsUser = repository.findByLoginIgnoreCase(login);
        if (existsUser.isPresent() && !existsUser.get().getId().equals(id)) {
            throw new IllegalArgumentException(
                    String.format("User with login %s is already exists", login));
        }
    }

    @Transactional(readOnly = true)
    public List<UserEntity> getAll() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).toList();
    }

    @Transactional(readOnly = true)
    public Page<UserEntity> getAll(int page, int size) {
        return repository.findAll(PageRequest.of(page, size, Sort.by("id")));
    }

    @Transactional(readOnly = true)
    public UserEntity get(Long id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException(UserEntity.class, id));
    }

    @Transactional(readOnly = true)
    public UserEntity getByUsername(String username) {
        return repository.findByLoginIgnoreCase(username)
                .orElseThrow(() -> new IllegalArgumentException(
                        String.format("User with login %s is not found", username)));
    }

    @Transactional
    public UserEntity create(UserEntity entity) {
        if (entity == null) {
            throw new IllegalArgumentException("Entity is null");
        }
        checkLogin(null, entity.getLogin());
        final String password = Optional.ofNullable(entity.getPassword()).orElse("");
        entity.setPassword(
                passwordEncoder.encode(StringUtils.hasText(password.strip()) ? password : Constants.DEFAULT_PASSWORD));
        entity.setRole(Optional.ofNullable(entity.getRole()).orElse(UserRole.USER));
        repository.save(entity);
        return repository.save(entity);
    }

    @Transactional
    public UserEntity update(Long id, UserEntity entity) {
        UserEntity existsentity = get(id);
        checkLogin(id, entity.getLogin());
        existsentity.setLogin(entity.getLogin());
        existsentity.setPassword(entity.getPassword());
        existsentity.setRole(entity.getRole());
        return repository.save(existsentity);
    }

    @Transactional
    public UserEntity delete(Long id) {
        final UserEntity existsentity = get(id);
        repository.delete(existsentity);
        return existsentity;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final UserEntity existUser = getByUsername(username);
        return new UserPrincipal(existUser);
    }
}
