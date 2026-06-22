package com.flipkart.utils;

import java.sql.*;

public class DBUtils {

    private static final String DB_URL = "jdbc:h2:mem:flipkartdb;DB_CLOSE_DELAY=-1";
    private static final String DB_USER = "sa";
    private static final String DB_PASSWORD = "";

    private static Connection connection;

    public static Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = java.sql.DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            }
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException("Failed to connect to database", e);
        }
    }

    public static void setupSampleData() {
        try (Statement stmt = getConnection().createStatement()) {
            stmt.execute("DROP TABLE IF EXISTS orders");
            stmt.execute("CREATE TABLE orders (" +
                    "order_id INT PRIMARY KEY, " +
                    "product_name VARCHAR(100), " +
                    "price DECIMAL(10,2), " +
                    "status VARCHAR(20))");
            stmt.execute("INSERT INTO orders VALUES (1, 'watches', 999.00, 'CONFIRMED')");
            stmt.execute("INSERT INTO orders VALUES (2, 'Mobile', 15999.00, 'PENDING')");
            stmt.execute("INSERT INTO orders VALUES (3, 'Headphones', 1999.00, 'CONFIRMED')");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to set up sample data", e);
        }
    }

    public static ResultSet executeQuery(String query) {
        try {
            Statement stmt = getConnection().createStatement();
            return stmt.executeQuery(query);
        } catch (SQLException e) {
            throw new RuntimeException("Query execution failed: " + query, e);
        }
    }

    public static void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            // Safe to ignore on cleanup
        }
    }
}