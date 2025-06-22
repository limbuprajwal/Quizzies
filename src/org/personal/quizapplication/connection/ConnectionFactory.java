package org.personal.quizapplication.connection;

import java.sql.*;

/**
 * A utility class for managing database connections and creating the necessary
 * database and tables for the QuizApplication.
 * This class uses MySQL JDBC for connection handling.
 */
public class ConnectionFactory {

    // MySQL database connection details
    private static final String BASE_URL = "jdbc:mysql://localhost:3306/";
    private static final String DB_NAME = "QuizApplication";
    private static final String URL = BASE_URL + DB_NAME;
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    // Static block to load MySQL JDBC driver
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        }
    }

    /**
     * Establishes and returns a connection to the QuizApplication database.
     * If the database does not exist, it is created and tables are initialized.
     * 
     * @return Connection object to the database
     * @throws SQLException if a database access error occurs
     */
    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (SQLException e) {
            if (e.getErrorCode() == 1049) {
                createDatabase();
                return DriverManager.getConnection(URL, USERNAME, PASSWORD);
            }
            throw e;
        }
    }

    /**
     * Creates the QuizApplication database and its tables if they do not already exist.
     * 
     * @throws SQLException if a database access error occurs
     */
    private static void createDatabase() throws SQLException {
        try (Connection conn = DriverManager.getConnection(BASE_URL, USERNAME, PASSWORD);
             Statement stmt = conn.createStatement()) {

            // Create the database if not exists
            System.out.println("Creating database if not exists: QuizApplication");
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS QuizApplication");

            // Switch to the created database
            System.out.println("Using database: QuizApplication");
            stmt.executeUpdate("USE " + DB_NAME);

            // Execute all CREATE TABLE statements to create necessary tables
            String[] createTableStatements = getCreateTableStatements();
            for (String statement : createTableStatements) {
                try {
                    System.out.println("Executing: " + statement);
                    stmt.executeUpdate(statement);
                } catch (SQLException e) {
                    System.err.println("Error executing statement: " + statement);
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Main method to create the database and tables for testing purposes.
     * 
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        try {
            createDatabase(); // Ensure database and tables are created
            System.out.println("Database and tables created successfully.");
        } catch (SQLException e) {
            System.err.println("Error creating database or tables:");
            e.printStackTrace();
        }
    }

    /**
     * Returns the SQL statements required to create the necessary tables
     * for the QuizApplication database.
     * 
     * @return an array of SQL CREATE TABLE statements
     */
    private static String[] getCreateTableStatements() {
        return new String[] {
            // SQL statement to create users table
            "CREATE TABLE IF NOT EXISTS users (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "username VARCHAR(50) UNIQUE NOT NULL," +
                "password VARCHAR(64) NOT NULL," +
                "role VARCHAR(10) NOT NULL" +
            ")",

            // SQL statement to create questions table
            "CREATE TABLE IF NOT EXISTS questions (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "question_text VARCHAR(500) NOT NULL," +
                "option1 VARCHAR(500) NOT NULL," +
                "option2 VARCHAR(500) NOT NULL," +
                "option3 VARCHAR(500) NOT NULL," +
                "option4 VARCHAR(500) NOT NULL," +
                "correct_option VARCHAR(500) NOT NULL," +
                "difficulty ENUM('Beginner', 'Intermediate', 'Advanced') NOT NULL" +
            ")",

            // SQL statement to create quiz_scores table
            "CREATE TABLE IF NOT EXISTS quiz_scores (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "user_id INT," +
                "beginner_score DECIMAL(5,2) DEFAULT 0.0," +
                "intermediate_score DECIMAL(5,2) DEFAULT 0.0," +
                "advanced_score DECIMAL(5,2) DEFAULT 0.0," +
                "beginner_avg DECIMAL(5,2) DEFAULT 0.0," +
                "intermediate_avg DECIMAL(5,2) DEFAULT 0.0," +
                "advanced_avg DECIMAL(5,2) DEFAULT 0.0," +
                "average_score DECIMAL(5,2) DEFAULT 0.0," +
                "games_played INT DEFAULT 0," +
                "last_played TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                "FOREIGN KEY (user_id) REFERENCES users(id)" +
            ");",

            // SQL statement to create quiz_history table
            "CREATE TABLE IF NOT EXISTS quiz_history (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "user_id INT," +
                "difficulty VARCHAR(20) NOT NULL," +
                "score DECIMAL(5,2) NOT NULL," +
                "questions_attempted INT NOT NULL," +
                "correct_answers INT NOT NULL," +
                "date_taken TIMESTAMP DEFAULT CURRENT_TIMESTAMP," +
                "FOREIGN KEY (user_id) REFERENCES users(id)" +
            ")"
        };
    }

    /**
     * Closes the given resources quietly (without throwing any exceptions).
     * 
     * @param resources the resources to close (e.g., Connection, Statement, ResultSet)
     */
    public static void closeQuietly(AutoCloseable... resources) {
        for (AutoCloseable resource : resources) {
            if (resource != null) {
                try {
                    resource.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
