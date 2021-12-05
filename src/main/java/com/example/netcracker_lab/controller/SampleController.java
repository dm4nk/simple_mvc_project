package com.example.netcracker_lab.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import com.example.netcracker_lab.HelloApplication;
import com.example.netcracker_lab.HelloController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

//TODO: при начале реализации контроллера убрать assert'ы

public class SampleController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button gotoAlbumButton;

    @FXML
    private Button gotoDataBaseButton;

    @FXML
    private Button gotoGenreOfMusicButton;

    @FXML
    private Button gotoTrackButton;

    @FXML
    private Button testButtonOne;

    @FXML
    private Button testButtonTwo;

    @FXML
    void initialize() {

        testButtonOne.setOnAction(event -> {
            System.out.println("test button one is work"); //тест кнопки
        });

        gotoTrackButton.setOnAction(event -> { //переход
            gotoTrackButton.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("track.fxml"));
           // loader.setLocation(HelloApplication.class.getResource("track.fxml"));

            try {
                loader.load();

            }catch (IOException e){
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });
    }

}
