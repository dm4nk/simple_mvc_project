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
        assert albumColumnOfAlbumTable != null : "fx:id=\"albumColumnOfAlbumTable\" was not injected: check your FXML file 'albums.fxml'.";
        assert albumTable != null : "fx:id=\"albumTable\" was not injected: check your FXML file 'albums.fxml'.";
        assert choiseField != null : "fx:id=\"choiseField\" was not injected: check your FXML file 'albums.fxml'.";
        assert durationColumnOfAlbumTable != null : "fx:id=\"durationColumnOfAlbumTable\" was not injected: check your FXML file 'albums.fxml'.";
        assert findAlbumButton != null : "fx:id=\"findAlbumButton\" was not injected: check your FXML file 'albums.fxml'.";
        assert findAlbumByNameButton != null : "fx:id=\"findAlbumByNameButton\" was not injected: check your FXML file 'albums.fxml'.";
        assert findAlbumByNameField != null : "fx:id=\"findAlbumByNameField\" was not injected: check your FXML file 'albums.fxml'.";
        assert idColumnOfAlbumTable != null : "fx:id=\"idColumnOfAlbumTable\" was not injected: check your FXML file 'albums.fxml'.";
        assert refreshButton != null : "fx:id=\"refreshButton\" was not injected: check your FXML file 'albums.fxml'.";
        assert trackColumnOfAlbumTable != null : "fx:id=\"trackColumnOfAlbumTable\" was not injected: check your FXML file 'albums.fxml'.";

    }

}
