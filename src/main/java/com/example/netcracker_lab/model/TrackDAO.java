package com.example.netcracker_lab.model;

import com.example.netcracker_lab.pojo.Genre;
import com.example.netcracker_lab.pojo.Track;

import java.sql.*;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class TrackDAO extends AbstractDAO implements DAO<Track> {

    private static TrackDAO instance;

    private static PreparedStatement save;
    private static PreparedStatement findByName;
    private static PreparedStatement delete;
    private static PreparedStatement deleteById;
    private static PreparedStatement findAll;
    private static PreparedStatement findById;
    private static PreparedStatement update;

    static {
        try {
            instance = new TrackDAO();

            save = connection.prepareStatement(
                    "INSERT INTO track(name,author,album,duration, genre_id) VALUES(?,?,?,?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            findByName = connection.prepareStatement(
                    "SELECT * FROM track WHERE name = ?"
            );
            delete = connection.prepareStatement(
                    "DELETE FROM track WHERE name = ? AND author = ? AND album = ? AND duration = ? AND genre_id = ?"
            );
            deleteById = connection.prepareStatement(
                    "DELETE FROM track WHERE idtrack = ?"
            );
            findAll = connection.prepareStatement(
                    "SELECT * FROM track"
            );
            findById = connection.prepareStatement(
                    "SELECT * FROM track WHERE idtrack = ?"
            );
            update = connection.prepareStatement(
                    "UPDATE track SET name = ?, author = ?, album = ?, duration = ?, genre_id = ? " +
                            "WHERE name = ? AND author = ? AND album = ? AND duration = ? AND genre_id = ? "
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
        save.setInt(5, findGenre(object).getId());

        save.executeUpdate();
        save.clearParameters();
        return object;
    }

    @Override
    public void delete(Track object) throws SQLException {
        delete.setString(1, object.getName());
        delete.setString(2, object.getAuthor());
        delete.setString(3, object.getAlbum());
        delete.setDouble(4, object.getDuration());
        delete.setInt(5, findGenre(object).getId());

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
    public Set<Track> findAll() throws SQLException {
        Set<Track> trackSet = new HashSet<>();
        ResultSet resultSet = findAll.executeQuery();
        while (resultSet.next()){
            trackSet.add(
              Track.builder()
                      .id(resultSet.getInt(1))
                      .name(resultSet.getString(2))
                      .author(resultSet.getString(3))
                      .album(resultSet.getString(4))
                      .duration(resultSet.getDouble(5))
                      .genre(GenreDAO.getInstance().findById(resultSet.getInt(6)).get())
                      .build()
            );
        }
        return trackSet;
    }

    @Override
    public Optional<Track> findById(Integer id) throws SQLException {
        findById.setInt(1, id);
        ResultSet resultSet = findById.executeQuery();
        resultSet.next();
        resultSet.clearWarnings();
        return Optional.ofNullable(Track.builder()
                .id(resultSet.getInt(1))
                .name(resultSet.getString(2))
                .author(resultSet.getString(3))
                .album(resultSet.getString(4))
                .duration(resultSet.getDouble(5))
                .genre(GenreDAO.getInstance().findById(resultSet.getInt(6)).get())
                .build());
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
    public Track update(Track oldObject, Track newObject) throws SQLException {
        update.setString(1, newObject.getName());
        update.setString(2, newObject.getAuthor());
        update.setString(3, newObject.getAlbum());
        update.setDouble(4, newObject.getDuration());
        update.setInt(5, findGenre(newObject).getId());

        update.setString(6, oldObject.getName());
        update.setString(7, oldObject.getAuthor());
        update.setString(8, oldObject.getAlbum());
        update.setDouble(9, oldObject.getDuration());
        update.setInt(10, findGenre(oldObject).getId());

        update.executeUpdate();
        update.clearParameters();

        return newObject;
    }

    private static Genre findGenre(Track track) throws SQLException {
        Genre genre = track.getGenre();
        if(genre.getId() == null){
            Set<Genre> genreSet = GenreDAO.getInstance().findByName(genre.getName());
            if(genreSet.size() != 1) throw new RuntimeException("No such genre, or several genres with common name");
            genre = genreSet.stream().findFirst().get();
        }
        return genre;
    }
}
