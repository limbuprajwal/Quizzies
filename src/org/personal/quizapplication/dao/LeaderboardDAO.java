package org.personal.quizapplication.dao;

import java.sql.SQLException;
import java.util.List;

import org.personal.quizapplication.model.LeaderboardEntry;

/**
 * Interface for accessing leaderboard data from the database.
 * This includes methods for retrieving leaderboard entries
 * based on different difficulty levels.
 */
public interface LeaderboardDAO {

    /**
     * Retrieves the top 10 leaderboard entries ordered by average score
     * from the database, without filtering by difficulty.
     * 
     * @return a list of top 10 leaderboard entries
     * @throws SQLException if a database access error occurs
     */
    List<LeaderboardEntry> getLeaderboardByDifficulty() throws SQLException;

    /**
     * Retrieves the top 10 leaderboard entries for a specific difficulty level
     * ordered by the score for that difficulty.
     * 
     * @param difficulty the difficulty level (e.g., "Beginner", "Intermediate", "Advanced")
     * @return a list of top 10 leaderboard entries for the specified difficulty
     * @throws SQLException if a database access error occurs
     */
    List<LeaderboardEntry> getLeaderboardByDifficulty(String difficulty) throws SQLException;
}
