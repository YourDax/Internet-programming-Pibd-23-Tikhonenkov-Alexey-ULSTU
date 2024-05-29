package com.example.demo.users.api;

import java.util.ArrayList;
import java.util.List;

public class UserProfileDto {
    private List<UserMovieDto> movies = new ArrayList<>();

    public UserProfileDto() {
    }

    public UserProfileDto(List<UserMovieDto> movies) {
        this.movies = movies;
    }

    public List<UserMovieDto> getMovies() {
        return movies;
    }

    public void setMovies(List<UserMovieDto> movies) {
        this.movies = movies;
    }
}
