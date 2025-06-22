package org.personal.quizapplication.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * DAO class for interacting with the quiz scores data in the database.
 * This class provides methods for retrieving and updating quiz scores for users,
 * including the highest score, average score, and games played for specific difficulties.
 */
public class QuizScoresDAO {

    private Connection connection;

    /**
     * Constructor that initializes the database connection.
     *
     * @param connection the database connection to use for queries
     */
    public QuizScoresDAO(Connection connection) {
        this.connection = connection;
    }

    /**
     * Retrieves the highest score for a specific user and difficulty.
     *
     * @param userId the ID of the user
     * @param difficulty the difficulty level (e.g., "Beginner", "Intermediate", "Advanced")
     * @return the highest score for the specified difficulty
     */
    public double getHighestScore(int userId, String difficulty) {
        String query = "SELECT COALESCE(MAX(score), 0) AS highest_score FROM quiz_history WHERE user_id = ? AND difficulty = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);
            ps.setString(2, difficulty);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("highest_score");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving highest score", e);
        }
        return 0.0;
    }

    /**
     * Retrieves the average score for a specific user and difficulty.
     *
     * @param userId the ID of the user
     * @param difficulty the difficulty level (e.g., "Beginner", "Intermediate", "Advanced")
     * @return the average score for the specified difficulty
     */
    public double getAverageScore(int userId, String difficulty) {
        String query = "SELECT COALESCE(AVG(score), 0) AS avg_score FROM quiz_history WHERE user_id = ? AND difficulty = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);
            ps.setString(2, difficulty);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("avg_score");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving average score", e);
        }
        return 0.0;
    }

    /**
     * Retrieves the number of games played by a user for a specific difficulty.
     *
     * @param userId the ID of the user
     * @param difficulty the difficulty level (e.g., "Beginner", "Intermediate", "Advanced")
     * @return the number of games played by the user for the specified difficulty
     */
    public int getGamesPlayed(int userId, String difficulty) {
        String query = "SELECT COALESCE(COUNT(*), 0) AS games_played FROM quiz_history WHERE user_id = ? AND difficulty = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);
            ps.setString(2, difficulty);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("games_played");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving games played", e);
        }
        return 0;
    }

    /**
     * Retrieves the overall average score for a user across all difficulties.
     *
     * @param userId the ID of the user
     * @return the overall average score for the user
     */
    public double getOverallAverage(int userId) {
        String query = "SELECT COALESCE(AVG(score), 0) AS overall_avg FROM quiz_history WHERE user_id = ?";
        try (PreparedStatement ps = connection.prepareStatement(query)) {
            ps.setInt(1, userId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getDouble("overall_avg");
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error retrieving overall average score", e);
        }
        return 0.0;
    }

    /**
     * Inserts or updates the quiz scores for a user.
     * If a record already exists for the user, it updates the record; otherwise, it inserts a new record.
     *
     * @param userId the ID of the user
     * @param beginnerAvg the average score for the beginner level
     * @param intermediateAvg the average score for the intermediate level
     * @param advancedAvg the average score for the advanced level
     * @param overallAvg the overall average score for the user
     * @param beginnerHigh the highest score for the beginner level
     * @param intermediateHigh the highest score for the intermediate level
     * @param advancedHigh the highest score for the advanced level
     * @param gamesPlayed the number of games played by the user
     * @return true if the operation was successful, false otherwise
     */
    public boolean upsertQuizScores(int userId, double beginnerAvg, double intermediateAvg, double advancedAvg, 
                                     double overallAvg, double beginnerHigh, double intermediateHigh, 
                                     double advancedHigh, int gamesPlayed) {
        try {
            // Check if the user already has a record
            String checkQuery = "SELECT COUNT(*) FROM quiz_scores WHERE user_id = ?";
            try (PreparedStatement checkStmt = connection.prepareStatement(checkQuery)) {
                checkStmt.setInt(1, userId);
                ResultSet rs = checkStmt.executeQuery();
                rs.next();
                boolean recordExists = rs.getInt(1) > 0;

                if (recordExists) {
                    // Update existing record
                    String updateQuery = "UPDATE quiz_scores SET beginner_avg = ?, intermediate_avg = ?, advanced_avg = ?, "
                            + "average_score = ?, beginner_score = ?, intermediate_score = ?, advanced_score = ?, "
                            + "games_played = ?, last_played = CURRENT_TIMESTAMP WHERE user_id = ?";
                    try (PreparedStatement updateStmt = connection.prepareStatement(updateQuery)) {
                        updateStmt.setDouble(1, beginnerAvg);
                        updateStmt.setDouble(2, intermediateAvg);
                        updateStmt.setDouble(3, advancedAvg);
                        updateStmt.setDouble(4, overallAvg);
                        updateStmt.setDouble(5, beginnerHigh);
                        updateStmt.setDouble(6, intermediateHigh);
                        updateStmt.setDouble(7, advancedHigh);
                        updateStmt.setInt(8, gamesPlayed);
                        updateStmt.setInt(9, userId);
                        updateStmt.executeUpdate();
                    }
                } else {
                    // Insert new record
                    String insertQuery = "INSERT INTO quiz_scores (user_id, beginner_avg, intermediate_avg, advanced_avg, "
                            + "average_score, beginner_score, intermediate_score, advanced_score, games_played, last_played) "
                            + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, CURRENT_TIMESTAMP)";
                    try (PreparedStatement insertStmt = connection.prepareStatement(insertQuery)) {
                        insertStmt.setInt(1, userId);
                        insertStmt.setDouble(2, beginnerAvg);
                        insertStmt.setDouble(3, intermediateAvg);
                        insertStmt.setDouble(4, advancedAvg);
                        insertStmt.setDouble(5, overallAvg);
                        insertStmt.setDouble(6, beginnerHigh);
                        insertStmt.setDouble(7, intermediateHigh);
                        insertStmt.setDouble(8, advancedHigh);
                        insertStmt.setInt(9, gamesPlayed);
                        insertStmt.executeUpdate();
                    }
                }
            }
            return true;
        } catch (SQLException e) {
            throw new RuntimeException("Error upserting quiz scores", e);
        }
    }
}
