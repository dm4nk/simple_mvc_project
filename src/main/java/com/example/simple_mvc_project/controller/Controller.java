package com.example.simple_mvc_project.controller;

import java.sql.SQLException;
import java.util.List;

public interface Controller<T> {
    void deleteById(String id) throws SQLException;

    List<T> findByTemplate(String template) throws SQLException;

    List<T> findById(String id) throws SQLException;

    List<T> findByName(String name) throws SQLException;

    void add(String... name) throws SQLException;
}
