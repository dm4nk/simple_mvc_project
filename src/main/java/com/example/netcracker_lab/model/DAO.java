package com.example.netcracker_lab.model;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;

public interface DAO<T> {
    T save(T object) throws SQLException;

    void delete(T object) throws SQLException;

    void deleteById(Integer id) throws SQLException;

    Set<T> findAll() throws SQLException;

    Optional<T> findById(Integer id) throws SQLException;

    Set<T> findByName(String name) throws SQLException;

    Set<T> findByTemplate(String template) throws SQLException;

    T update(T oldObject, T newObject) throws SQLException;
}
