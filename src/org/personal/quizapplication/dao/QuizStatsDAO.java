package org.personal.quizapplication.dao;

import java.sql.*;

import org.personal.quizapplication.model.QuizStatistics;

import static org.personal.quizapplication.connection.ConnectionFactory.getConnection;

/**
 * Data Access Object (DAO) class for retrieving quiz statistics from the database.
 * This class provides a method for fetching overall quiz statistics such as the total number of players,
 * average score, highest scores for each difficulty level, and the total number of questions and correct answers.
 */
public class QuizStatsDAO {

    /**
     * Retrieves the overall quiz statistics from the database.
     * This includes the total number of players, average score, highest scores for each difficulty,
     * total questions attempted, and total correct answers.
     *
     * @return a QuizStatistics object containing the calculated statistics, or null if there is an error
     */
    public static QuizStatistics getStatistics() {
        String query = "SELECT COUNT(DISTINCT u.id) AS total_players, AVG(q.average_score) AS avg_score, " +
                "MAX(q.beginner_score) AS best_beginner, MAX(q.intermediate_score) AS best_intermediate, " +
                "MAX(q.advanced_score) AS best_advanced, " +
                "SUM(h.questions_attempted) AS total_questions, SUM(h.correct_answers) AS total_correct_answers " +
                "FROM quiz_scores q " +
                "LEFT JOIN quiz_history h ON q.user_id = h.user_id " +
                "LEFT JOIN users u ON q.user_id = u.id";

        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            if (rs.next()) {
                return new QuizStatistics(
                        rs.getInt("total_players"),
                        rs.getDouble("avg_score"),
                        rs.getDouble("best_beginner"),
                        rs.getDouble("best_intermediate"),
                        rs.getDouble("best_advanced"),
                        rs.getInt("total_questions"),
                        rs.getInt("total_correct_answers")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
