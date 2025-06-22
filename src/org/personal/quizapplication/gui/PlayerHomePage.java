package org.personal.quizapplication.gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import org.personal.quizapplication.model.User;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * The PlayerHomePage class represents the main dashboard for the player after they log in.
 * It provides options for the player to play a quiz, view the leaderboard, and view their profile.
 * This class interacts with various other screens like the quiz page, leaderboard, and profile screen.
 */
public class PlayerHomePage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private User currentUser;

    /**
     * Constructs the PlayerHomePage with the given user object.
     * Initializes the graphical components and sets up event listeners for buttons.
     *
     * @param user the current player user object.
     */
    public PlayerHomePage(User user) {
        this.currentUser = user;

        // Set up the JFrame settings
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 600);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(48, 60, 81));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Back Button with Arrow Icon
        JButton btnBack = new JButton("←");
        btnBack.setFont(new Font("SansSerif", Font.BOLD, 20));
        btnBack.setForeground(Color.WHITE);
        btnBack.setBackground(new Color(48, 60, 81));
        btnBack.setBorderPainted(false);
        btnBack.setFocusPainted(false);
        btnBack.setBounds(-21, 6, 121, 40);
        contentPane.add(btnBack);

        // ActionListener for back button: Log out and return to login page
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(
                    null, 
                    "Are you sure you want to log out?", 
                    "Log Out", 
                    JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    // Show LoginPage after logout
                    LoginPage loginPage = new LoginPage();
                    loginPage.setVisible(true);
                    dispose(); // Close current PlayerHomePage window
                }
            }
        });

        // Display Logo
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setEnabled(false);
        lblNewLabel.setIcon(new ImageIcon(PlayerHomePage.class.getResource("/icon/QuizziesLogo.png")));
        lblNewLabel.setBounds(6, -37, 198, 152);
        contentPane.add(lblNewLabel);

        // Play Quiz Button
        JButton playBtn = new JButton("Play Quiz");
        playBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new QuizDifficulty(currentUser).setVisible(true);
                dispose(); // Close the dashboard window
            }
        });
        playBtn.setFont(new Font("Tahoma", Font.BOLD, 40));
        playBtn.setForeground(new Color(59, 78, 103));
        playBtn.setBackground(new Color(59, 78, 103));
        playBtn.setBounds(384, 93, 287, 114);
        contentPane.add(playBtn);

        // Leaderboard Button
        JButton btnLeaderboard = new JButton("Leaderboard\n");
        btnLeaderboard.setForeground(new Color(59, 78, 103));
        btnLeaderboard.setFont(new Font("Tahoma", Font.BOLD, 39));
        btnLeaderboard.setBackground(new Color(59, 78, 103));
        btnLeaderboard.setBounds(384, 239, 287, 114);
        contentPane.add(btnLeaderboard);

        // ActionListener for leaderboard button: Open PlayerLeaderboard screen
        btnLeaderboard.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new PlayerLeaderboard(currentUser).setVisible(true);
            }
        });

        // Profile Button
        JButton btnProfile = new JButton("Profile");
        btnProfile.setForeground(new Color(59, 78, 103));
        btnProfile.setFont(new Font("Tahoma", Font.BOLD, 39));
        btnProfile.setBackground(new Color(59, 78, 103));
        btnProfile.setBounds(384, 390, 287, 114);
        contentPane.add(btnProfile);

        // ActionListener for profile button: Open PlayerViewProfile screen
        btnProfile.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new PlayerViewProfile(currentUser).setVisible(true);
            }
        });

        setVisible(true);
    }

    /**
     * Main method to launch the PlayerHomePage.
     * 
     * @param args command-line arguments (not used in this implementation).
     */
    // Uncomment and use for testing purposes
    // public static void main(String[] args) {
    //     User dummyUser = new User(1,"john", "password123"); // Replace with your User's data
    //     EventQueue.invokeLater(new Runnable() {
    //         public void run() {
    //             try {
    //                 PlayerHomePage frame = new PlayerHomePage(dummyUser);
    //                 frame.setVisible(true);
    //             } catch (Exception e) {
    //                 e.printStackTrace();
    //             }
    //         }
    //     });
    // }
}
