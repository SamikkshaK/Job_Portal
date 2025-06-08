package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/jobportal";
        String user = "root"; 
        String password = "cats"; 

        return DriverManager.getConnection(url, user, password);
    }
}

