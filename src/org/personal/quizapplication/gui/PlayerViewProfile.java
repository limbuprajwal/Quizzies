package org.personal.quizapplication.gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.personal.quizapplication.model.User;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

/**
 * The PlayerViewProfile class is responsible for displaying the profile information of a player, including their game statistics and scores for various difficulty levels.
 * It retrieves the data from a database and displays it in a user-friendly interface.
 */
public class PlayerViewProfile extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private User currentUser;

    private JLabel usernameLabel, gamesPlayedLabel, highestBeginnerLabel, highestIntermediateLabel, highestAdvancedLabel, highestOverallLabel;
    private JLabel avgBeginnerLabel, avgIntermediateLabel, avgAdvancedLabel, avgOverallLabel;

    /**
     * The main method launches the PlayerViewProfile frame, mainly for testing purposes.
     * It passes null for the user parameter, which is typically set in the constructor.
     *
     * @param args command line arguments
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    PlayerViewProfile frame = new PlayerViewProfile(null);
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Constructs a PlayerViewProfile window with the specified user data.
     * Initializes the UI components and loads the user's profile data from the database.
     *
     * @param user the current logged-in user whose profile will be displayed
     */
    public PlayerViewProfile(User user) {
        this.currentUser = user;

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 600);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(48, 60, 81));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setForeground(Color.WHITE);
        lblNewLabel.setEnabled(false);
        lblNewLabel.setIcon(new ImageIcon(PlayerHomePage.class.getResource("/icon/QuizziesLogo.png")));
        lblNewLabel.setBounds(6, -37, 198, 152);
        contentPane.add(lblNewLabel);

        // Back Button
        JButton btnBack = new JButton("←");
        btnBack.setFont(new Font("SansSerif", Font.BOLD, 20));
        btnBack.setForeground(Color.WHITE);
        btnBack.setBackground(new Color(48, 60, 81));
        btnBack.setBorderPainted(false);
        btnBack.setFocusPainted(false);
        btnBack.setBounds(-21, 6, 121, 40);
        contentPane.add(btnBack);

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlayerHomePage playerHomePage = new PlayerHomePage(currentUser);
                playerHomePage.setVisible(true);
                dispose();
            }
        });

        JLabel lblTitle = new JLabel("Player Profile");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Lucida Grande", Font.BOLD, 30));
        lblTitle.setBounds(386, 47, 212, 45);
        contentPane.add(lblTitle);

        JLabel lblStats = new JLabel("Stats");
        lblStats.setFont(new Font("Tahoma", Font.BOLD, 30));
        lblStats.setForeground(Color.WHITE);
        lblStats.setBounds(48, 206, 89, 27);
        contentPane.add(lblStats);

        // Username Label
        JLabel lblUsernameTitle = new JLabel("Username:");
        lblUsernameTitle.setForeground(Color.WHITE);
        lblUsernameTitle.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblUsernameTitle.setBounds(48, 145, 121, 27);
        contentPane.add(lblUsernameTitle);

        usernameLabel = createLabel("", 180, 145);  // Will be updated by loadUserData()

        // Stats Labels
        gamesPlayedLabel = createLabel("Games Played: ", 48, 259);
        highestBeginnerLabel = createLabel("Highest Beginner Score: ", 48, 308);
        highestIntermediateLabel = createLabel("Highest Intermediate Score: ", 48, 347);
        highestAdvancedLabel = createLabel("Highest Advanced Score: ", 48, 386);
        highestOverallLabel = createLabel("Overall Highest Score: ", 48, 426);

        avgBeginnerLabel = createLabel("Avg Beginner Score: ", 557, 308);
        avgIntermediateLabel = createLabel("Avg Intermediate Score: ", 557, 347);
        avgAdvancedLabel = createLabel("Avg Advanced Score: ", 557, 386);
        avgOverallLabel = createLabel("Overall Avg Score: ", 557, 426);
        
        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setIcon(new ImageIcon(PlayerViewProfile.class.getResource("/icon/Player.png")));
        lblNewLabel_1.setBounds(599, 47, 61, 36);
        contentPane.add(lblNewLabel_1);

        // Load user data from database
        loadUserData();

        setVisible(true);
    }

    /**
     * Creates and adds a label dynamically to the content pane.
     * 
     * @param text the text to display on the label
     * @param x the x-coordinate of the label's position
     * @param y the y-coordinate of the label's position
     * @return the created JLabel
     */
    private JLabel createLabel(String text, int x, int y) {
        JLabel label = new JLabel(text);
        label.setFont(new Font("Tahoma", Font.BOLD, 18));
        label.setForeground(Color.WHITE);
        label.setBounds(x, y, 400, 27);
        contentPane.add(label);
        return label;
    }

    /**
     * Establishes a connection to the MySQL database.
     * 
     * @return the database connection
     * @throws SQLException if a database access error occurs
     */
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/QuizApplication", "root", "");
    }

    /**
     * Loads and displays the user data from the database.
     * The method fetches user-specific data such as username, game statistics, and scores,
     * and updates the corresponding UI components with this information.
     */
    private void loadUserData() {
        if (currentUser == null) {
            return;
        }

        try (Connection conn = getConnection()) {
            // Query to get username and scores
            String query = "SELECT u.username, q.games_played, q.beginner_score, q.intermediate_score, q.advanced_score, " +
                           "q.beginner_avg, q.intermediate_avg, q.advanced_avg, q.average_score " +
                           "FROM users u " +
                           "JOIN quiz_scores q ON u.id = q.user_id " +
                           "WHERE u.id = ?";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, currentUser.getId());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String username = rs.getString("username");
                int gamesPlayed = rs.getInt("games_played");
                double beginnerScore = rs.getDouble("beginner_score");
                double intermediateScore = rs.getDouble("intermediate_score");
                double advancedScore = rs.getDouble("advanced_score");
                double beginnerAvg = rs.getDouble("beginner_avg");
                double intermediateAvg = rs.getDouble("intermediate_avg");
                double advancedAvg = rs.getDouble("advanced_avg");
                double overallAvg = rs.getDouble("average_score");

                // Update labels with fetched data
                usernameLabel.setText(username);
                gamesPlayedLabel.setText("Games Played: " + gamesPlayed);
                highestBeginnerLabel.setText("Highest Beginner Score: " + beginnerScore);
                highestIntermediateLabel.setText("Highest Intermediate Score: " + intermediateScore);
                highestAdvancedLabel.setText("Highest Advanced Score: " + advancedScore);
                highestOverallLabel.setText("Overall Highest Score: " + Math.max(beginnerScore, Math.max(intermediateScore, advancedScore)));

                avgBeginnerLabel.setText("Avg Beginner Score: " + beginnerAvg);
                avgIntermediateLabel.setText("Avg Intermediate Score: " + intermediateAvg);
                avgAdvancedLabel.setText("Avg Advanced Score: " + advancedAvg);
                avgOverallLabel.setText("Overall Avg Score: " + overallAvg);
            }

            // Close resources
            rs.close();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
