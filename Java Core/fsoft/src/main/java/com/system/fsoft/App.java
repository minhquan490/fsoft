package com.system.fsoft;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDate;

import com.system.fsoft.config.DatabaseConfig;

public class App {

    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;

    public static void main(String[] args) {
        try {
            connection = DatabaseConfig.getConnection();
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            resultSet = statement.executeQuery("SELECT * FROM Candidate");
            resultSet.moveToInsertRow();
            resultSet.updateString("Candidate_ID", "Id test");
            resultSet.updateString("Full_Name", "Name test");
            resultSet.updateDate("Birth_Day", Date.valueOf(LocalDate.now()));
            resultSet.updateString("Phone", "0868550492");
            resultSet.updateString("Email", "test@gmail.com");
            resultSet.insertRow();
            System.out.println("sucess");
        } catch (Exception e) {
            System.out.println("err");
        }
    }
}
