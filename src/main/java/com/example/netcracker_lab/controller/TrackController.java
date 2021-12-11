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
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@FieldDefaults(level = AccessLevel.PRIVATE)
public class TrackController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button findTrackByNameButton;

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
    void initialize() throws SQLException {
        tableOfTrackColumnAlbum.setCellValueFactory(new PropertyValueFactory<>("album"));
        tableOfTrackColumnArtist.setCellValueFactory(new PropertyValueFactory<>("author"));
        tableOfTrackColumnGenre.setCellValueFactory(new PropertyValueFactory<>("genre"));
        tableOfTrackColumnID.setCellValueFactory(new PropertyValueFactory<>("id"));
        tableOfTrackColumnNameOfTrack.setCellValueFactory(new PropertyValueFactory<>("name"));
        tableOfTrackColumnTime.setCellValueFactory(new PropertyValueFactory<>("duration"));

        refreshTable();

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
            try {
                refreshTable();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        {//find funcs
            findTrackByNameButton.setOnAction(actionEvent -> {
                String name = FindTrackByNameField.getText();
                if (!name.equals("")) {
                    try {
                        fillTableWithTracks(TrackDAO.getInstance().findByName(name));
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });

            findTrackByIdButton.setOnAction(actionEvent -> {
                String id = findTrackByIdField.getText();
                if (!id.equals("")) {
                    try {
                        List<Track> trackList = new ArrayList<>();
                        TrackDAO.getInstance().findById(Integer.parseInt(id)).ifPresent(trackList::add);
                        fillTableWithTracks(trackList);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        { //delete funcs
            deleteTrackByIdButton.setOnAction(actionEvent -> {
                String id = deleteTrackByIdField.getText();
                if (!id.equals("")) {
                    try {
                        TrackDAO.getInstance().deleteById(Integer.parseInt(id));
                        refreshTable();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private void refreshTable() throws SQLException {
        List<Track> trackList = TrackDAO.getInstance().findAll();
        ObservableList<Track> observableList = new TrackObservableList(trackList);
        tableOfTrack.setItems(observableList);
        tableOfTrack.refresh();
    }

    private void fillTableWithTracks(List<Track> tracks) {
        ObservableList<Track> observableList = new TrackObservableList(tracks);
        tableOfTrack.setItems(observableList);
        tableOfTrack.refresh();
    }
}
