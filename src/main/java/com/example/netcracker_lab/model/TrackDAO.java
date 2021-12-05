package com.example.netcracker_lab.model;

import com.example.netcracker_lab.pojo.Genre;
import com.example.netcracker_lab.pojo.Track;
import com.example.netcracker_lab.utility.Properties;
import lombok.SneakyThrows;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class TrackDAO implements DAO<Track> {

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
    private TrackDAO() {
        save = connection.prepareStatement(Properties.getInstance().getTrackDAOProperties().getSave());
        findByName = connection.prepareStatement(Properties.getInstance().getTrackDAOProperties().getFindByName());
        delete = connection.prepareStatement(Properties.getInstance().getTrackDAOProperties().getDelete());
        deleteById = connection.prepareStatement(Properties.getInstance().getTrackDAOProperties().getDeleteById());
        findAll = connection.prepareStatement(Properties.getInstance().getTrackDAOProperties().getFindAll());
        findById = connection.prepareStatement(Properties.getInstance().getTrackDAOProperties().getFindById());
        findByTemplate = connection.prepareStatement(Properties.getInstance().getTrackDAOProperties().getFindByTemplate());
        update = connection.prepareStatement(Properties.getInstance().getTrackDAOProperties().getUpdate());
    }

    public static TrackDAO getInstance() {
        return TrackDAOHandler.TRACK_DAO_INSTANCE;
    }

    private static Genre findGenre(Track track) throws SQLException {
        Genre genre = track.getGenre();
        if (genre.getId() == null) {
            Set<Genre> genreSet = GenreDAO.getInstance().findByName(genre.getName());
            if (genreSet.size() != 1) throw new RuntimeException("No such genre, or several genres with common name");
            genre = genreSet.stream().findFirst().get();
        }
        return genre;
    }

    @Override
    public Track save(Track object) throws SQLException {
        executeSaveOrDelete(object, save);
        return object;
    }

    @Override
    public void delete(Track object) throws SQLException {
        executeSaveOrDelete(object, delete);
    }

    private void executeSaveOrDelete(Track object, PreparedStatement save) throws SQLException {
        save.setString(1, object.getName());
        save.setString(2, object.getAuthor());
        save.setString(3, object.getAlbum());
        save.setDouble(4, object.getDuration());
        save.setInt(5, findGenre(object).getId());

        save.executeUpdate();
        save.clearParameters();
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
        while (resultSet.next()) {
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
        return getTracks(name, findByName);
    }

    @Override
    public Set<Track> findByTemplate(String template) throws SQLException {
        return getTracks(template, findByTemplate);
    }

    private Set<Track> getTracks(String template, PreparedStatement statement) throws SQLException {
        statement.setString(1, template);

        ResultSet resultSet = statement.executeQuery();

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

        statement.clearParameters();
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

    private static class TrackDAOHandler {
        public static final TrackDAO TRACK_DAO_INSTANCE = new TrackDAO();
    }
}
