package com.example.simple_mvc_project.controller;

import com.example.simple_mvc_project.model.TrackDAO;
import com.example.simple_mvc_project.pojo.Genre;
import com.example.simple_mvc_project.pojo.Track;
import com.example.simple_mvc_project.view.IDHolder;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class UpdateTrackController {
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
        } else {
            Stage stage = (Stage) update.getScene().getWindow();
            stage.close();
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
                close();
            } else {
                close();
            }
        });
    }

    private void close() {
        Stage stage = (Stage) name.getScene().getWindow();
        stage.close();
    }
}
