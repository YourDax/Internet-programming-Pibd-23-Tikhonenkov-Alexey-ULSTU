package com.example.demo.ageratings.model;

import java.util.Objects;

import com.example.demo.core.model.BaseEntity;

public class AgeRatingEntity extends BaseEntity {
    private String name;

    public AgeRatingEntity() {
        super();
    }

    public AgeRatingEntity(Long id, String name) {
        super(id);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        final AgeRatingEntity other = (AgeRatingEntity) obj;
        return Objects.equals(other.getId(), id)
                && Objects.equals(other.getName(), name);
    }

}
