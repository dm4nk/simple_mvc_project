package com.example.simple_mvc_project.view;

import com.example.simple_mvc_project.pojo.Track;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TrackHolder {

    private Track track;
    private static final TrackHolder INSTANCE = new TrackHolder();

    private TrackHolder() {

    }

    public static TrackHolder getInstance() {
        return INSTANCE;
    }
}
