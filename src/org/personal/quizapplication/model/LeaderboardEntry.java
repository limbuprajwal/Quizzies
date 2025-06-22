package org.personal.quizapplication.model;

/**
 * Represents an entry in the leaderboard.
 * This class contains the username of a player and their average score.
 */
public class LeaderboardEntry {
    
    private String username;
    private double avgScore;

    /**
     * Constructor to create a new instance of LeaderboardEntry with the specified username and average score.
     * 
     * @param username the username of the player
     * @param avgScore the average score of the player
     */
    public LeaderboardEntry(String username, double avgScore) {
        this.username = username;
        this.avgScore = avgScore;
    }

    // Getters and Setters

    /**
     * Gets the username of the player.
     * 
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the player.
     * 
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the average score of the player.
     * 
     * @return the average score
     */
    public double getAvgScore() {
        return avgScore;
    }

    /**
     * Sets the average score of the player.
     * 
     * @param avgScore the average score
     */
    public void setAvgScore(double avgScore) {
        this.avgScore = avgScore;
    }
}
