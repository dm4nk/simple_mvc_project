package com.example.netcracker_lab.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    //todo: это запихнуть в пропертис
    private static final String URL = "jdbc:mysql://localhost:3306/musical_schema";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";
    private Connection connection;

    private Connector() {
        try {
            //todo: lightConnection
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return ConnectorHandler.CONNECTOR_INSTANCE.connection;
    }

    private static class ConnectorHandler {
        public static final Connector CONNECTOR_INSTANCE = new Connector();
    }
}
