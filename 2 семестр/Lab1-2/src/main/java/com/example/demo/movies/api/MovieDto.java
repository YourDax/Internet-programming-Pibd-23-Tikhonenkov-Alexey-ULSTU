package com.example.demo.movies.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MovieDto {
    private Long id;

    @NotNull
    @NotBlank
    private String title;

    @NotNull
    @Min(1)
    private Long typeId;

    private String poster;

    @NotNull
    @NotBlank
    private String description;

    @NotNull
    private Integer releaseDate;

    @NotNull
    @NotBlank
    private String country;

    @NotNull
    @NotBlank
    private String director;

    @NotNull
    @Min(1)
    private Long ageRatingId;

    @NotNull
    @NotBlank
    private String video;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTypeId() {
        return typeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
    }

    public String getDescription() {
        return description;
    }

    public void getDescription(String description) {
        this.description = description;
    }

    public Integer getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Integer releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public Long getAgeRatingId() {
        return ageRatingId;
    }

    public void setAgeRatingId(Long ageRatingId) {
        this.ageRatingId = ageRatingId;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }
}
