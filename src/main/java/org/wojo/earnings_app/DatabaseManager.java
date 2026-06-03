package org.wojo.earnings_app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

//    Manages the connection.
public class DatabaseManager {

    public static Connection getConnection() throws SQLException {
        String url = System.getenv("earnings_db_url");
        String username = System.getenv("earnings_db_username");
        String password = System.getenv("earnings_db_password");

        return DriverManager.getConnection(url, username, password);
    }
}
