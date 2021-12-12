package com.example.netcracker_lab.controller;

import com.example.netcracker_lab.model.GenreDAO;
import com.example.netcracker_lab.pojo.Genre;
import com.example.netcracker_lab.view.GenreObservableList;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;


public class GenreController implements Controller<Genre> {

    private final GenreDAO genreDAO;
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
