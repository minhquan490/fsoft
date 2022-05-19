package com.system.fsoft.config;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private static final String SERVER_ADDRESS = "localhost";
    private static final String DB_NAME = "Candidate_Management";
    private static final String SERVER_PORT = "1434";
    private static final String USERNAME = "sa";
    private static final String PASSWORD = "Quan3008";

    public static Connection getConnection() throws SQLException, InstantiationException, IllegalAccessException {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").getDeclaredConstructor().newInstance();
            String url = "jdbc:sqlserver://" + SERVER_ADDRESS + ":" + SERVER_PORT + ";" + "database=" + DB_NAME + ";"
                    + "trustServerCertificate=true;";
            return DriverManager.getConnection(url, USERNAME, PASSWORD);
        } catch (ClassNotFoundException ea) {
            System.out.println("ClassNotFoundException: " + ea.getMessage());
        } catch (NoSuchMethodException eb) {
            System.out.println("NoSuchMethodException: " + eb.getMessage());
        } catch (InvocationTargetException ec) {
            System.out.println("InvocationTargetException: " + ec.getMessage());
        } catch (SQLException ed) {
            System.out.println("SQLException: " + ed.getMessage());
            System.out.println("SQLState: " + ed.getSQLState());
            System.out.println("VendorError: " + ed.getErrorCode());
        } catch (InstantiationException ee) {
            System.out.println("InstantiationException: " + ee.getMessage());
        } catch (IllegalAccessException ef) {
            System.out.println("IllegalAccessException: " + ef.getMessage());
        }
        return null;
    }
}