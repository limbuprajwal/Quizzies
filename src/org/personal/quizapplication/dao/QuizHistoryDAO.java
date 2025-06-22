package org.personal.quizapplication.dao;

import org.personal.quizapplication.model.QuizHistory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.personal.quizapplication.connection.ConnectionFactory.getConnection;

/**
 * DAO class for interacting with the quiz history data in the database.
 * This class provides methods for inserting and managing quiz history records.
 */
public class QuizHistoryDAO {

    private Connection connection;

    /**
     * Constructor that initializes the database connection.
     * 
     * @throws SQLException if a database access error occurs during connection setup
     */
    public QuizHistoryDAO() {
        try {
            this.connection = getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Inserts a quiz history record into the database.
     * 
     * @param quizHistory the quiz history record to insert
     * @return true if the record was inserted successfully, false otherwise
     */
    public boolean insertQuizHistory(QuizHistory quizHistory) {
        String query = "INSERT INTO quiz_history (user_id, difficulty, score, questions_attempted, correct_answers) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, quizHistory.getUserId());
            ps.setString(2, quizHistory.getDifficulty());
            ps.setDouble(3, quizHistory.getScore());
            ps.setInt(4, quizHistory.getQuestionsAttempted());
            ps.setInt(5, quizHistory.getCorrectAnswers());
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
