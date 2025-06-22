package org.personal.quizapplication.dao;

import java.sql.SQLException;
import java.util.List;

import org.personal.quizapplication.model.Question;

/**
 * Interface for accessing question data in the database.
 * This interface includes methods for adding, retrieving,
 * updating, and deleting questions.
 */
public interface QuestionDAO {

    /**
     * Adds a new question to the database.
     * 
     * @param question the question to be added
     * @throws SQLException if a database access error occurs
     */
    void addQuestion(Question question) throws SQLException;

    /**
     * Retrieves all questions from the database that match the given search term.
     * This method searches the question text, options, correct option, and ID.
     * 
     * @param searchTerm the term to search for in the questions
     * @return a list of questions that match the search term
     * @throws SQLException if a database access error occurs
     */
    List<Question> getAllQuestions(String searchTerm) throws SQLException;

    /**
     * Deletes a question from the database by its ID.
     * 
     * @param id the ID of the question to be deleted
     * @return true if the question was deleted, false otherwise
     * @throws SQLException if a database access error occurs
     */
    boolean deleteQuestion(int id) throws SQLException;

    /**
     * Updates an existing question in the database.
     * 
     * @param question the question with updated details
     * @return true if the question was updated, false otherwise
     */
    boolean updateQuestion(Question question);

    /**
     * Retrieves a question from the database by its ID.
     * 
     * @param id the ID of the question to retrieve
     * @return the question with the given ID, or null if not found
     */
    Question getQuestionById(int id);

    /**
     * Retrieves questions from the database for a specific difficulty level.
     * 
     * @param difficulty the difficulty level of the questions to retrieve
     * @return a list of questions that match the given difficulty
     */
    List<Question> getQuestionsByDifficulty(String difficulty);
}
