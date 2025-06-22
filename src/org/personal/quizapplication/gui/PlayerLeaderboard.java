package org.personal.quizapplication.gui;

import java.awt.*;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.personal.quizapplication.dao.LeaderboardDAO;
import org.personal.quizapplication.dao.impl.LeaderboardDAOImpl;
import org.personal.quizapplication.model.LeaderboardEntry;
import org.personal.quizapplication.model.User;

/**
 * The PlayerLeaderboard class is responsible for displaying the leaderboard for different quiz difficulties (Beginner, Intermediate, Advanced).
 * It retrieves and displays top 5 leaderboard entries for each difficulty and allows the player to return to the home page.
 */
public class PlayerLeaderboard extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel[] beginnerTop5, intermediateTop5, advancedTop5;
    private LeaderboardDAO leaderboardDAO;
    private User currentUser;

    /**
     * Constructs a PlayerLeaderboard window with the current user's details.
     * Initializes the leaderboard panels and fetches the leaderboard data for each difficulty.
     *
     * @param currentUser the current logged-in user
     */
    public PlayerLeaderboard(User currentUser) {
        this.currentUser = currentUser;
        leaderboardDAO = new LeaderboardDAOImpl();

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 1000, 600);

        contentPane = new JPanel();
        contentPane.setBackground(new Color(48, 60, 81));
        contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
        contentPane.setLayout(new BorderLayout(10, 10));
        setContentPane(contentPane);

        // Top panel (Back Button + Title)
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBackground(new Color(48, 60, 81));

        // Back Button
        JButton btnBack = new JButton("←");
        btnBack.setFont(new Font("SansSerif", Font.BOLD, 20));
        btnBack.setForeground(Color.WHITE);
        btnBack.setBackground(new Color(48, 60, 81));
        btnBack.setBorderPainted(false);
        btnBack.setFocusPainted(false);
        btnBack.setPreferredSize(new Dimension(80, 60));

        btnBack.addActionListener(e -> {
            new PlayerHomePage(currentUser).setVisible(true);
            dispose();
        });

        // Title
        JLabel titleLabel = new JLabel("Leaderboard 🏆", SwingConstants.CENTER);
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 45));

        topPanel.add(btnBack, BorderLayout.WEST);
        topPanel.add(titleLabel, BorderLayout.CENTER);

        // Adding a gap after the title
        topPanel.add(Box.createRigidArea(new Dimension(0, 50)), BorderLayout.SOUTH); // Added vertical gap

        contentPane.add(topPanel, BorderLayout.NORTH);

        // Main leaderboard display panel
        JPanel leaderboardPanel = new JPanel(new GridLayout(1, 3, 20, 10)); // 3 columns for leaderboards
        leaderboardPanel.setBackground(new Color(48, 60, 81));
        contentPane.add(leaderboardPanel, BorderLayout.CENTER);

        // Creating leaderboard columns
        beginnerTop5 = createLeaderboardColumn("Beginner", leaderboardPanel);
        intermediateTop5 = createLeaderboardColumn("Intermediate", leaderboardPanel);
        advancedTop5 = createLeaderboardColumn("Advanced", leaderboardPanel);

        // Fetch and update leaderboard data
        fetchLeaderboardData(beginnerTop5, "Beginner");
        fetchLeaderboardData(intermediateTop5, "Intermediate");
        fetchLeaderboardData(advancedTop5, "Advanced");

        setVisible(true);
    }

    /**
     * Creates a leaderboard column for a given difficulty level and adds it to the provided panel.
     *
     * @param difficulty the difficulty level of the leaderboard (Beginner, Intermediate, Advanced)
     * @param parentPanel the panel to which the leaderboard column will be added
     * @return an array of JLabel elements representing the top 5 players in that difficulty
     */
    private JLabel[] createLeaderboardColumn(String difficulty, JPanel parentPanel) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBackground(new Color(60, 78, 100));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel titleLabel = new JLabel(difficulty + " Leaderboard", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Tahoma", Font.BOLD, 22));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(titleLabel);

        // Adding some space between the title and the first label
        panel.add(Box.createRigidArea(new Dimension(0, 20))); // Increased gap to 20px

        JLabel[] labels = new JLabel[5];
        for (int i = 0; i < 5; i++) {
            labels[i] = new JLabel((i + 1) + ". --- : ---", SwingConstants.CENTER);
            labels[i].setFont(new Font("Tahoma", Font.BOLD, 20));
            labels[i].setForeground(Color.WHITE);
            labels[i].setAlignmentX(Component.CENTER_ALIGNMENT);
            panel.add(labels[i]);
            panel.add(Box.createRigidArea(new Dimension(0, 5))); // Adding a small gap between player entries
        }

        parentPanel.add(panel); // Adding panel to leaderboardPanel
        return labels;
    }

    /**
     * Fetches the leaderboard data for a given difficulty and updates the labels with player information.
     *
     * @param labels the labels to update with leaderboard data
     * @param difficulty the difficulty level for which the leaderboard is being fetched (Beginner, Intermediate, Advanced)
     */
    private void fetchLeaderboardData(JLabel[] labels, String difficulty) {
        try {
            List<LeaderboardEntry> leaderboard = leaderboardDAO.getLeaderboardByDifficulty(difficulty);
            for (int i = 0; i < labels.length && i < leaderboard.size(); i++) {
                LeaderboardEntry entry = leaderboard.get(i);
                labels[i].setText((i + 1) + ". " + entry.getUsername() + " : " + entry.getAvgScore());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
