package com.example.netcracker_lab.controller;

import com.example.netcracker_lab.model.TrackDAO;
import com.example.netcracker_lab.pojo.Track;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.sql.SQLException;
import java.util.Optional;

class TrackControllerTest {

    @Mock
    TrackDAO trackDAO;

    Controller<Track> controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        controller = new TrackController(trackDAO);
    }

    @Test
    void deleteById() throws SQLException {
        controller.deleteById("1");
        controller.deleteById("");
        Mockito.verify(trackDAO, Mockito.times(1)).deleteById(Mockito.anyInt());
    }

    @Test
    void findByTemplate() throws SQLException {
        controller.findByTemplate("1");
        controller.findByTemplate("");
        Mockito.verify(trackDAO, Mockito.times(1)).findByTemplate(Mockito.anyString());
    }

    @Test
    void findByName() throws SQLException {
        controller.findByName("1");
        controller.findByName("");
        Mockito.verify(trackDAO, Mockito.times(1)).findByName(Mockito.anyString());
    }

    @Test
    void findById() throws SQLException {
        Mockito.when(trackDAO.findById(Mockito.anyInt())).thenReturn(Optional.empty());
        controller.findById("1");
        controller.findById("");
        Mockito.verify(trackDAO, Mockito.times(1)).findById(Mockito.anyInt());
    }

    @Test
    void add() throws SQLException {
        controller.add("name", "author", "album", "12", "pop");
        controller.add("name", "", "album", "duration", "genre");
        Mockito.verify(trackDAO, Mockito.times(1)).save(Mockito.any());
    }
}