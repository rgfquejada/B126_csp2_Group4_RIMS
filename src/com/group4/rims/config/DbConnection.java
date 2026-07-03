package com.group4.rims.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    private static final String URL = "jdbc:mysql://localhost:3306/restaurant_inventory_management";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    public static Connection connect() {
        try {
            return getConnection();
        } catch (SQLException e) {
            System.err.println("[DB ERROR] Failed to connect to MySQL: " + e.getMessage());
            return null;
        }
    }


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