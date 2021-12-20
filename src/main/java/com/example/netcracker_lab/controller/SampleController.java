package com.example.netcracker_lab.controller;

import com.example.netcracker_lab.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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

            Stage stage = new Stage();
            FXMLLoader loaderr = new FXMLLoader(Application.class.getResource("genre.fxml"));

            try {
                Scene scenee = new Scene(loaderr.load(),700,500);
                stage.setScene(scenee);
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.showAndWait();
        });

        gotoTrackButton.setOnAction(ActionEvent -> { //переход
            gotoTrackButton.getScene().getWindow().hide();

            Stage stage = new Stage();
            FXMLLoader loader = new FXMLLoader(Application.class.getResource("track.fxml"));

            try {
                Scene scene = new Scene(loader.load(),700,500);
                stage.setScene(scene);
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.show();

        });
    }

}
