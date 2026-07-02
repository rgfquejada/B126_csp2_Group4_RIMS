package com.group4.rims.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/restaurant_inventory_management";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    private DbConnection() {
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }

    public static void main(String[] args) {
        try (Connection conn = getConnection()) {
            System.out.println("Connected Successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}