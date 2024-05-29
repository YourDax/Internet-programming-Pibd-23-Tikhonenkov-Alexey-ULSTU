package com.example.demo.favorites.service;

import com.example.demo.favorites.model.UserMovieEntity;
import com.example.demo.favorites.repository.UserMovieRepository;
import com.example.demo.movies.api.MovieDTO;
import com.example.demo.movies.model.MovieEntity;
import com.example.demo.movies.repository.MovieRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserMovieService {

    private final MovieRepository movieRepository;
    private final UserMovieRepository userMovieRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public UserMovieService(UserMovieRepository userMovieRepository, MovieRepository movieRepository,
            ModelMapper modelMapper) {
        this.movieRepository = movieRepository;
        this.userMovieRepository = userMovieRepository;
        this.modelMapper = modelMapper;
    }

    public List<MovieDTO> getFavoriteMoviesByUserId(Long userId) {
        return userMovieRepository.findByUserId(userId).stream()
                .map(userMovie -> modelMapper.map(userMovie.getMovie(), MovieDTO.class))
                .collect(Collectors.toList());
    }

    public List<UserMovieEntity> getAllUserMovies() {
        return userMovieRepository.findAll();
    }

    public Optional<MovieEntity> getMovieById(Long movieId) {
        return movieRepository.findById(movieId);
    }

    public Optional<UserMovieEntity> getUserMovieById(Long id) {
        return userMovieRepository.findById(id);
    }

    public List<UserMovieEntity> getUserMoviesByUserId(Long userId) {
        return userMovieRepository.findByUserId(userId);
    }

    public List<UserMovieEntity> getUserMoviesByMovieId(Long movieId) {
        return userMovieRepository.findByMovieId(movieId);
    }

    public UserMovieEntity createUserMovie(UserMovieEntity userMovieEntity) {
        return userMovieRepository.save(userMovieEntity);
    }

    public UserMovieEntity updateUserMovie(Long id, UserMovieEntity userMovieEntity) {
        userMovieEntity.setId(id);
        return userMovieRepository.save(userMovieEntity);
    }

    public void deleteUserMovie(Long id) {
        userMovieRepository.deleteById(id);
    }

    @Transactional
    public void removeUserMovie(Long userId, Long movieId) {
        Optional<UserMovieEntity> userMovieEntity = userMovieRepository.findByUserIdAndMovieId(userId, movieId);
        userMovieEntity.ifPresent(userMovieRepository::delete);
    }
}
