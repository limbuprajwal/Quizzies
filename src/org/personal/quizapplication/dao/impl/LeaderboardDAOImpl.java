package org.personal.quizapplication.dao.impl;

import org.personal.quizapplication.dao.LeaderboardDAO;
import org.personal.quizapplication.model.LeaderboardEntry;
import org.personal.quizapplication.connection.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementation of the LeaderboardDAO interface that handles interactions with
 * the leaderboard data in the QuizApplication database.
 */
public class LeaderboardDAOImpl implements LeaderboardDAO {

    /**
     * Retrieves the top 10 users from the leaderboard based on the specified difficulty level.
     * 
     * @param difficulty the difficulty level (Beginner, Intermediate, Advanced)
     * @return a list of LeaderboardEntry objects representing the top 10 users and their scores
     * @throws SQLException if a database access error occurs
     */
    @Override
    public List<LeaderboardEntry> getLeaderboardByDifficulty(String difficulty) throws SQLException {
        String scoreColumn = getScoreColumn(difficulty);
        List<LeaderboardEntry> leaderboard = new ArrayList<>();

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(
            		 "SELECT u.username, qs." + scoreColumn + " FROM quiz_scores qs " +
            				 "JOIN users u ON qs.user_id = u.id " +
            				 "ORDER BY qs." + scoreColumn + " DESC LIMIT 10")) {

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                leaderboard.add(new LeaderboardEntry(
                        rs.getString("username"),
                        rs.getDouble(scoreColumn)
                ));
            }
        }
        return leaderboard;
    }

    /**
     * Returns the column name for the score based on the specified difficulty level.
     * 
     * @param difficulty the difficulty level (Beginner, Intermediate, Advanced)
     * @return the column name corresponding to the score for the given difficulty
     * @throws IllegalArgumentException if an invalid difficulty is provided
     */
    private String getScoreColumn(String difficulty) {
        switch (difficulty) {
            case "Beginner": return "beginner_score";
            case "Intermediate": return "intermediate_score";
            case "Advanced": return "advanced_score";
            default: throw new IllegalArgumentException("Invalid difficulty: " + difficulty);
        }
    }
    
    /**
     * Retrieves the top 10 users from the overall leaderboard based on their average score.
     * 
     * @return a list of LeaderboardEntry objects representing the top 10 users and their average scores
     * @throws SQLException if a database access error occurs
     */
    @Override
    public List<LeaderboardEntry> getLeaderboardByDifficulty() throws SQLException {
        List<LeaderboardEntry> leaderboard = new ArrayList<>();
        String query = "SELECT u.username, q.average_score " +
                       "FROM quiz_scores q " +
                       "JOIN users u ON q.user_id = u.id " +
                       "ORDER BY q.average_score DESC LIMIT 10";

        try (Connection conn = ConnectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(query);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                LeaderboardEntry entry = new LeaderboardEntry(
                        rs.getString("username"),
                        rs.getDouble("average_score")
                );
                leaderboard.add(entry);
            }
        }
        return leaderboard;
    }
}
