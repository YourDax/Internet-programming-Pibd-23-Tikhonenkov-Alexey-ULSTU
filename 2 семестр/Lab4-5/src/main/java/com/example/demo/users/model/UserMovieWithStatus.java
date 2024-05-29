package com.example.demo.users.model;

import java.util.Objects;

import com.example.demo.movies.model.MovieEntity;

public class UserMovieWithStatus {
    private Long id;
    private String name;
    private boolean active;

    public UserMovieWithStatus() {
    }

    public UserMovieWithStatus(MovieEntity entity, boolean active) {
        this.id = entity.getId();
        this.name = entity.getName();
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, active);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        UserMovieWithStatus other = (UserMovieWithStatus) obj;
        return Objects.equals(id, other.id) && Objects.equals(name, other.name) && active == other.active;
    }

}
