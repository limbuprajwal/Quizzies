package org.personal.quizapplication.model;

/**
 * Represents the history of a user's quiz attempt.
 * This class holds details such as the user's ID, difficulty level, score, 
 * questions attempted, and correct answers.
 */
public class QuizHistory {

    private int userId;
    private String difficulty;
    private double score;
    private int questionsAttempted;
    private int correctAnswers;

    /**
     * Constructor to create a new instance of QuizHistory with specified values.
     * 
     * @param userId the ID of the user who took the quiz
     * @param difficulty the difficulty level of the quiz (e.g., Beginner, Intermediate, Advanced)
     * @param score the score obtained by the user in the quiz
     * @param questionsAttempted the total number of questions attempted by the user
     * @param correctAnswers the number of correct answers given by the user
     */
    public QuizHistory(int userId, String difficulty, double score, int questionsAttempted, int correctAnswers) {
        this.userId = userId;
        this.difficulty = difficulty;
        this.score = score;
        this.questionsAttempted = questionsAttempted;
        this.correctAnswers = correctAnswers;
    }

    // Getters and Setters

    /**
     * Gets the ID of the user who took the quiz.
     * 
     * @return the user ID
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets the ID of the user who took the quiz.
     * 
     * @param userId the user ID
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * Gets the difficulty level of the quiz.
     * 
     * @return the difficulty level (e.g., Beginner, Intermediate, Advanced)
     */
    public String getDifficulty() {
        return difficulty;
    }

    /**
     * Sets the difficulty level of the quiz.
     * 
     * @param difficulty the difficulty level (e.g., Beginner, Intermediate, Advanced)
     */
    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    /**
     * Gets the score achieved by the user in the quiz.
     * 
     * @return the quiz score
     */
    public double getScore() {
        return score;
    }

    /**
     * Sets the score achieved by the user in the quiz.
     * 
     * @param score the quiz score
     */
    public void setScore(double score) {
        this.score = score;
    }

    /**
     * Gets the total number of questions attempted by the user in the quiz.
     * 
     * @return the number of questions attempted
     */
    public int getQuestionsAttempted() {
        return questionsAttempted;
    }

    /**
     * Sets the total number of questions attempted by the user in the quiz.
     * 
     * @param questionsAttempted the number of questions attempted
     */
    public void setQuestionsAttempted(int questionsAttempted) {
        this.questionsAttempted = questionsAttempted;
    }

    /**
     * Gets the total number of correct answers given by the user in the quiz.
     * 
     * @return the number of correct answers
     */
    public int getCorrectAnswers() {
        return correctAnswers;
    }

    /**
     * Sets the total number of correct answers given by the user in the quiz.
     * 
     * @param correctAnswers the number of correct answers
     */
    public void setCorrectAnswers(int correctAnswers) {
        this.correctAnswers = correctAnswers;
    }

    /**
     * Returns a string representation of the QuizHistory object, 
     * including the user ID, difficulty level, score, questions attempted, 
     * and correct answers.
     * 
     * @return a string representation of the quiz history
     */
    @Override
    public String toString() {
        return "QuizHistory{" +
                "userId=" + userId +
                ", difficulty='" + difficulty + '\'' +
                ", score=" + score +
                ", questionsAttempted=" + questionsAttempted +
                ", correctAnswers=" + correctAnswers +
                '}';
    }
}
