package com.example.netcracker_lab.model;

import com.example.netcracker_lab.pojo.Genre;

import java.sql.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class GenreDAO implements DAO<Genre> {

    private static final Connection connection = Connector.getConnection();

    private static PreparedStatement save;
    private static PreparedStatement findByName;
    private static PreparedStatement delete;
    private static PreparedStatement deleteById;
    private static PreparedStatement findAll;
    private static PreparedStatement findById;
    private static PreparedStatement update;

    //todo: убрать нафиг статик поле (отовсюду)
    static {
        try {
            //todo: SQL зарпросы вынести это в файлик properties

            save = connection.prepareStatement(
                    "INSERT INTO genre(name) VALUES(?)",
                    Statement.RETURN_GENERATED_KEYS);
            findByName = connection.prepareStatement(
                    "SELECT * FROM genre WHERE name = ?"
            );
            delete = connection.prepareStatement(
                    "DELETE FROM genre WHERE name = ?"
            );
            deleteById = connection.prepareStatement(
                    "DELETE FROM genre WHERE idgenre = ?"
            );
            findAll = connection.prepareStatement(
                    "SELECT * FROM genre"
            );
            findById = connection.prepareStatement(
                    "SELECT * FROM genre WHERE idgenre = ?"
            );
            update = connection.prepareStatement(
                    "UPDATE genre SET name = ? WHERE name = ?"
            );

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private GenreDAO() {

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
    public Set<Genre> findAll() throws SQLException {
        Set<Genre> genreSet = new HashSet<>();
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
        findById.setInt(1, id);
        ResultSet resultSet = findById.executeQuery();
        resultSet.next();
        findById.clearParameters();
        return Optional.ofNullable(
                Genre.builder()
                        .id(resultSet.getInt(1))
                        .name(resultSet.getString(2))
                        .build()
        );
    }

    @Override
    public Set<Genre> findByName(String name) throws SQLException {
        findByName.setString(1, name);

        ResultSet resultSet = findByName.executeQuery();

        Set<Genre> genreSet = new HashSet<>();

        while (resultSet.next())
            genreSet.add(
                    Genre.builder()
                            .id(resultSet.getInt(1))
                            .name(resultSet.getString(2))
                            .build()
            );

        findByName.clearParameters();
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
