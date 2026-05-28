package org.wojo.repository;

import org.wojo.DatabaseManager;
import org.wojo.entity.Earning;
import org.wojo.exceptions.ConnectionFailedException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EarningsRepository {

//  Nie tworzymy pola Connection bo połączenie byłoby otwarte tak długo jak działa program
//  Więc każda metoda w repo będzie żądać połączenie od DatabaseManager i od razu je zamykać
//  jak skończy swoją pracę.

    public List<Earning> findAll() {
        try (Connection c = DatabaseManager.getConnection()) {
            Statement stmt = c.createStatement();
            String query = "SELECT * FROM earnings";

            ResultSet resultSet = stmt.executeQuery(query);

            return convertRowsToEarnings(resultSet);
        } catch (SQLException e) {
            throw new ConnectionFailedException("The program can't connect with the database.", e);

        }
    }
    public int addEarning(Earning earning) {
        try(Connection c = DatabaseManager.getConnection()) {
            String query = "INSERT INTO earnings(lesson_date, student_name, amount, subject)" +
                    "VALUES(?,?,?,?)";
            PreparedStatement stmt = c.prepareStatement(query);
            stmt.setDate(1, earning.getLesson_date());
            stmt.setString(2, earning.getStudent_name());
            stmt.setInt(3, earning.getAmount());
            stmt.setString(4, earning.getSubject());


            // executeUpdate do zapytań zmieniających tabelę
            // executeQuery do zapytań które pobierają dane
            return stmt.executeUpdate();
        } catch(SQLException e) {
            throw new ConnectionFailedException("The program can't connect with the database.", e);
        }
    }

    public int updateEarning(Earning earning) {
        try(Connection c = DatabaseManager.getConnection()) {
            String query = "UPDATE earnings SET " +
                    "lesson_date = ?," +
                    "student_name = ?," +
                    "amount = ?," +
                    "subject = ? " +
                    "WHERE id = ?";
            PreparedStatement statement = c.prepareStatement(query);
            statement.setDate(1, earning.getLesson_date());
            statement.setString(2, earning.getStudent_name());
            statement.setInt(3, earning.getAmount());
            statement.setString(4, earning.getSubject());
            statement.setInt(5, earning.getId());

            return statement.executeUpdate();

        } catch(SQLException e) {
            throw new ConnectionFailedException("The program can't connect with the database.", e);
        }
    }

    public boolean existsById(int id) {
        String query = "SELECT COUNT(*) FROM earnings WHERE id = ?";
        try(Connection c = DatabaseManager.getConnection()) {
            PreparedStatement stmt = c.prepareStatement(query);
            stmt.setInt(1, id);

            try(ResultSet result = stmt.executeQuery()) {
                if (result.next()){
                    int rows = result.getInt(1);

                    return rows > 0;
                }
            }
        } catch(SQLException e) {
            throw new ConnectionFailedException("The program can't connect with the database.", e);
        }
        return false;
    }

    public int deleteById(int id) {
        try(Connection c = DatabaseManager.getConnection()) {
            String query = "DELETE FROM earnings WHERE id = ?";

            PreparedStatement statement = c.prepareStatement(query);
            statement.setInt(1, id);

            return statement.executeUpdate();
        } catch(SQLException e) {
            throw new ConnectionFailedException("The program can't connect with the database.", e);
        }
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
