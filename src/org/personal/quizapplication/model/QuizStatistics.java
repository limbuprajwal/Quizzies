package org.personal.quizapplication.model;

/**
 * Represents the statistics of the quiz application.
 * This class holds information about total players, average score, 
 * the best scores in various categories, and statistics regarding questions.
 */
public class QuizStatistics {

    private int totalPlayers;
    private double avgScore;
    private double bestBeginner;
    private double bestIntermediate;
    private double bestAdvanced;
    private int totalQuestions;
    private int totalCorrectAnswers;

    /**
     * Constructor to create a new instance of QuizStatistics with specified values.
     * 
     * @param totalPlayers the total number of players in the quiz
     * @param avgScore the average score of all players
     * @param bestBeginner the best score achieved by a beginner player
     * @param bestIntermediate the best score achieved by an intermediate player
     * @param bestAdvanced the best score achieved by an advanced player
     * @param totalQuestions the total number of questions attempted in the quiz
     * @param totalCorrectAnswers the total number of correct answers given by all players
     */
    public QuizStatistics(int totalPlayers, double avgScore, double bestBeginner, double bestIntermediate,
                          double bestAdvanced, int totalQuestions, int totalCorrectAnswers) {
        this.totalPlayers = totalPlayers;
        this.avgScore = avgScore;
        this.bestBeginner = bestBeginner;
        this.bestIntermediate = bestIntermediate;
        this.bestAdvanced = bestAdvanced;
        this.totalQuestions = totalQuestions;
        this.totalCorrectAnswers = totalCorrectAnswers;
    }

    // Getters and Setters

    /**
     * Gets the total number of players.
     * 
     * @return the total number of players
     */
    public int getTotalPlayers() {
        return totalPlayers;
    }

    /**
     * Sets the total number of players.
     * 
     * @param totalPlayers the total number of players
     */
    public void setTotalPlayers(int totalPlayers) {
        this.totalPlayers = totalPlayers;
    }

    /**
     * Gets the average score of all players.
     * 
     * @return the average score
     */
    public double getAvgScore() {
        return avgScore;
    }

    /**
     * Sets the average score of all players.
     * 
     * @param avgScore the average score
     */
    public void setAvgScore(double avgScore) {
        this.avgScore = avgScore;
    }

    /**
     * Gets the best score achieved by a beginner.
     * 
     * @return the best beginner score
     */
    public double getBestBeginner() {
        return bestBeginner;
    }

    /**
     * Sets the best score achieved by a beginner.
     * 
     * @param bestBeginner the best beginner score
     */
    public void setBestBeginner(double bestBeginner) {
        this.bestBeginner = bestBeginner;
    }

    /**
     * Gets the best score achieved by an intermediate player.
     * 
     * @return the best intermediate score
     */
    public double getBestIntermediate() {
        return bestIntermediate;
    }

    /**
     * Sets the best score achieved by an intermediate player.
     * 
     * @param bestIntermediate the best intermediate score
     */
    public void setBestIntermediate(double bestIntermediate) {
        this.bestIntermediate = bestIntermediate;
    }

    /**
     * Gets the best score achieved by an advanced player.
     * 
     * @return the best advanced score
     */
    public double getBestAdvanced() {
        return bestAdvanced;
    }

    /**
     * Sets the best score achieved by an advanced player.
     * 
     * @param bestAdvanced the best advanced score
     */
    public void setBestAdvanced(double bestAdvanced) {
        this.bestAdvanced = bestAdvanced;
    }

    /**
     * Gets the total number of questions attempted in the quiz.
     * 
     * @return the total number of questions
     */
    public int getTotalQuestions() {
        return totalQuestions;
    }

    /**
     * Sets the total number of questions attempted in the quiz.
     * 
     * @param totalQuestions the total number of questions
     */
    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }

    /**
     * Gets the total number of correct answers given by all players.
     * 
     * @return the total correct answers
     */
    public int getTotalCorrectAnswers() {
        return totalCorrectAnswers;
    }

    /**
     * Sets the total number of correct answers given by all players.
     * 
     * @param totalCorrectAnswers the total correct answers
     */
    public void setTotalCorrectAnswers(int totalCorrectAnswers) {
        this.totalCorrectAnswers = totalCorrectAnswers;
    }
}
