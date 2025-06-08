package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    private static Connection connection;

    public static Connection getConnection() throws SQLException {
        if (connection == null || connection.isClosed()) {
            try {
                Properties props = new Properties();

                try ( // Load db.properties from project root directory
                        FileInputStream fis = new FileInputStream("db.properties")) {
                    props.load(fis);
                }

                String url = props.getProperty("url");
                String username = props.getProperty("username");
                String password = props.getProperty("password");

                connection = DriverManager.getConnection(url, username, password);

            } catch (IOException e) {
                System.out.println("Error: Could not load db.properties file. " + e.getMessage());
                throw new SQLException("Database configuration file not found.", e);
            } catch (SQLException e) {
                System.out.println("Error: Could not connect to database. " + e.getMessage());
                throw e;
            }
        }
        return connection;
    }
}
