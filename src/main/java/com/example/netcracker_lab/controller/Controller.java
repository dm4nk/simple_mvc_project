package com.example.netcracker_lab.controller;

import java.util.Optional;
import java.util.Set;

public interface Controller<T> {
    T save(T object);

    void delete(T object);

    Set<T> findAll();

    Optional<T> findById(Long id);

    T update(T oldObject, T newObject);
}
