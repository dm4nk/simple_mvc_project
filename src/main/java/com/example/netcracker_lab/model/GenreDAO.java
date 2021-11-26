package com.example.netcracker_lab.model;

import com.example.netcracker_lab.pojo.Genre;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.Optional;
import java.util.Set;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class GenreDAO implements DAO<Genre> {

    static GenreDAO instance = new GenreDAO();

    private GenreDAO() {
    }

    public static GenreDAO getInstance() {
        return instance;
    }

    //todo: вставить сюда всю логику подключения к базе данных

    @Override
    public Genre save(Genre object) {
        return null;
    }

    @Override
    public void delete(Genre object) {

    }

    @Override
    public Set<Genre> findAll() {
        return null;
    }

    @Override
    public Optional<Genre> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Genre update(Genre oldObject, Genre newObject) {
        return null;
    }
}
