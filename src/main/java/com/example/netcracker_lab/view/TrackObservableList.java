package com.example.netcracker_lab.view;

import com.example.netcracker_lab.pojo.Track;
import javafx.collections.ObservableListBase;

import java.util.ArrayList;
import java.util.List;

public class TrackObservableList extends ObservableListBase<Track> {

    private final List<Track> tracks;

    public TrackObservableList(List<Track> tracks) {
        this.tracks = tracks;
    }

    public TrackObservableList() {
        this.tracks = new ArrayList<>();
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
