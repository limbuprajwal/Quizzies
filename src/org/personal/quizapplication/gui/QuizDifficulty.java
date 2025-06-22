package org.personal.quizapplication.gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import org.personal.quizapplication.model.User;

/**
 * A JFrame class representing the quiz difficulty selection screen. 
 * This screen allows the user to choose a difficulty level for the quiz.
 * The available difficulty levels are Beginner, Intermediate, and Advanced.
 * Upon selection, the quiz for the chosen difficulty is started.
 */
public class QuizDifficulty extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private User currentUser;

    /**
     * Constructs a new QuizDifficulty screen for the specified user.
     * This initializes the screen components including buttons for difficulty selection.
     * 
     * @param user the User object representing the current player.
     */
    public QuizDifficulty(User user) {
        this.currentUser = user;

        setTitle("Select Difficulty");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 600);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(48, 60, 81));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(null);
        setContentPane(contentPane);

        // Back Button with Arrow Icon
        JButton btnBack = new JButton("←");
        btnBack.setFont(new Font("SansSerif", Font.BOLD, 20));
        btnBack.setForeground(Color.WHITE);
        btnBack.setBackground(new Color(48, 60, 81));
        btnBack.setBorderPainted(false);
        btnBack.setFocusPainted(false);
        btnBack.setBounds(10, 10, 83, 44);
        contentPane.add(btnBack);

        btnBack.addActionListener(e -> {
            new PlayerHomePage(user).setVisible(true);
            dispose();
        });

        // Logo
        JLabel logoLabel = new JLabel();
        logoLabel.setIcon(new ImageIcon(QuizDifficulty.class.getResource("/icon/QuizziesLogo.png")));
        logoLabel.setBounds(10, -10, 198, 152);
        contentPane.add(logoLabel);

        // Title
        JLabel titleLabel = new JLabel("Let's Play!");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 40));
        titleLabel.setBounds(384, 66, 269, 59);
        contentPane.add(titleLabel);

        JLabel subTitleLabel = new JLabel("Choose a level:");
        subTitleLabel.setForeground(Color.WHITE);
        subTitleLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
        subTitleLabel.setBounds(384, 150, 396, 59);
        contentPane.add(subTitleLabel);

        // Buttons
        JButton beginnerBtn = new JButton("Beginner");
        beginnerBtn.setFont(new Font("Tahoma", Font.BOLD, 30));
        beginnerBtn.setForeground(new Color(59, 78, 103));
        beginnerBtn.setBackground(new Color(59, 78, 103));
        beginnerBtn.setBounds(384, 213, 287, 89);
        contentPane.add(beginnerBtn);

        JButton intermediateBtn = new JButton("Intermediate");
        intermediateBtn.setFont(new Font("Tahoma", Font.BOLD, 30));
        intermediateBtn.setForeground(new Color(59, 78, 103));
        intermediateBtn.setBackground(new Color(59, 78, 103));
        intermediateBtn.setBounds(384, 328, 287, 89);
        contentPane.add(intermediateBtn);

        JButton advancedBtn = new JButton("Advanced");
        advancedBtn.setFont(new Font("Tahoma", Font.BOLD, 30));
        advancedBtn.setForeground(new Color(59, 78, 103));
        advancedBtn.setBackground(new Color(59, 78, 103));
        advancedBtn.setBounds(384, 441, 287, 89);
        contentPane.add(advancedBtn);

        // Action Listeners
        beginnerBtn.addActionListener(e -> startQuiz(user, "Beginner"));
        intermediateBtn.addActionListener(e -> startQuiz(user, "Intermediate"));
        advancedBtn.addActionListener(e -> startQuiz(user, "Advanced"));

        setVisible(true);
    }

    /**
     * Starts the quiz for the selected difficulty.
     * 
     * @param user the User object representing the current player.
     * @param difficulty the selected difficulty level ("Beginner", "Intermediate", or "Advanced").
     */
    private void startQuiz(User user, String difficulty) {
        new QuizPage(user, difficulty).setVisible(true);
        dispose();
    }
}
