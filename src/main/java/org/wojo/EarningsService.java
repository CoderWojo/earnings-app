package org.wojo;


import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class EarningsService {

    private final String database_name = "earnings";
    private Connection connection;

    public EarningsService(Connection connection) {
        this.connection = connection;
    }

    public void printAll(){
        // Przejdź po ResultSet i wypisz wszystkie wiersze
        String query = "SELECT * FROM earnings";

        try(Statement statement = connection.createStatement()) {
            statement.execute(query);
        } catch(SQLException e) {
            System.err.println("Nie udało się pobrać danych.");
            e.printStackTrace();
        }
    }
}
