package com.example.netcracker_lab.controller;

import com.example.netcracker_lab.model.TrackDAO;
import com.example.netcracker_lab.pojo.Track;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.Set;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class TrackController implements Controller<Track> {
    static TrackController instance = new TrackController();
    final TrackDAO trackDAO;
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
    void initialize() {
        //TODO: при начале реализации контроллера убрать assert'ы

    }


}
