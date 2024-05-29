package com.example.demo.favorites.repository;

import com.example.demo.favorites.model.UserMovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserMovieRepository extends JpaRepository<UserMovieEntity, Long> {
    @Query("SELECT um FROM UserMovieEntity um JOIN FETCH um.movie WHERE um.user.id = :userId")
    List<UserMovieEntity> findByUserId(Long userId);

    List<UserMovieEntity> findByMovieId(Long movieId);

    Optional<UserMovieEntity> findByUserIdAndMovieId(Long userId, Long movieId);
}
