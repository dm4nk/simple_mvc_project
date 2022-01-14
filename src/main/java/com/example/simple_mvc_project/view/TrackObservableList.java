package com.example.simple_mvc_project.view;

import com.example.simple_mvc_project.pojo.Track;
import javafx.collections.ObservableListBase;

import java.util.List;

public class TrackObservableList extends ObservableListBase<Track> {

    private final List<Track> tracks;

    public TrackObservableList(List<Track> tracks) {
        this.tracks = tracks;
    }

    @Override
    public Track get(int index) {
        return tracks.get(index);
    }

    @Override
    public int size() {
        return tracks.size();
    }
}
