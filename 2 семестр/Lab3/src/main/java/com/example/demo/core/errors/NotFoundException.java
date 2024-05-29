package com.example.demo.core.errors;

public class NotFoundException extends RuntimeException {
    public NotFoundException(Integer id) {
        super(String.format("Сущность с айдишником <[%s]> не найден, либо его не существует", id));
    }
}
