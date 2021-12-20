package com.example.netcracker_lab.controller;

import com.example.netcracker_lab.model.GenreDAO;
import com.example.netcracker_lab.pojo.Genre;
import com.example.netcracker_lab.view.IDHolder;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class UpdateGenreController {

    @FXML
    public TextField name;
    @FXML
    public Button update;

    @FXML
    void initialize() throws SQLException {
        Integer id = IDHolder.getInstance().getId();
        Genre genre = GenreDAO.getInstance().findById(id).orElse(null);
        if (genre != null) {
            name.setText(genre.getName());
        }

        update.setOnAction(actionEvent -> {
            if (genre != null) {
                Genre newGenre = Genre.builder().name(name.getText()).build();

                try {
                    GenreDAO.getInstance().update(genre.getId(), newGenre);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

                close();
            } else {
                close();
            }
        });
    }

    private void close() {
        Stage stage = (Stage) name.getScene().getWindow();
        stage.close();
    }
}
