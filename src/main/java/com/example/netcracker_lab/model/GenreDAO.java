package com.example.netcracker_lab.model;

import com.example.netcracker_lab.pojo.Genre;
import com.example.netcracker_lab.utility.Properties;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class GenreDAO implements DAO<Genre> {

    private static final Connection connection = Connector.getConnection();

    private final PreparedStatement save;
    private final PreparedStatement findByName;
    private final PreparedStatement delete;
    private final PreparedStatement deleteById;
    private final PreparedStatement findAll;
    private final PreparedStatement findById;
    private final PreparedStatement findByTemplate;
    private final PreparedStatement update;

    @SneakyThrows
    private GenreDAO() {
        Properties properties = Properties.getInstance();

        save = connection.prepareStatement(properties.getGenreDAOProperties().getSave());
        findByName = connection.prepareStatement(properties.getGenreDAOProperties().getFindByName());
        delete = connection.prepareStatement(properties.getGenreDAOProperties().getDelete());
        deleteById = connection.prepareStatement(properties.getGenreDAOProperties().getDeleteById());
        findAll = connection.prepareStatement(properties.getGenreDAOProperties().getFindAll());
        findById = connection.prepareStatement(properties.getGenreDAOProperties().getFindById());
        findByTemplate = connection.prepareStatement(properties.getGenreDAOProperties().getFindByTemplate());
        update = connection.prepareStatement(properties.getGenreDAOProperties().getUpdate());
    }

    public static GenreDAO getInstance() {
        return GenreDAOHandler.GENRE_DAO_INSTANCE;
    }

    @Override
    public Genre save(Genre object) throws SQLException {
        save.setString(1, object.getName());

        //todo: add slf4j later...
        int affectedRows = save.executeUpdate();

        //todo: may be strange logic....
        save.clearParameters();
        return object;
    }

    @Override
    public void delete(Genre object) throws SQLException {
        delete.setString(1, object.getName());
        delete.executeUpdate();
        delete.clearParameters();
    }

    @Override
    public void deleteById(Integer id) throws SQLException {
        deleteById.setInt(1, id);
        deleteById.executeUpdate();
        deleteById.clearParameters();
    }

    @Override
    public List<Genre> findAll() throws SQLException {
        List<Genre> genreSet = new ArrayList<>();
        ResultSet resultSet = findAll.executeQuery();
        while (resultSet.next()) {
            genreSet.add(
                    Genre.builder()
                            .id(resultSet.getInt(1))
                            .name(resultSet.getString(2))
                            .build()
            );
        }
        return genreSet;
    }

    @Override
    public Optional<Genre> findById(Integer id) throws SQLException {
        Optional<Genre> genre;

        findById.setInt(1, id);
        ResultSet resultSet = findById.executeQuery();
        if (resultSet.next() == false)
            genre = Optional.empty();
        else
            genre = Optional.ofNullable(
                    Genre.builder()
                            .id(resultSet.getInt(1))
                            .name(resultSet.getString(2))
                            .build());
        findById.clearParameters();
        return genre;
    }

    @Override
    public List<Genre> findByName(String name) throws SQLException {
        return getGenres(name, findByName);
    }

    @Override
    public List<Genre> findByTemplate(String template) throws SQLException {
        return getGenres(template, findByTemplate);
    }

    private List<Genre> getGenres(String template, PreparedStatement statement) throws SQLException {
        statement.setString(1, template);
        ResultSet resultSet = statement.executeQuery();

        List<Genre> genreSet = new ArrayList<>();

        while (resultSet.next())
            genreSet.add(
                    Genre.builder()
                            .id(resultSet.getInt(1))
                            .name(resultSet.getString(2))
                            .build()
            );

        statement.clearParameters();
        return genreSet;
    }


    @Override
    public Genre update(Genre oldObject, Genre newObject) throws SQLException {
        update.setString(1, newObject.getName());
        update.setString(2, oldObject.getName());
        update.executeUpdate();
        update.clearParameters();
        return newObject;
    }

    private static class GenreDAOHandler {
        public static final GenreDAO GENRE_DAO_INSTANCE = new GenreDAO();
    }
}
