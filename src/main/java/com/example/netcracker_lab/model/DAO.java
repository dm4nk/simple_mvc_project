package com.example.netcracker_lab.model;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;

public interface DAO<T> {
    T save(T object) throws SQLException;

    void delete(T object);

    Set<T> findAll();

    Optional<T> findById(Integer id);

    Set<T> findByName(String name) throws SQLException;

    T update(T oldObject, T newObject);
}
