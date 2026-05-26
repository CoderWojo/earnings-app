package org.wojo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;


public class App 
{

    public static void main( String[] args ) {
        System.out.println("Próba nawiązania połączenia z bazą danych...");
        try (Connection connection = DatabaseManager.getConnection()) {
            System.out.println("Inicjalizacja bazy danych...");
            DatabaseInitializer.initialize(connection);

        } catch (SQLException e) {
            System.err.println("Nie udało się nawiązać połączenia z bazą danych.");
            e.printStackTrace();
        }

        System.out.println("=== SYSTEM ZARZĄDZANIA ZAROBKAMI ===");
        System.out.println("""
                1 - Wypisz wszystkie dane.
                2 - Dodaj nową kwotę.
                3 - Zaktualizuj aktualny zarobek.
                4 - Usuń istniejącą pozycję.
                """);
        Scanner in = new Scanner(System.in);
        boolean isRunning = true;
        EarningsService service = new EarningsService();
        while(isRunning) {
            int choice = in.nextInt();

            switch(choice) {
                case 1:
                    // wypisz wszystko

            }
        }

    }
}
