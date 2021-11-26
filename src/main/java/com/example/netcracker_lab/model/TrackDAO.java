package com.example.netcracker_lab.model;

import com.example.netcracker_lab.pojo.Track;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.util.Optional;
import java.util.Set;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class TrackDAO implements DAO<Track> {

    static TrackDAO instance = new TrackDAO();

    private TrackDAO() {
    }

    public static TrackDAO getInstance() {
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
        return Optional.empty();
    }

    @Override
    public Track update(Track oldObject, Track newObject) {
        return null;
    }
}
