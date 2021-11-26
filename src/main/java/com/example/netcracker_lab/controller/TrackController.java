package com.example.netcracker_lab.controller;

import com.example.netcracker_lab.model.TrackDAO;
import com.example.netcracker_lab.pojo.Track;
import com.example.netcracker_lab.view.View;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.Optional;
import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class TrackController implements Controller<Track> {
    static TrackController instance = new TrackController();
    TrackDAO trackDAO;

    private TrackController() {
        trackDAO = TrackDAO.getInstance();
    }

    private static TrackController getInstance() {
        return instance;
    }

    @Override
    public Track save(Track object) {
        return null;
    }

    @Override
    public void delete(Track object) {

    }

    @Override
    public Set<Track> findAll() {
        return null;
    }

    @Override
    public Optional<Track> findById(Long id) {
        return null;
    }

    @Override
    public Track update(Track oldObject, Track newObject) {
        return null;
    }
}
