package com.example.simple_mvc_project.model;

import com.example.simple_mvc_project.utility.Properties;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    private static final String URL = Properties.getInstance().getConnectorProperties().getURL();
    private static final String USER = Properties.getInstance().getConnectorProperties().getUser();
    private static final String PASSWORD = Properties.getInstance().getConnectorProperties().getPassword();
    private Connection connection;

    private Connector() {
        try {
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
