package dao;

import java.sql.*;
import javax.swing.*;

public class SQLConnect {
    private static final String url = "jdbc:mysql://localhost:3306/?allowMultiQueries=true";
    private static final String URL = "jdbc:mysql://localhost:3306/sc_assignment";
    private static final String USER = "root";
    private static String PASSWORD;

    private static Connection conn = null;

    public void Connect() {
        PASSWORD = JOptionPane.showInputDialog("Enter password:");

        try {
            conn = DriverManager.getConnection(url, USER, PASSWORD);

            try {
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
                JOptionPane.showMessageDialog(null, "Connected!");

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Error Connection: " + e.getMessage(), "Connection Error", JOptionPane.ERROR_MESSAGE);
                System.exit(1);
            }
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Wrong Password!", "Password Error", JOptionPane.ERROR_MESSAGE);
            e.getMessage();
            System.exit(1);
        }
    }

    public Connection getConnection() {
        try{
            Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
            return conn;
        }
        catch(Exception e){
            return null;
        }
    }
}
