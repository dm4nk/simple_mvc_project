package com.example.netcracker_lab.model;

import com.example.netcracker_lab.pojo.Genre;
import com.example.netcracker_lab.pojo.Track;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;

import java.sql.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
public class TrackDAO implements DAO<Track> {

    static String URL = "jdbc:mysql://localhost:3306/musical_schema";
    static String USER = "root";
    static String PASSWORD = "123456";

    static TrackDAO instance;
    static Connection connection;

    static PreparedStatement save;
    static PreparedStatement findByName;

    static {
        try {
            instance = new TrackDAO();
            connection = DriverManager.getConnection(URL, USER, PASSWORD);

            save = connection.prepareStatement(
                    "INSERT INTO track(name,author,album,duration, genre_id) VALUES(?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            findByName = connection.prepareStatement(
                    "SELECT * FROM track WHERE name = ?"
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private TrackDAO() {
    }

    public static TrackDAO getInstance() {
        return instance;
    }

    @Override
    public Track save(Track object) throws SQLException {
        save.setString(1, object.getName());
        save.setString(2, object.getAuthor());
        save.setString(3, object.getAlbum());
        save.setDouble(4, object.getDuration());

        //это вообще ок? или нужно через контроллер?

        if(object.getGenre().getId() == null){
            Set<Genre> genreSet = GenreDAO.getInstance().findByName(object.getGenre().getName());
            if(genreSet.size() != 1) throw new RuntimeException("Unique genre name violation (probably)");

            Genre genre = genreSet.stream().findFirst().orElse(null);

            if(genre == null) throw new NullPointerException("No such genre");

            Integer genreId = genre.getId();
            save.setInt(5, genreId);
        }
        else {
            save.setInt(5, object.getGenre().getId());
        }

        int affectedRows = save.executeUpdate();

        save.clearParameters();

        //todo: may be strange logic....
        return object;
    }

    @Override
    public void delete(Track object) {

    }

    @Override
    public Set<Track> findAll() {
        return null;
    }

    @Override
    public Optional<Track> findById(Integer id) {
        return Optional.empty();
    }

    @Override
    public Set<Track> findByName(String name) throws SQLException {
        findByName.setString(1, name);

        ResultSet resultSet = findByName.executeQuery();

        Set<Track> trackSet = new HashSet<>();

        while (resultSet.next())
            trackSet.add(
                    Track.builder()
                            .id(resultSet.getInt(1))
                            .name(resultSet.getString(2))
                            .author(resultSet.getString(3))
                            .album(resultSet.getString(4))
                            .duration(resultSet.getDouble(5))
                            .genre(GenreDAO.getInstance().findById(resultSet.getInt(6)).orElse(null))
                            .build()
            );

        findByName.clearParameters();
        return trackSet;
    }

    @Override
    public Track update(Track oldObject, Track newObject) {
        return null;
    }
}
