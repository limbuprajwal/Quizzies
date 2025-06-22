package org.personal.quizapplication.test;

import static org.junit.jupiter.api.Assertions.*;
import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.personal.quizapplication.dao.QuizScoresDAO;

import static org.personal.quizapplication.connection.ConnectionFactory.getConnection;

/**
 * Unit test class for testing the functionality of {@link QuizScoresDAO} class.
 * <p>
 * This class provides various tests for querying the average and highest scores from the quiz database. 
 * It uses an in-memory H2 database to execute the queries, ensuring tests do not affect production data. 
 * The tests verify that the DAO methods correctly return the average and highest quiz scores 
 * based on the provided user ID and difficulty level.
 */
public class QuizScoresDAOTest {

    private Connection connection;   // Database connection object used in tests
    private QuizScoresDAO quizScoresDAO; // DAO instance for interacting with quiz scores

    /**
     * Sets up the test environment before each test method is executed.
     * <p>
     * Initializes a connection to an in-memory H2 database and creates an instance of {@link QuizScoresDAO} 
     * to be used in the tests.
     * </p>
     * 
     * @throws SQLException if there is an error while establishing the connection to the database
     */
    @BeforeEach
    public void setUp() throws SQLException {
        // Using an in-memory H2 database for isolated tests
        connection = getConnection();
        quizScoresDAO = new QuizScoresDAO(connection);
    }

    /**
     * Tests the {@link QuizScoresDAO#getAverageScore(int, String)} method for a user who has scores 
     * in the specified difficulty level.
     * <p>
     * The test verifies that the average score returned by the method is correct for the given user and difficulty.
     * </p>
     * 
     * @throws SQLException if there is an error executing the query
     */
    @Test
    public void testGetAverageScore() throws SQLException {
        int userId = 2;                 // User ID with existing records
        String difficulty = "beginner"; // Difficulty level to test
        double expectedAverage = 5.00;  // Expected average score for the user in this difficulty

        // Calling the actual method to retrieve the average score
        double actualAverage = quizScoresDAO.getAverageScore(userId, difficulty);
        System.out.println(actualAverage); // For debugging purposes

        // Verifying the result
        assertEquals(expectedAverage, actualAverage, 0.01); // Allowing small floating-point differences
    }

    /**
     * Tests the {@link QuizScoresDAO#getAverageScore(int, String)} method for a user who does not have any records 
     * in the database for the specified difficulty.
     * <p>
     * The test verifies that the method returns 0.0 if no scores are found for the given user and difficulty.
     * </p>
     * 
     * @throws SQLException if there is an error executing the query
     */
    @Test
    public void testGetAverageScoreNoResult() throws SQLException {
        int userId = 1; // User ID with no records in the database
        String difficulty = "beginner"; // Difficulty level to test
        double expectedAverage = 0.0;   // Expected average score when no records exist

        // Calling the actual method to retrieve the average score
        double actualAverage = quizScoresDAO.getAverageScore(userId, difficulty);

        // Verifying the result
        assertEquals(expectedAverage, actualAverage, 0.01); // Allowing small floating-point differences
    }

    /**
     * Tests the {@link QuizScoresDAO#getHighestScore(int, String)} method for a user who has scores 
     * in the specified difficulty level.
     * <p>
     * The test verifies that the highest score returned by the method is correct for the given user and difficulty.
     * </p>
     * 
     * @throws SQLException if there is an error executing the query
     */
    @Test
    public void testGetHighestScore() throws SQLException {
        int userId = 2;                // User ID with existing records
        String difficulty = "beginner"; // Difficulty level to test
        double expectedHighestScore = 5.0; // Expected highest score for the user in this difficulty

        // Calling the actual method to retrieve the highest score
        double actualHighestScore = quizScoresDAO.getHighestScore(userId, difficulty);
        System.out.println(actualHighestScore); // For debugging purposes

        // Verifying the result
        assertEquals(expectedHighestScore, actualHighestScore, 0.01); // Allowing small floating-point differences
    }

    /**
     * Tests the {@link QuizScoresDAO#getHighestScore(int, String)} method for a user who does not have any records 
     * in the database for the specified difficulty.
     * <p>
     * The test verifies that the method returns 0.0 if no scores are found for the given user and difficulty.
     * </p>
     * 
     * @throws SQLException if there is an error executing the query
     */
    @Test
    public void testGetHighestScoreNoResult() throws SQLException {
        int userId = 1; // User ID with no records in the database
        String difficulty = "beginner"; // Difficulty level to test
        double expectedHighestScore = 0.0; // Expected highest score when no records exist

        // Calling the actual method to retrieve the highest score
        double actualHighestScore = quizScoresDAO.getHighestScore(userId, difficulty);

        // Verifying the result
        assertEquals(expectedHighestScore, actualHighestScore, 0.01); // Allowing small floating-point differences
    }
}