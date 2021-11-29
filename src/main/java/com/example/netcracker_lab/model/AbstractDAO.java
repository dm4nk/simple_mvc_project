package com.example.netcracker_lab.model;

import java.sql.Connection;
import java.sql.SQLException;

import static java.sql.DriverManager.getConnection;

public abstract class AbstractDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/musical_schema";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    static Connection connection;

    static {
        try {
            connection = getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
