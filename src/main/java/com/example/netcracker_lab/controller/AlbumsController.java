package com.example.netcracker_lab.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

public class AlbumsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableColumn<?, ?> albumColumnOfAlbumTable;

    @FXML
    private TableView<?> albumTable;

    @FXML
    private ChoiceBox<?> choiseField;

    @FXML
    private TableColumn<?, ?> durationColumnOfAlbumTable;

    @FXML
    private Button findAlbumButton;

    @FXML
    private Button findAlbumByNameButton;

    @FXML
    private TextField findAlbumByNameField;

    @FXML
    private TableColumn<?, ?> idColumnOfAlbumTable;

    @FXML
    private Button refreshButton;

    @FXML
    private TableColumn<?, ?> trackColumnOfAlbumTable;

    @FXML
    void initialize() {



    }

}
