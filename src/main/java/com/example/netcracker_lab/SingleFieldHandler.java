package com.example.netcracker_lab;

import com.example.netcracker_lab.model.DAO;
import com.example.netcracker_lab.model.GenreDAO;
import com.example.netcracker_lab.model.TrackDAO;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class SingleFieldHandler {
    private static final String findTrack = "track:";
    private static final String findGenre = "genre:";
    private static final String Id = "id:";

    public static List<Object> find(String string) {
        if (string.startsWith(findTrack)) {
            return find(string.replaceFirst(findTrack, ""), TrackDAO.getInstance());
        }

        if (string.startsWith(findGenre)) {
            return find(string.replaceFirst(findGenre, ""), GenreDAO.getInstance());
        }

        List<Object> objectList = new LinkedList<>();

        objectList.addAll(find(string, TrackDAO.getInstance()));
        objectList.addAll(find(string, GenreDAO.getInstance()));

        return objectList;
    }

    private static List<Object> find(String string, DAO<?> dao) {
        if (string.startsWith(Id)) {
            return findId(string.replaceFirst(Id, ""), dao);
        }

        List<Object> objectList = new LinkedList<>();

        try {
            objectList.addAll(dao.findByTemplate("%" + string + "%"));
        } catch (SQLException ignored) {

        }
        objectList.addAll(findId(string, dao));
        return objectList;
    }

    private static List<Object> findId(String string, DAO<?> dao) {
        List<Object> objectList = new LinkedList<>();

        try {
            dao.findById(Integer.parseInt(string)).ifPresent(objectList::add);
        } catch (SQLException | NumberFormatException ignored) {

        }

        return objectList;
    }
}
