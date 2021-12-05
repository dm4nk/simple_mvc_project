package com.example.netcracker_lab.controller;

import com.example.netcracker_lab.model.TrackDAO;
import com.example.netcracker_lab.pojo.Track;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.sql.SQLException;
import java.util.Optional;
import java.util.Set;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class TrackController implements Controller<Track> {
    static TrackController instance = new TrackController();
    final TrackDAO trackDAO;

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
    public Optional<Track> findByName(String name) throws SQLException {
        return Optional.empty();
    }

    @Override
    public Track update(Track oldObject, Track newObject) {
        return null;
    }


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
    private Button deleteTrackByIdOrNameButton;

    @FXML
    private TextField deleteTrackByIdOrNameField;

    @FXML
    private Button findTrackByIdButton;

    @FXML
    private TextField findTrackByIdField;

    @FXML
    private TableView<?> tableOfTrack;

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
        //TODO: при начале реализации контроллера убрать assert'ы
        assert FindTrackByNameButton != null : "fx:id=\"FindTrackByNameButton\" was not injected: check your FXML file 'track.fxml'.";
        assert FindTrackByNameField != null : "fx:id=\"FindTrackByNameField\" was not injected: check your FXML file 'track.fxml'.";
        assert addTrackButton != null : "fx:id=\"addTrackButton\" was not injected: check your FXML file 'track.fxml'.";
        assert addTrackFieldAlbum != null : "fx:id=\"addTrackFieldAlbum\" was not injected: check your FXML file 'track.fxml'.";
        assert addTrackFieldArtist != null : "fx:id=\"addTrackFieldArtist\" was not injected: check your FXML file 'track.fxml'.";
        assert addTrackFieldGenre != null : "fx:id=\"addTrackFieldGenre\" was not injected: check your FXML file 'track.fxml'.";
        assert addTrackFieldName != null : "fx:id=\"addTrackFieldName\" was not injected: check your FXML file 'track.fxml'.";
        assert addTrackFieldTime != null : "fx:id=\"addTrackFieldTime\" was not injected: check your FXML file 'track.fxml'.";
        assert deleteTrackByIdOrNameButton != null : "fx:id=\"deleteTrackByIdOrNameButton\" was not injected: check your FXML file 'track.fxml'.";
        assert deleteTrackByIdOrNameField != null : "fx:id=\"deleteTrackByIdOrNameField\" was not injected: check your FXML file 'track.fxml'.";
        assert findTrackByIdButton != null : "fx:id=\"findTrackByIdButton\" was not injected: check your FXML file 'track.fxml'.";
        assert findTrackByIdField != null : "fx:id=\"findTrackByIdField\" was not injected: check your FXML file 'track.fxml'.";
        assert tableOfTrack != null : "fx:id=\"tableOfTrack\" was not injected: check your FXML file 'track.fxml'.";
        assert tableOfTrackColumnAlbum != null : "fx:id=\"tableOfTrackColumnAlbum\" was not injected: check your FXML file 'track.fxml'.";
        assert tableOfTrackColumnArtist != null : "fx:id=\"tableOfTrackColumnArtist\" was not injected: check your FXML file 'track.fxml'.";
        assert tableOfTrackColumnGenre != null : "fx:id=\"tableOfTrackColumnGenre\" was not injected: check your FXML file 'track.fxml'.";
        assert tableOfTrackColumnID != null : "fx:id=\"tableOfTrackColumnID\" was not injected: check your FXML file 'track.fxml'.";
        assert tableOfTrackColumnNameOfTrack != null : "fx:id=\"tableOfTrackColumnNameOfTrack\" was not injected: check your FXML file 'track.fxml'.";
        assert tableOfTrackColumnTime != null : "fx:id=\"tableOfTrackColumnTime\" was not injected: check your FXML file 'track.fxml'.";

    }



}
