package com.example.netcracker_lab.controller;

import com.example.netcracker_lab.model.GenreDAO;
import com.example.netcracker_lab.pojo.Genre;
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


public class GenreController{

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TableView<?> GenreTable;

    @FXML
    private Button addGenreButton;

    @FXML
    private TextField addGenreField;

    @FXML
    private Button deleteGenreByIdButton;

    @FXML
    private TextField deleteGenreByIdField;

    @FXML
    private Button findGenreByIdButton;

    @FXML
    private TextField findGenreByIdField;

    @FXML
    private Button findGenreByNameButton;

    @FXML
    private TextField findGenreByNameField;

    @FXML
    private Button findGenreByTemplateButton;

    @FXML
    private TextField findGenreByTemplateField;

    @FXML
    private TableColumn<?, ?> genreColumnOfGenreTable;

    @FXML
    private TableColumn<?, ?> idColumnOfGenreTable;

    @FXML
    private Button refreshButton;

    @FXML
    void initialize() {


    }
}
