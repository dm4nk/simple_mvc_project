package com.example.netcracker_lab.model;

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

    T update(T oldObject, T newObject) throws SQLException;
}
