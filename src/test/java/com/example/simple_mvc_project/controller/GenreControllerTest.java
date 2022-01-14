package com.example.simple_mvc_project.controller;

import com.example.simple_mvc_project.model.GenreDAO;
import com.example.simple_mvc_project.pojo.Genre;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;
import java.util.Optional;

class GenreControllerTest {

    @Mock
    GenreDAO genreDAO;

    Controller<Genre> controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new GenreController(genreDAO);
    }

    @Test
    void deleteById() throws SQLException {
        controller.deleteById("1");
        controller.deleteById("");
        Mockito.verify(genreDAO, Mockito.times(1)).deleteById(Mockito.anyInt());
    }

    @Test
    void findByTemplate() throws SQLException {
        controller.findByTemplate("1");
        controller.findByTemplate("");
        Mockito.verify(genreDAO, Mockito.times(1)).findByTemplate(Mockito.anyString());
    }

    @Test
    void findByName() throws SQLException {
        controller.findByName("1");
        controller.findByName("");
        Mockito.verify(genreDAO, Mockito.times(1)).findByName(Mockito.anyString());
    }

    @Test
    void findById() throws SQLException {
        Mockito.when(genreDAO.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        controller.findById("1");
        controller.findById("");
        Mockito.verify(genreDAO, Mockito.times(1)).findById(Mockito.anyInt());
    }

    @Test
    void add() throws SQLException {
        controller.add("name");
        controller.add("");
        Mockito.verify(genreDAO, Mockito.times(1)).save(Mockito.any());
    }
}