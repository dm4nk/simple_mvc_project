package com.example.netcracker_lab;

import com.example.netcracker_lab.model.TrackDAO;
import com.example.netcracker_lab.pojo.Genre;
import com.example.netcracker_lab.pojo.Track;

import java.sql.*;
import java.util.Set;

public class SandBox {
    public static void main(String[] args) throws SQLException {

        Set<Track> trackSet = TrackDAO.getInstance().findByName("name");

        System.out.println(trackSet);
    }
}
