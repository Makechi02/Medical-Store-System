package com.medical.store.connections;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Connections {

    public static Connection connection;
    public static Statement statement;

    public Connections() {

        var properties = new Properties();

        try (Reader in = Files.newBufferedReader(Path.of("database.properties"), StandardCharsets.UTF_8)) {

            properties.load(in);
            String drivers = properties.getProperty("jdbc.drivers");
            if (drivers != null) System.setProperty("jdbc.drivers", "drivers");
            String url = properties.getProperty("jdbc.url");
            String username = properties.getProperty("jdbc.username");
            String password = properties.getProperty("jdbc.password");
            connection = DriverManager.getConnection(url, username, password);
            statement = connection.createStatement();

        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public Connection getConnection() {
        return connection;
    }

    public Statement getStatement() {
        return statement;
    }

}