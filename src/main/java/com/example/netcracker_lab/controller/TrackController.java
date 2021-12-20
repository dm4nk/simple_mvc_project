package com.example.netcracker_lab.controller;

import com.example.netcracker_lab.Application;
import com.example.netcracker_lab.model.TrackDAO;
import com.example.netcracker_lab.pojo.Genre;
import com.example.netcracker_lab.pojo.Track;
import com.example.netcracker_lab.view.IDHolder;
import com.example.netcracker_lab.view.TrackObservableList;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class TrackController implements Controller<Track> {
    private final TrackDAO trackDAO;
    @FXML
    public Button back;
    @FXML
    public TextField updateTrackByIdField;
    @FXML
    public Button updateTrackById;
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private Button findTrackByNameButton;
    @FXML
    private TextField findTrackByNameFieldText;
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

    public TrackController(TrackDAO trackDAO) {
        this.trackDAO = trackDAO;
    }

    public TrackController() {
        this.trackDAO = TrackDAO.getInstance();
    }

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
            String author = addTrackFieldArtist.getText();
            String album = addTrackFieldAlbum.getText();
            String duration = addTrackFieldTime.getText();
            String genreName = addTrackFieldGenre.getText();
            try {
                add(name, author, album, duration, genreName);
                refreshTable();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        {//find funcs
            findTrackByNameButton.setOnAction(actionEvent -> {
                String name = findTrackByNameFieldText.getText();
                try {
                    fillTableWithTracks(findByName(name));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });

            findTrackByIdButton.setOnAction(actionEvent -> {
                String id = findTrackByIdField.getText();
                try {
                    fillTableWithTracks(findById(id));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });

            findTrackByTemplateButton.setOnAction(actionEvent -> {
                String template = templateField.getText();
                try {
                    fillTableWithTracks(findByTemplate(template));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        }

        { //delete funcs
            deleteTrackByIdButton.setOnAction(actionEvent -> {
                String id = deleteTrackByIdField.getText();
                try {
                    deleteById(id);
                    refreshTable();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        }

        refreshTableButton.setOnAction(actionEvent -> {
            try {
                refreshTable();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        back.setOnAction(actionEvent -> {
            back.getScene().getWindow().hide();

            Stage stage = new Stage();
            FXMLLoader loaderr = new FXMLLoader(Application.class.getResource("sample.fxml"));

            try {
                Scene scenee = new Scene(loaderr.load(), 700, 500);
                stage.setScene(scenee);
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.show();
        });

        updateTrackById.setOnAction(actionEvent -> {
            Stage stage = new Stage();
            FXMLLoader loaderr = new FXMLLoader(Application.class.getResource("update.fxml"));

            IDHolder.getInstance().setId(Integer.valueOf(updateTrackByIdField.getText()));

            try {
                Scene scenee = new Scene(loaderr.load());
                stage.setScene(scenee);
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(updateTrackById.getScene().getWindow());
            stage.showAndWait();
            try {
                refreshTable();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public void deleteById(String id) throws SQLException {
        if (!id.equals(""))
            trackDAO.deleteById(Integer.parseInt(id));
    }

    public List<Track> findByTemplate(String template) throws SQLException {
        if (!template.equals("")) {
            return trackDAO.findByTemplate(template);
        }
        return new ArrayList<>();
    }

    public List<Track> findById(String id) throws SQLException {
        List<Track> trackList = new ArrayList<>();
        if (!id.equals(""))
            trackDAO.findById(Integer.parseInt(id)).ifPresent(trackList::add);
        return trackList;
    }

    public List<Track> findByName(String name) throws SQLException {
        if (!name.equals("")) {
            return trackDAO.findByName(name);
        }
        return new ArrayList<>();
    }

    public void add(String... args) throws SQLException {
        if (Arrays.stream(args).noneMatch(arg -> arg.equals(""))) {
            Genre genre = Genre.builder().name(args[4]).build();

            Track track = Track.builder()
                    .name(args[0])
                    .author(args[1])
                    .album(args[2])
                    .duration(Double.parseDouble(args[3]))
                    .genre(genre)
                    .build();
            trackDAO.save(track);
        }
    }

    private void refreshTable() throws SQLException {
        fillTableWithTracks(trackDAO.findAll());
    }

    private void fillTableWithTracks(List<Track> tracks) {
        ObservableList<Track> observableList = new TrackObservableList(tracks);
        tableOfTrack.setItems(observableList);
        tableOfTrack.refresh();
    }
}
