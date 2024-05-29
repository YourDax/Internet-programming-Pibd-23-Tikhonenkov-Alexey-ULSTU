package com.example.demo.core.model;

import com.example.demo.core.configurations.Constants;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.SequenceGenerator;

@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = Constants.SEQUENCE_NAME)
    @SequenceGenerator(name = Constants.SEQUENCE_NAME, sequenceName = Constants.SEQUENCE_NAME, allocationSize = 1)
    protected Integer id;

    protected BaseEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
