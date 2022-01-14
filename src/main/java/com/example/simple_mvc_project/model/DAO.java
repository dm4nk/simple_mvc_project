package com.example.simple_mvc_project.model;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    T save(T object) throws SQLException;

    void delete(T object) throws SQLException;

    void deleteById(Integer id) throws SQLException;

    List<T> findAll() throws SQLException;

    Optional<T> findById(Integer id) throws SQLException;

    List<T> findByName(String name) throws SQLException;

    List<T> findByTemplate(String template) throws SQLException;

    T update(Integer id, T newObject) throws SQLException;
}
