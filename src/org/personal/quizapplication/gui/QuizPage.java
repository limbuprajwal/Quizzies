package org.personal.quizapplication.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import org.personal.quizapplication.model.Question;
import org.personal.quizapplication.model.User;
import org.personal.quizapplication.dao.QuestionDAO;
import org.personal.quizapplication.dao.QuizHistoryDAO;
import org.personal.quizapplication.dao.QuizScoresDAO;
import org.personal.quizapplication.dao.impl.QuestionDAOImpl;
import org.personal.quizapplication.model.QuizHistory;
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;
import static org.personal.quizapplication.connection.ConnectionFactory.getConnection;

/**
 * A JFrame class representing the quiz page where users can answer questions based on the selected difficulty.
 * This class displays a series of questions, allows users to select answers, and tracks the score.
 * Once the quiz is completed, it stores the score and displays the results.
 */
public class QuizPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel questionLabel, difficultyLabel;
    private JRadioButton option1, option2, option3, option4;
    private ButtonGroup optionsGroup;
    private JButton nextButton, submitButton;
    private User currentUser;
    private String difficulty;
    private List<Question> questions;
    private int currentQuestionIndex = 0;
    private int score = 0;
    private int correctAnswers = 0;
    private QuestionDAO questionDAO;
    private QuizHistoryDAO quizHistoryDAO;

    /**
     * Constructs a new QuizPage to present a quiz to the user.
     * 
     * @param user the User object representing the current player.
     * @param difficulty the difficulty level selected by the user.
     */
    public QuizPage(User user, String difficulty) {
        this.currentUser = user;
        this.difficulty = difficulty;

        try {
            this.questionDAO = new QuestionDAOImpl();
            this.quizHistoryDAO = new QuizHistoryDAO(); // Fixed initialization
        } catch (SQLException e) {
            e.printStackTrace();
        }

        this.questions = questionDAO.getQuestionsByDifficulty(difficulty);
        Collections.shuffle(this.questions);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 600);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(48, 60, 81));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Back Button with Confirmation Dialog
        JButton btnBack = new JButton("←");
        btnBack.setFont(new Font("SansSerif", Font.BOLD, 20));
        btnBack.setForeground(Color.WHITE);
        btnBack.setBackground(new Color(48, 60, 81));
        btnBack.setBorderPainted(false);
        btnBack.setFocusPainted(false);
        btnBack.setBounds(10, 10, 75, 40);
        contentPane.add(btnBack);

        btnBack.addActionListener(e -> {
            int response = JOptionPane.showConfirmDialog(
                QuizPage.this,
                "Are you sure you want to exit the quiz? Your progress will be lost.",
                "Exit Quiz",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE
            );

            if (response == JOptionPane.YES_OPTION) {
                new PlayerHomePage(currentUser).setVisible(true);
                dispose();
            }
        });

        JLabel lblLogo = new JLabel("");
        lblLogo.setIcon(new ImageIcon(QuizPage.class.getResource("/icon/QuizziesLogo.png")));
        lblLogo.setBounds(6, -37, 198, 152);
        contentPane.add(lblLogo);

        questionLabel = new JLabel("");
        questionLabel.setForeground(Color.WHITE);
        questionLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
        questionLabel.setBounds(50, 140, 900, 90);
        contentPane.add(questionLabel);

        difficultyLabel = new JLabel("Difficulty: " + difficulty);
        difficultyLabel.setForeground(Color.WHITE);
        difficultyLabel.setFont(new Font("Tahoma", Font.BOLD, 15));
        difficultyLabel.setBounds(782, 74, 212, 30);
        contentPane.add(difficultyLabel);

        // Options
        option1 = new JRadioButton();
        option1.setForeground(Color.WHITE);
        option2 = new JRadioButton();
        option2.setForeground(Color.WHITE);
        option3 = new JRadioButton();
        option3.setForeground(Color.WHITE);
        option4 = new JRadioButton();
        option4.setForeground(Color.WHITE);
        option1.setBackground(new Color(48, 60, 81));
        option2.setBackground(new Color(48, 60, 81));
        option3.setBackground(new Color(48, 60, 81));
        option4.setBackground(new Color(48, 60, 81));

        optionsGroup = new ButtonGroup();
        optionsGroup.add(option1);
        optionsGroup.add(option2);
        optionsGroup.add(option3);
        optionsGroup.add(option4);

        option1.setBounds(50, 270, 400, 30);
        option2.setBounds(50, 310, 400, 30);
        option3.setBounds(50, 350, 400, 30);
        option4.setBounds(50, 390, 400, 30);

        contentPane.add(option1);
        contentPane.add(option2);
        contentPane.add(option3);
        contentPane.add(option4);

        // Buttons
        nextButton = new JButton("Next");
        nextButton.setBounds(314, 480, 120, 50);
        nextButton.setFont(new Font("Tahoma", Font.BOLD, 18));
        nextButton.addActionListener(e -> nextQuestion());
        contentPane.add(nextButton);

        submitButton = new JButton("Submit");
        submitButton.setBounds(589, 480, 120, 50);
        submitButton.setFont(new Font("Tahoma", Font.BOLD, 18));
        submitButton.setEnabled(false);
        submitButton.addActionListener(e -> submitQuiz());
        contentPane.add(submitButton);

        if (!questions.isEmpty()) {
            showQuestion(0);
        } else {
            JOptionPane.showMessageDialog(this, "No questions available for this difficulty.");
            dispose();
        }

        setVisible(true);
    }

    /**
     * Displays the question at the specified index in the quiz.
     * 
     * @param index the index of the question to display.
     */
    private void showQuestion(int index) {
        if (index < questions.size()) {
            Question q = questions.get(index);
            questionLabel.setText(q.getQuestionText());
            option1.setText(q.getOption1());
            option2.setText(q.getOption2());
            option3.setText(q.getOption3());
            option4.setText(q.getOption4());

            optionsGroup.clearSelection();
        }
    }

    /**
     * Moves to the next question in the quiz.
     * It checks the user's current answer and updates the score.
     */
    private void nextQuestion() {
        checkAnswer();
        currentQuestionIndex++;
        if (currentQuestionIndex < questions.size()) {
            showQuestion(currentQuestionIndex);
        } 
        if (currentQuestionIndex == questions.size() - 1) {
            nextButton.setEnabled(false);
            submitButton.setEnabled(true);
        }
    }

    /**
     * Checks if the selected option is correct for the current question.
     * If the answer is correct, the score is updated based on the difficulty level.
     */
    private void checkAnswer() {
        if (currentQuestionIndex >= questions.size()) return;

        Question q = questions.get(currentQuestionIndex);
        JRadioButton selected = getSelectedOption();
        if (selected != null && q.isCorrectAnswer(selected.getText())) {
            correctAnswers++;
            switch (difficulty) {
                case "Beginner": score += 1; break;
                case "Intermediate": score += 2; break;
                case "Advanced": score += 3; break;
            }
        }
    }

    /**
     * Returns the selected radio button option.
     * 
     * @return the selected radio button, or null if no option is selected.
     */
    private JRadioButton getSelectedOption() {
        if (option1.isSelected()) return option1;
        if (option2.isSelected()) return option2;
        if (option3.isSelected()) return option3;
        if (option4.isSelected()) return option4;
        return null;
    }

    /**
     * Submits the quiz, stores the score, and displays the quiz end screen.
     */
    private void submitQuiz() {
        checkAnswer();
        storeScore();
        new QuizEndPage(currentUser, score).setVisible(true);
        dispose();
    }

    /**
     * Stores the user's score and quiz history in the database.
     * This method updates the quiz history and quiz scores for the current user.
     */
    private void storeScore() {
        try (Connection conn = getConnection()) {
            QuizHistory quizHistory = new QuizHistory(currentUser.getId(), difficulty, score, questions.size(), correctAnswers);
            quizHistoryDAO.insertQuizHistory(quizHistory);

            QuizScoresDAO quizScoresDAO = new QuizScoresDAO(conn);
            
            double beginnerAvg = quizScoresDAO.getAverageScore(currentUser.getId(), "Beginner");
            double intermediateAvg = quizScoresDAO.getAverageScore(currentUser.getId(), "Intermediate");
            double advancedAvg = quizScoresDAO.getAverageScore(currentUser.getId(), "Advanced");
            double overallAvg = quizScoresDAO.getOverallAverage(currentUser.getId());

            double beginnerHigh = quizScoresDAO.getHighestScore(currentUser.getId(), "Beginner");
            double intermediateHigh = quizScoresDAO.getHighestScore(currentUser.getId(), "Intermediate");
            double advancedHigh = quizScoresDAO.getHighestScore(currentUser.getId(), "Advanced");

            int gamesPlayed = quizScoresDAO.getGamesPlayed(currentUser.getId(), difficulty);

            // Call upsertQuizScores with the correct parameters
            quizScoresDAO.upsertQuizScores(
                currentUser.getId(), beginnerAvg, intermediateAvg, advancedAvg, overallAvg, 
                beginnerHigh, intermediateHigh, advancedHigh, gamesPlayed
            );
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
