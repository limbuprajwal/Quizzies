package org.personal.quizapplication.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.personal.quizapplication.dao.QuestionDAO;
import org.personal.quizapplication.model.Question;

import static org.personal.quizapplication.connection.ConnectionFactory.getConnection;

/**
 * Implementation of the QuestionDAO interface that handles CRUD operations
 * for managing quiz questions in the database.
 */
public class QuestionDAOImpl implements QuestionDAO {

    private static final String INSERT_QUERY = "INSERT INTO questions (question_text, option1, option2, option3, option4, correct_option, difficulty) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private Connection connection;
    
    /**
     * Initializes the QuestionDAOImpl with an active database connection.
     * 
     * @throws SQLException if a database access error occurs
     */
    public QuestionDAOImpl() throws SQLException {
        this.connection = getConnection();
    }

    /**
     * Adds a new question to the database.
     * 
     * @param question the question to be added
     * @throws SQLException if a database access error occurs
     */
    @Override
    public void addQuestion(Question question) throws SQLException {
        try (PreparedStatement stmt = connection.prepareStatement(INSERT_QUERY)) {

            stmt.setString(1, question.getQuestionText());
            stmt.setString(2, question.getOption1());
            stmt.setString(3, question.getOption2());
            stmt.setString(4, question.getOption3());
            stmt.setString(5, question.getOption4());
            stmt.setString(6, question.getCorrectOption());
            stmt.setString(7, question.getDifficulty());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // Debugging purpose
            throw e; // Re-throw exception to be handled in the UI
        }
    }

    /**
     * Retrieves a question by its ID.
     * 
     * @param id the ID of the question
     * @return the question with the specified ID, or null if not found
     */
    @Override
    public Question getQuestionById(int id) {
        String sql = "SELECT * FROM questions WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Question(
                    rs.getInt("id"),
                    rs.getString("question_text"),
                    rs.getString("option1"),
                    rs.getString("option2"),
                    rs.getString("option3"),
                    rs.getString("option4"),
                    rs.getString("correct_option"),
                    rs.getString("difficulty")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retrieves all questions from the database that match the given search term.
     * 
     * @param searchTerm the term to search for in the question text, options, and correct answer
     * @return a list of questions matching the search term
     * @throws SQLException if a database access error occurs
     */
    @Override
    public List<Question> getAllQuestions(String searchTerm) throws SQLException {
        List<Question> questions = new ArrayList<>();
        String query = "SELECT * FROM questions WHERE question_text LIKE ? OR option1 LIKE ? OR option2 LIKE ? OR option3 LIKE ? OR option4 LIKE ? OR correct_option LIKE ? OR id LIKE ?";
        PreparedStatement statement = connection.prepareStatement(query);
        String searchPattern = "%" + searchTerm + "%";
        for (int i = 1; i <= 7; i++) {
            statement.setString(i, searchPattern);
        }
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            questions.add(new Question(
                resultSet.getInt("id"),
                resultSet.getString("question_text"),
                resultSet.getString("option1"),
                resultSet.getString("option2"),
                resultSet.getString("option3"),
                resultSet.getString("option4"),
                resultSet.getString("correct_option"),
                resultSet.getString("difficulty")
            ));
        }
        return questions;
    }

    /**
     * Updates an existing question in the database.
     * 
     * @param question the question with updated details
     * @return true if the question was successfully updated, false otherwise
     */
    @Override
    public boolean updateQuestion(Question question) {
        String sql = "UPDATE questions SET question_text = ?, option1 = ?, option2 = ?, option3 = ?, option4 = ?, correct_option = ?, difficulty = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, question.getQuestionText());
            stmt.setString(2, question.getOption1());
            stmt.setString(3, question.getOption2());
            stmt.setString(4, question.getOption3());
            stmt.setString(5, question.getOption4());
            stmt.setString(6, question.getCorrectOption());
            stmt.setString(7, question.getDifficulty());
            stmt.setInt(8, question.getId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Deletes a question from the database by its ID.
     * 
     * @param id the ID of the question to be deleted
     * @return true if the question was successfully deleted, false otherwise
     * @throws SQLException if a database access error occurs
     */
    @Override
    public boolean deleteQuestion(int id) throws SQLException {
        String sql = "DELETE FROM questions WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Retrieves questions by difficulty level.
     * 
     * @param difficulty the difficulty level (Beginner, Intermediate, Advanced)
     * @return a list of questions for the specified difficulty
     */
    @Override
    public List<Question> getQuestionsByDifficulty(String difficulty) {
        List<Question> questionList = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement("SELECT * FROM questions WHERE difficulty = ? LIMIT 5")) {
            ps.setString(1, difficulty);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                questionList.add(new Question(
                    rs.getInt("id"),
                    rs.getString("question_text"),
                    rs.getString("option1"),
                    rs.getString("option2"),
                    rs.getString("option3"),
                    rs.getString("option4"),
                    rs.getString("correct_option"),
                    rs.getString("difficulty")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questionList;
    }
}
