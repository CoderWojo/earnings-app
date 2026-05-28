package org.wojo;

import org.wojo.entity.Earning;
import org.wojo.exceptions.ConnectionFailedException;
import org.wojo.exceptions.EarningNotFoundException;
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
    public static void main( String[] args ) throws ConnectionFailedException {
        System.out.println("Attempting to connect to the database...");
        try (Connection connection = DatabaseManager.getConnection()) {
            System.out.println("Database initialization...");
            DatabaseInitializer.initialize(connection);

        } catch (SQLException e) {
            throw new ConnectionFailedException("Nie udało się nawiązać połączenia z bazą.", e);
        }

        String menu = """
            === EARNINGS MANAGEMENT SYSTEM ===
            1 - Enter all data.
            2 - Add a new amount.
            3 - Update the current earnings.
            4 - Delete the existing item.""";
        System.out.println(menu);

        Scanner in = new Scanner(System.in);
        boolean isRunning = true;

        EarningsService service = new EarningsService(new EarningsRepository());
        while(isRunning) {
            int choice = in.nextInt();
            in.nextLine(); // because nextInt() didn't consume \n sign
            switch(choice) {

                case 1:
                    System.out.println("This is the current state of the table:");
                    List<Earning> earnings = service.getAllEarnings();
                    // wypisz je
                    earnings.forEach(System.out::println);
                    System.out.println('\n');
                    break;
                case 2:
                    System.out.println("Provide the appropriate data.");


                    Earning earning = getDataFromUser(in);
                    int added_rows = service.addEarning(earning);

                    System.out.println("Successfully added " + added_rows + " rows. \n");
                    break;

                case 3:
                    System.out.println("Enter the row id you want to modify:");
                    int id = in.nextInt();
                    in.nextLine();

                    // sprawdzamy czy podane id istnieje w bazie.
                    if(!service.exists(id)) {
                        throw new EarningNotFoundException("Incorrect id provided!");
                    }


                    Earning earning1 = getDataFromUser(in);
                    earning1.setId(id);
                    service.updateEarning(earning1);

                    System.out.println("Earnings updated without any problems.");
                    break;

                case 4:
                    System.out.println("Enter the ID of the earnings you want to delete: ");
                    int id2 = in.nextInt();
                    in.nextLine();

                    if(!service.exists(id2)) {
                        throw new EarningNotFoundException("Incorrect id provided!");
                    }

                    service.deleteById(id2);
                    System.out.println("Earning has been successfully removed.");
                    break;
            }

            System.out.println(menu);
        }
    }

    private static Earning getDataFromUser(Scanner in) {
        System.out.println("Enter the date in the format yyyy-mm-dd:");
        String input = in.nextLine();
        Date date = Date.valueOf(input);

        System.out.println("Enter the student's name: ");
        String student_name = in.nextLine();

        System.out.println("Enter the amount:");
        int amount = in.nextInt();
        in.nextLine(); // as above ^^

        System.out.println("Specify the topic of the lesson:");
        String subject = in.nextLine();

        return new Earning(date, student_name, amount, subject);
    }
}
