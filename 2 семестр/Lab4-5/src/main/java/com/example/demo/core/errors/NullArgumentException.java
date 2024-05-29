package com.example.demo.core.errors;

public class NullArgumentException extends RuntimeException {
    public NullArgumentException(Integer id) {
        super(String.format("В данной сущности нет объектов в айдишником <[%s]>", id));
    }
}
