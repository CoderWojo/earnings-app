package org.wojo;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

// Helper, ulility class. Without fields, objects. so we write a static method
public class DatabaseInitializer {

    public static void initialize(Connection connection) {
        String query = """
            CREATE TABLE IF NOT EXISTS earnings (
            id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
            lesson_date DATE,
            student_name VARCHAR(255),
            amount INT,
            subject VARCHAR(255)
            );
    """;

        try(Statement statement = connection.createStatement()) {
            statement.execute(query);

            System.out.println("Table is created successfully.");
        } catch(SQLException e) {
            System.err.println("Nie udało się utworzyć tabeli.");
            e.printStackTrace();
        }

    }
}
