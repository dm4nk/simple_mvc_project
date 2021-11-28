package com.example.netcracker_lab.model;

import com.example.netcracker_lab.pojo.Genre;
import lombok.AccessLevel;
import lombok.Synchronized;
import lombok.experimental.FieldDefaults;

import java.sql.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class GenreDAO implements DAO<Genre> {

    static String URL = "jdbc:mysql://localhost:3306/musical_schema";
    static String USER = "root";
    static String PASSWORD = "123456";

    static GenreDAO instance = new GenreDAO();
    static Connection connection;
    static PreparedStatement save;
    static PreparedStatement findByName;

    static {
        try {
            instance = new GenreDAO();
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            save = connection.prepareStatement(
                    "INSERT INTO genre(name) VALUES(?)",
                    Statement.RETURN_GENERATED_KEYS);
            findByName = connection.prepareStatement(
                    "SELECT * FROM genre WHERE name = ?"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private GenreDAO() {
    }

    public static GenreDAO getInstance() {
        return instance;
    }

    @Override
    public Genre save(Genre object) throws SQLException {
        save.setString(1, object.getName());

        int affectedRows = save.executeUpdate();

        //todo: may be strange logic....
        save.clearParameters();
        return object;
    }

    @Override
    @Synchronized
    public void delete(Genre object) {

    }

    @Override
    public Set<Genre> findAll() {
        return null;
    }

    @Override
    public Optional<Genre> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Genre update(Genre oldObject, Genre newObject) {
        return null;
    }

    @Override
    public Set<Genre> findByName(String name) throws SQLException {
        findByName.setString(1, name);

        ResultSet resultSet = findByName.executeQuery();
        resultSet.next();

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
}
