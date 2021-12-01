package com.example.netcracker_lab;

import com.example.netcracker_lab.model.GenreDAO;
import com.example.netcracker_lab.pojo.Genre;
import com.example.netcracker_lab.pojo.Track;

import java.io.IOException;
import java.sql.SQLException;

public class SandBox {

    public static void main(String[] args) throws SQLException, IOException {

        Genre genre1 = Genre.builder().name("rap").build();


        Track track1 = Track.builder()
                .name("nice track")
                .author("me")
                .album("some album")
                .duration(12.)
                .genre(genre1)
                .build();

        Track track2 = Track.builder()
                .name("also bad track")
                .author("not me")
                .album("some album")
                .duration(12.)
                .genre(genre1)
                .build();

        GenreDAO.getInstance().save(genre1);

        //System.out.println(Properties.getInstance().getTrackDAOProperties().getDelete());

    }
}
