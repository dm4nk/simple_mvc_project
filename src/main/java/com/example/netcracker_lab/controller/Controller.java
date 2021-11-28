package com.example.netcracker_lab.controller;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;

public interface Controller<T> {
    T save(T object);

    void delete(T object);

    Set<T> findAll();

    Optional<T> findById(Long id);

    Optional<T> findByName(String name) throws SQLException;

    T update(T oldObject, T newObject);
}
