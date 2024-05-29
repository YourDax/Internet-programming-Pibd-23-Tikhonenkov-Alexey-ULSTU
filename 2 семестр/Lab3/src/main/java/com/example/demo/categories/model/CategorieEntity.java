package com.example.demo.categories.model;

import java.util.Objects;
import java.util.List;

import com.example.demo.core.model.BaseEntity;
import com.example.demo.movies.model.MovieEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "categories")
public class CategorieEntity extends BaseEntity {

    @Column(nullable = false, unique = true, length = 50)
    private String name;

    @Column(nullable = true)
    private String image;

    @ManyToMany(mappedBy = "categories")
    private List<MovieEntity> movies;

    public CategorieEntity() {

    }

    public CategorieEntity(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, image);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        final CategorieEntity other = (CategorieEntity) obj;
        return Objects.equals(other.getId(), id) &&
                Objects.equals(other.getName(), name) &&
                Objects.equals(other.getImage(), image);
    }

}
