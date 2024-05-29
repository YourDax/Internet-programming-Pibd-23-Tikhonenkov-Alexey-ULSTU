package com.example.demo.movies.model;

import java.util.Objects;
import java.util.List;

import com.example.demo.categories.model.CategorieEntity;
import com.example.demo.core.model.BaseEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "movies")
public class MovieEntity extends BaseEntity {

    @ManyToMany
    private List<CategorieEntity> categories;

    @Column(nullable = false, unique = true, length = 50)
    private String name;

    @Column(nullable = false, unique = true, length = 300)
    private String description;

    @Column(nullable = false, unique = true, length = 50)
    private String duration;

    @Column(nullable = true)
    private String image;

    public MovieEntity() {
        super();
    }

    public MovieEntity(List<CategorieEntity> categories, String name, String description, String duration,
            String image) {

        this.categories = categories;
        this.name = name;
        this.description = description;
        this.duration = duration;
        this.image = image;
    }

    public List<CategorieEntity> getCategories() {
        return categories;
    }

    public void setCategories(List<CategorieEntity> categories) {
        this.categories = categories;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, categories, name, description, duration, image);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        final MovieEntity other = (MovieEntity) obj;
        return Objects.equals(other.getId(), id)
                && Objects.equals(other.getCategories(), categories)
                && Objects.equals(other.getName(), name)
                && Objects.equals(other.getDescription(), description)
                && Objects.equals(other.getDuration(), duration)
                && Objects.equals(other.getImage(), image);
    }

}
