package com.example.netcracker_lab.view;

import com.example.netcracker_lab.pojo.Track;
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
