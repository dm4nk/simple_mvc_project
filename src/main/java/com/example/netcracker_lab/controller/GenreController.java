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

        addGenreButton.setOnAction(event -> {
           if (addGenreField.getText().trim().length() == 0) {
               System.out.println("Field is null");
           }
           else {
               addGenreField.getText();
               System.out.println("ADD field has been got");
           }

        });


        {//finds
            findGenreByIdButton.setOnAction(actionEvent -> {

                if(findGenreByIdField.getText().trim().length() == 0){
                    System.out.println("Field is null");
                }
                else {
                    findGenreByIdField.getText();
                    System.out.println("ID field has been got");
                }


            });

            findGenreByNameButton.setOnAction(actionEvent -> {

                if(findGenreByNameField.getText().trim().length() == 0) {
                    System.out.println("Field is null");}
                else {
                    findGenreByNameField.getText();
                    System.out.println("Name field has been got");
                }


            });

            findGenreByTemplateButton.setOnAction(actionEvent -> {
                if(findGenreByTemplateField.getText().trim().length() == 0){
                    System.out.println("Field is null");
                }
                else {
                    findGenreByTemplateField.getText();
                    System.out.println("Template field has been got");
                }


            });
        }

        deleteGenreByIdButton.setOnAction(actionEvent -> {
            if (deleteGenreByIdField.getText().trim().length()==0){
                System.out.println("Field is null");
            }
            else{
                deleteGenreByIdField.getText();
                System.out.println("Delete field has been got");
            }


        });

        refreshButton.setOnAction(actionEvent -> {
            GenreTable.refresh();

        });

    }
}
