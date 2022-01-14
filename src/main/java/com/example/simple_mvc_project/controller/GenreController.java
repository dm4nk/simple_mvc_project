package com.example.simple_mvc_project.controller;

import com.example.simple_mvc_project.Application;
import com.example.simple_mvc_project.model.GenreDAO;
import com.example.simple_mvc_project.pojo.Genre;
import com.example.simple_mvc_project.view.GenreObservableList;
import com.example.simple_mvc_project.view.IDHolder;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class GenreController implements Controller<Genre> {

    private final GenreDAO genreDAO;
    @FXML
    public Button back;
    @FXML
    public Button updateGenre;
    @FXML
    public TextField updateGenreField;
    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;
    @FXML
    private TableView<Genre> genreTable;
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

    public GenreController(GenreDAO genreDAO) {
        this.genreDAO = genreDAO;
    }

    public GenreController() {
        genreDAO = GenreDAO.getInstance();
    }

    @FXML
    void initialize() throws SQLException {
        genreColumnOfGenreTable.setCellValueFactory(new PropertyValueFactory<>("name"));
        idColumnOfGenreTable.setCellValueFactory(new PropertyValueFactory<>("id"));
        refreshTable();

        addGenreButton.setOnAction(event -> {
            String name = addGenreField.getText();
            try {
                add(name);
                refreshTable();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        {//finds
            findGenreByIdButton.setOnAction(actionEvent -> {
                String id = findGenreByIdField.getText();
                try {
                    fillTableWithGenres(findById(id));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });

            findGenreByNameButton.setOnAction(actionEvent -> {
                String name = findGenreByNameField.getText();
                try {
                    fillTableWithGenres(findByName(name));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });

            findGenreByTemplateButton.setOnAction(actionEvent -> {
                String template = findGenreByTemplateField.getText();
                try {
                    fillTableWithGenres(findByTemplate(template));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            });
        }

        deleteGenreByIdButton.setOnAction(actionEvent -> {
            String id = deleteGenreByIdField.getText();
            try {
                deleteById(id);
                refreshTable();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        refreshButton.setOnAction(actionEvent -> {
            try {
                refreshTable();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        back.setOnAction(actionEvent -> {
            back.getScene().getWindow().hide();

            Stage stage = new Stage();
            FXMLLoader loaderr = new FXMLLoader(Application.class.getResource("sample.fxml"));

            try {
                Scene scenee = new Scene(loaderr.load(), 700, 500);
                stage.setScene(scenee);
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.show();
        });

        updateGenre.setOnAction(actionEvent -> {
            Stage stage = new Stage();
            FXMLLoader loaderr = new FXMLLoader(Application.class.getResource("updateGenre.fxml"));

            IDHolder.getInstance().setId(Integer.valueOf(updateGenreField.getText()));

            try {
                Scene scenee = new Scene(loaderr.load());
                stage.setScene(scenee);
            } catch (IOException e) {
                e.printStackTrace();
            }
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.initOwner(updateGenreField.getScene().getWindow());
            stage.showAndWait();
            try {
                refreshTable();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public void deleteById(String id) throws SQLException {
        if (!id.equals(""))
            genreDAO.deleteById(Integer.parseInt(id));
    }

    public List<Genre> findByTemplate(String template) throws SQLException {
        if (!template.equals("")) {
            return genreDAO.findByTemplate(template);
        }
        return new ArrayList<>();
    }

    public List<Genre> findByName(String name) throws SQLException {
        if (!name.equals("")) {
            return genreDAO.findByName(name);
        }
        return new ArrayList<>();
    }

    public List<Genre> findById(String id) throws SQLException {
        List<Genre> genres = new ArrayList<>();
        if (!id.equals(""))
            genreDAO.findById(Integer.parseInt(id)).ifPresent(genres::add);
        return genres;
    }

    public void add(String... name) throws SQLException {
        if (!name[0].equals("")) {
            Genre genre = Genre.builder().name(name[0]).build();
            genreDAO.save(genre);
        }
    }

    private void refreshTable() throws SQLException {
        fillTableWithGenres(genreDAO.findAll());
    }

    private void fillTableWithGenres(List<Genre> genres) {
        ObservableList<Genre> observableList = new GenreObservableList(genres);
        genreTable.setItems(observableList);
        genreTable.refresh();
    }
}
