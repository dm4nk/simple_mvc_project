package com.example.netcracker_lab.controller;

import com.example.netcracker_lab.model.TrackDAO;
import com.example.netcracker_lab.pojo.Genre;
import com.example.netcracker_lab.pojo.Track;
import com.example.netcracker_lab.view.TrackObservableList;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.net.URL;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class TrackController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button FindTrackByNameButton;

    @FXML
    private TextField FindTrackByNameField;

    @FXML
    private Button addTrackButton;

    @FXML
    private TextField addTrackFieldAlbum;

    @FXML
    private TextField addTrackFieldArtist;

    @FXML
    private TextField addTrackFieldGenre;

    @FXML
    private TextField addTrackFieldName;

    @FXML
    private TextField addTrackFieldTime;

    @FXML
    private Button deleteTrackByIdButton;

    @FXML
    private TextField deleteTrackByIdField;

    @FXML
    private Button deleteTrackByNameButton;

    @FXML
    private TextField deleteTrackByNameField;

    @FXML
    private Button findTrackByIdButton;

    @FXML
    private Button refreshTableButton;

    @FXML
    private TextField findTrackByIdField;

    @FXML
    private Button findTrackByTemplateButton;

    @FXML
    private TextField templateField;

    @FXML
    private TableView<Track> tableOfTrack;

    @FXML
    private TableColumn<?, ?> tableOfTrackColumnAlbum;

    @FXML
    private TableColumn<?, ?> tableOfTrackColumnArtist;

    @FXML
    private TableColumn<?, ?> tableOfTrackColumnGenre;

    @FXML
    private TableColumn<?, ?> tableOfTrackColumnID;

    @FXML
    private TableColumn<?, ?> tableOfTrackColumnNameOfTrack;

    @FXML
    private TableColumn<?, ?> tableOfTrackColumnTime;

    @FXML
    void initialize() {

        addTrackButton.setOnAction(actionEvent -> {
            String name = addTrackFieldName.getText();
            String genreName = addTrackFieldGenre.getText();
            String author = addTrackFieldArtist.getText();
            String duration = addTrackFieldTime.getText();
            String album = addTrackFieldAlbum.getText();

            if (!name.equals("") && !genreName.equals("") && !author.equals("") && !duration.equals("") && !album.equals("")) {
                Genre genre = Genre.builder().name(genreName).build();

                Track track = Track.builder()
                        .name(name)
                        .author(author)
                        .album(album)
                        .duration(Double.parseDouble(duration))
                        .genre(genre)
                        .build();

                try {
                    TrackDAO.getInstance().save(track);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        refreshTableButton.setOnAction(actionEvent -> {
            //TODO: code to refresh table when push the button
        });

        {//find funcs
            FindTrackByNameButton.setOnAction(actionEvent -> {
                String name = FindTrackByNameField.getText();
                if (!name.equals("")) {
                    try {
                        Set<Track> trackSet = TrackDAO.getInstance().findByName(name);
                        fillTableWithTracks(trackSet);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });

            findTrackByIdButton.setOnAction(actionEvent -> {
                String id = findTrackByIdField.getText();
                if (!id.equals("")) {
                    try {
                        Set<Track> trackSet = new HashSet<>();
                        TrackDAO.getInstance().findById(Integer.parseInt(id)).ifPresent(trackSet::add);
                        fillTableWithTracks(trackSet);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        { //delete funcs
            deleteTrackByIdButton.setOnAction(actionEvent -> {
                deleteTrackByIdField.getId();

                String id = deleteTrackByIdField.getId();
                if (!id.equals("")) {
                    try {
                        TrackDAO.getInstance().deleteById(Integer.parseInt(id));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private void fillTableWithTracks(Set<Track> tracks) {
        ObservableList<Track> observableList = new TrackObservableList();
        observableList.addAll(tracks);
        tableOfTrack.setItems(observableList);
        tableOfTrack.refresh();
    }
}
