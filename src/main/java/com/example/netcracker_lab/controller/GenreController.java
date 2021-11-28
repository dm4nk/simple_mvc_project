package com.example.netcracker_lab.controller;

import com.example.netcracker_lab.model.GenreDAO;
import com.example.netcracker_lab.pojo.Genre;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class GenreController implements Controller<Genre> {
    static GenreDAO genreDAO;

    static GenreController instance = new GenreController();

    private GenreController() {
        genreDAO = GenreDAO.getInstance();
    }

    private static GenreController getInstance() {
        return instance;
    }

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
        return null;
    }

    @Override
    public Optional<Genre> findByName(String name) throws SQLException {
        return Optional.empty();
    }

    @Override
    public Genre update(Genre oldObject, Genre newObject) {
        return null;
    }
}
