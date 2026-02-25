package dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.*;
import javax.swing.*;

public class SQLConnect {
    private static final String url = "jdbc:mysql://localhost:3306/?allowMultiQueries=true";
    private static final String URL = "jdbc:mysql://localhost:3306/sc_assignment";
    private static final String USER = "root";
    private static String PASSWORD;

    private static Connection conn = null;

    public void enterPassword() {
        try {
            PASSWORD = JOptionPane.showInputDialog("Enter password:");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Wrong Password!", "Password Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
        }   
    }

    public void createDatabase() {
        try {
            launchSQL();
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void launchSQL() throws databaseCreated {
        String scriptFilePath = "SC_Database.sql"; 
        Statement statement = null;

        try {
            conn = DriverManager.getConnection(url, USER, PASSWORD); // Create a connection to MySQL (no database selected yet)

            statement = conn.createStatement();                     // Create a Statement object to execute the script

            // check if database already exists
            String checkDBQuery = "SHOW DATABASES LIKE 'SC_ASSIGNMENT';";
            ResultSet rs = statement.executeQuery(checkDBQuery);

            if (rs.next()) {
                throw new databaseCreated("Database already exists. Proceeding to connect...");
            } 

            else {
                String script = readScriptFile(scriptFilePath);         // Read the SQL script file
                statement.executeUpdate(script);                         // Execute the script to create the database
                JOptionPane.showMessageDialog(null, "Database created successfully!");
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    private String readScriptFile(String filePath) throws IOException {
        StringBuilder script = new StringBuilder();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            script.append(line).append("\n");
        }
        reader.close();
        return script.toString();
    }

    public void Connect() {
        try {
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            JOptionPane.showMessageDialog(null, "Connected!");
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error Connection: " + e.getMessage(), "Connection Error", JOptionPane.ERROR_MESSAGE);
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

class databaseCreated extends Exception {
    public databaseCreated(String message) {
        super(message);
    }
}