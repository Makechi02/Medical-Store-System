package com.medical.store.supplier.connection;


import com.medical.store.supplier.Supplier;

import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class SupplierConnection {

    private static SupplierConnection supplierConnection;

    private SupplierConnection() {}

    public static SupplierConnection getInstance() {
        if (supplierConnection == null) {
            supplierConnection = new SupplierConnection();
        }
        return supplierConnection;
    }

    public Connection getConnection() {
        var properties = new Properties();
        try (Reader in = Files.newBufferedReader(Path.of("database.properties"), StandardCharsets.UTF_8)) {
            properties.load(in);
            String drivers = properties.getProperty("jdbc.drivers");
            if (drivers != null) System.setProperty("jdbc.drivers", "drivers");
            String url = properties.getProperty("jdbc.url");
            String username = properties.getProperty("jdbc.username");
            String password = properties.getProperty("jdbc.password");
            return DriverManager.getConnection(url, username, password);
        } catch (IOException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void addNewSupplier(Supplier supplier) {
        Connection connection = this.getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("insert into suppliers (id, name,address,phone,email)values(?,?,?,?,?)");
            preparedStatement.setInt(1, supplier.getId());
            preparedStatement.setString(2, supplier.getName());
            preparedStatement.setString(3, supplier.getAddress());
            preparedStatement.setString(4, supplier.getPhone());
            preparedStatement.setString(5, supplier.getEmail());
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateSupplier(String id, String name, String address, String phone, String email) {
        Connection connection = this.getConnection();
        try {
            String query = "UPDATE suppliers SET id='" + id + "', name='" + name + "', address='" + address + "', phone='" + phone + "',email='" + email + "' WHERE id='" + id +"'";
            Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            statement.executeUpdate(query);
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void deleteSupplier(int id) {
        Connection connection = this.getConnection();
        String query = "DELETE FROM suppliers WHERE id = '" + id + "'";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Supplier> getSuppliers() {
        Connection connection = this.getConnection();
        List<Supplier> suppliers = new ArrayList<>();
        String query = "SELECT * FROM suppliers";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                Supplier supplier = new Supplier();
                supplier.setId(resultSet.getInt("id"));
                supplier.setName(resultSet.getString("name"));
                supplier.setAddress(resultSet.getString("address"));
                supplier.setPhone(resultSet.getString("phone"));
                supplier.setEmail(resultSet.getString("email"));
                suppliers.add(supplier);
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return suppliers;
    }

    public Supplier getSupplier(int id) {
        Connection connection = this.getConnection();
        Supplier supplier = new Supplier();
        String query = "SELECT * FROM suppliers WHERE id = '" + id + "'";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            while (resultSet.next()) {
                supplier.setId(resultSet.getInt("id"));
                supplier.setName(resultSet.getString("name"));
                supplier.setAddress(resultSet.getString("address"));
                supplier.setPhone(resultSet.getString("phone"));
                supplier.setEmail(resultSet.getString("email"));
            }
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return supplier;
    }

}