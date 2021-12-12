package com.example.netcracker_lab.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

//TODO: при начале реализации контроллера убрать assert'ы

public class SampleController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button gotoGenreButton;

    @FXML
    private Button gotoTrackButton;

    @FXML
    void initialize() {

        gotoGenreButton.setOnAction(event -> {
            gotoGenreButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("genre.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });

        gotoTrackButton.setOnAction(ActionEvent -> { //переход
            gotoTrackButton.getScene().getWindow().hide();


            FXMLLoader loader = new FXMLLoader();
            Parent root = loader.getRoot();
            loader.setLocation(getClass().getResource("track.fxml"));

            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });
    }

}
