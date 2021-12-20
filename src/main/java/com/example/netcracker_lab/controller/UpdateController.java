package com.example.netcracker_lab.controller;

import com.example.netcracker_lab.model.TrackDAO;
import com.example.netcracker_lab.pojo.Genre;
import com.example.netcracker_lab.pojo.Track;
import com.example.netcracker_lab.view.IDHolder;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.sql.SQLException;

public class UpdateController {
    @FXML
    public TextField name;
    @FXML
    public TextField genre;
    @FXML
    public TextField artist;
    @FXML
    public TextField time;
    @FXML
    public TextField album;
    @FXML
    public Button update;

    @FXML
    void initialize() throws SQLException {
        Integer id = IDHolder.getInstance().getId();
        Track track = TrackDAO.getInstance().findById(id).orElse(null);
        if (track != null) {
            name.setText(track.getName());
            genre.setText(track.getGenre().getName());
            artist.setText(track.getAuthor());
            time.setText(String.valueOf(track.getDuration()));
            album.setText(track.getAlbum());
        }

        update.setOnAction(actionEvent -> {
            if (track != null) {
                Track newTrack = Track.builder()
                        .name(name.getText())
                        .genre(Genre.builder().name(genre.getText()).build())
                        .author(artist.getText())
                        .duration(Double.valueOf(time.getText()))
                        .album(album.getText())
                        .build();

                try {
                    TrackDAO.getInstance().update(track.getId(), newTrack);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
