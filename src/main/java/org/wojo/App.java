package org.wojo;

import org.wojo.entity.Earning;
import org.wojo.repository.EarningsRepository;
import org.wojo.service.EarningsService;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;


// To jest warstwa prezentacji - User Interface, tutaj wypisujemy dane
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

        String menu = """
                === SYSTEM ZARZĄDZANIA ZAROBKAMI ===
                1 - Wypisz wszystkie dane.
                2 - Dodaj nową kwotę.
                3 - Zaktualizuj aktualny zarobek.
                4 - Usuń istniejącą pozycję.""";
        System.out.println(menu);

        Scanner in = new Scanner(System.in);
        boolean isRunning = true;

        EarningsService service = new EarningsService(new EarningsRepository());
        while(isRunning) {
            int choice = in.nextInt();
            in.nextLine(); // because nextInt() didn't consume \n sign
            switch(choice) {

                case 1:
                    System.out.println("Oto aktualny stan tabeli:");
                    List<Earning> earnings = service.getAllEarnings();
                    // wypisz je
                    earnings.forEach(System.out::println);
                    System.out.println('\n');
                    break;
                case 2:
                    System.out.println("Wybrałeś opcję dodania nowego zarobku. Podaj odpowiednie dane.");
                    System.out.println("Podaj datę w formacie yyyy-mm-dd:");
                    String input = in.nextLine();
                    Date date = Date.valueOf(input);

                    System.out.println("Podaj imię ucznia: ");
                    String student_name = in.nextLine();

                    System.out.println("Podaj kwotę w zł:");
                    int amount = in.nextInt();
                    in.nextLine(); // as above ^^

                    System.out.println("Podaj temat lekcji:");
                    String subject = in.nextLine();

                    Earning earning = new Earning(date, student_name, amount, subject);
                    int added_rows = service.addEarning(earning);

                    System.out.println("Successfully added " + added_rows + " rows. \n");
            }

            System.out.println(menu);
        }

    }
}
