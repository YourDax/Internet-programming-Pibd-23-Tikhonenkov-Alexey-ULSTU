package com.example.demo.movies.model;

import java.util.Objects;

import com.example.demo.ageratings.model.AgeRatingEntity;
import com.example.demo.core.model.BaseEntity;
import com.example.demo.types.model.TypeEntity;

public class MovieEntity extends BaseEntity {
    private String title;
    private TypeEntity type;
    private String poster;
    private String description;
    private Integer releaseDate;
    private String country;
    private String director;
    private AgeRatingEntity ageRating;
    private String video;

    public MovieEntity() {
        super();
    }

    public MovieEntity(Long id, String title, TypeEntity type, String poster,
            String description, Integer releaseDate, String country, String director,
            AgeRatingEntity ageRating, String video) {
        super(id);

        this.title = title;
        this.type = type;
        this.poster = poster;
        this.description = description;
        this.releaseDate = releaseDate;
        this.country = country;
        this.director = director;
        this.ageRating = ageRating;
        this.video = video;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TypeEntity getType() {
        return type;
    }

    public void setType(TypeEntity type) {
        this.type = type;
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

    public void setDescription(String description) {
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

    public AgeRatingEntity getAgeRating() {
        return ageRating;
    }

    public void setAgeRating(AgeRatingEntity ageRating) {
        this.ageRating = ageRating;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, type, poster, description, releaseDate, country,
                director, ageRating, video);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        final MovieEntity other = (MovieEntity) obj;
        return Objects.equals(other.getId(), id)
                && Objects.equals(other.getTitle(), title)
                && Objects.equals(other.getType(), type)
                && Objects.equals(other.getPoster(), poster)
                && Objects.equals(other.getDescription(), description)
                && Objects.equals(other.getReleaseDate(), releaseDate)
                && Objects.equals(other.getCountry(), country)
                && Objects.equals(other.getDirector(), director)
                && Objects.equals(other.getAgeRating(), ageRating)
                && Objects.equals(other.getVideo(), video);
    }
}
