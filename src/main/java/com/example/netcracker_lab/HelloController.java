package com.example.netcracker_lab;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class HelloController {

    //TODO: поменять модификаторы доступа если этот контролер будет использоваться

    @FXML
    public Label welcomeText;

    @FXML
    public void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");


        System.out.println("onHelloButtonClick is work");
    }
}