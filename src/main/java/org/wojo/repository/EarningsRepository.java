package org.wojo.repository;

import org.wojo.CRUDOperations;
import org.wojo.DatabaseManager;
import org.wojo.entity.Earning;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EarningsRepository implements CRUDOperations {

//  Nie tworzymy pola Connection bo połączenie byłoby otwarte tak długo jak działa program
//  Więc każda metoda w repo będzie żądać połączenie od DatabaseManager i od razu je zamykać
//  jak skończy swoją pracę.

    @Override
    public List<Earning> findAll() {
        try(Connection c = DatabaseManager.getConnection()) {
            Statement stmt = c.createStatement();
            String query = "SELECT * FROM earnings";

            ResultSet resultSet = stmt.executeQuery(query);

            return convertRowsToEarnings(resultSet);
        } catch(SQLException e) {
            System.err.println("Nie udało się nawiązać połączenia z bazą danych.");
        }
        return null;
    }

    @Override
    public int addEarning(Earning earning) {
        try(Connection c = DatabaseManager.getConnection()) {
            String query = "INSERT INTO earnings(lesson_date, student_name, amount, subject)" +
                    "VALUES(?,?,?,?)";
            PreparedStatement stmt = c.prepareStatement(query);
            stmt.setDate(1, earning.getLesson_date());
            stmt.setString(2, earning.getStudent_name());
            stmt.setInt(3, earning.getAmount());
            stmt.setString(4, earning.getSubject());


            return stmt.executeUpdate();
        } catch(SQLException e) {
            System.err.println("Nie udało się nawiązać połączenia z bazą danych.");
            e.printStackTrace();
        }

        return -1;
    }


    private List<Earning> convertRowsToEarnings(ResultSet resultSet) throws SQLException {
        List<Earning> result = new ArrayList<>();

        int id;
        Date lesson_date;
        String student_name;
        int amount;
        String subject;
        while(resultSet.next()) {
            id = resultSet.getInt(1);
            lesson_date = resultSet.getDate(2);
            student_name = resultSet.getString(3);
            amount = resultSet.getInt(4);
            subject = resultSet.getString(5);

            result.add(new Earning(id, lesson_date, student_name, amount, subject));
        }

        return result;
    }
}
