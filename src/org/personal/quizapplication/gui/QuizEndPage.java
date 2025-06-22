package org.personal.quizapplication.gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import org.personal.quizapplication.model.User;

/**
 * A JFrame class representing the quiz end screen.
 * This screen displays the user's score after completing a quiz and provides an option to return to the homepage.
 */
public class QuizEndPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel scoreLabel;
    private JButton homepageBtn;

    /**
     * Constructs a new QuizEndPage screen to display the user's score.
     * 
     * @param user the User object representing the current player.
     * @param score the score the user achieved in the quiz.
     */
    public QuizEndPage(User user, int score) {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 600);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(48, 60, 81));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Logo
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setEnabled(false);
        lblNewLabel.setIcon(new ImageIcon(QuizEndPage.class.getResource("/icon/QuizziesLogo.png")));
        lblNewLabel.setBounds(6, -37, 198, 152);
        contentPane.add(lblNewLabel);

        // Congratulations Text
        JLabel congratsLabel = new JLabel("Congratulations!");
        congratsLabel.setForeground(Color.WHITE);
        congratsLabel.setFont(new Font("Tahoma", Font.BOLD, 50));
        congratsLabel.setBounds(281, 152, 428, 120);
        contentPane.add(congratsLabel);

        // Score Display
        scoreLabel = new JLabel("You scored: " + score);
        scoreLabel.setFont(new Font("Tahoma", Font.BOLD, 45));
        scoreLabel.setForeground(Color.WHITE);
        scoreLabel.setBounds(320, 272, 400, 96);
        contentPane.add(scoreLabel);

        // Homepage Button
        homepageBtn = new JButton("Homepage");
        homepageBtn.setFont(new Font("Tahoma", Font.BOLD, 35));
        homepageBtn.setForeground(new Color(59, 78, 103));
        homepageBtn.setBackground(new Color(70, 130, 180));
        homepageBtn.setBounds(356, 400, 297, 80);
        contentPane.add(homepageBtn);

        // Action Listener for Homepage Button
        homepageBtn.addActionListener(e -> {
            new PlayerHomePage(user).setVisible(true);
            dispose();
        });

        setVisible(true);
    }
}
