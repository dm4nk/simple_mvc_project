package com.example.netcracker_lab;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("genre.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 500);
        stage.setTitle("MDB");
        stage.setScene(scene);
        stage.show();
    }
}