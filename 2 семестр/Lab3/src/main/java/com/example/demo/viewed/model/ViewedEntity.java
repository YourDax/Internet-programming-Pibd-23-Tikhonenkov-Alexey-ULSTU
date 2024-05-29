package com.example.demo.viewed.model;

import java.util.Objects;

import com.example.demo.core.model.BaseEntity;
import com.example.demo.movies.model.MovieEntity;
import com.example.demo.users.model.UserEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "viewes")
public class ViewedEntity extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    private UserEntity user;

    @ManyToOne
    @JoinColumn(name = "movieId", nullable = false)
    private MovieEntity movie;

    public ViewedEntity() {
        super();
    }

    public ViewedEntity(UserEntity user, MovieEntity movie) {
        this.user = user;
        this.movie = movie;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public MovieEntity getMovie() {
        return movie;
    }

    public void setMovie(MovieEntity movie) {
        this.movie = movie;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, user, movie);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        final ViewedEntity other = (ViewedEntity) obj;
        return Objects.equals(other.getId(), id)
                && Objects.equals(other.getUser(), user)
                && Objects.equals(other.getMovie(), movie);
    }
}
