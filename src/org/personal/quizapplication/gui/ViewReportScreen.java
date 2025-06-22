package org.personal.quizapplication.gui;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import org.personal.quizapplication.model.LeaderboardEntry;
import org.personal.quizapplication.model.QuizStatistics;
import org.personal.quizapplication.services.QuizService;

/**
 * ViewReportScreen is a GUI class that displays the quiz statistics and leaderboard in a report format.
 * It includes a table for displaying the leaderboard and a text area for showing quiz statistics.
 * The screen also provides navigation options to other parts of the application.
 */
public class ViewReportScreen extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private JTextArea statsTextArea;
    private QuizService quizService;

    /**
     * Main method to launch the ViewReportScreen GUI.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    ViewReportScreen frame = new ViewReportScreen();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Constructor for initializing the ViewReportScreen.
     * Sets up the layout, components, and loads data.
     */
    public ViewReportScreen() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 600);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(48, 60, 81));
        contentPane.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel sidePanel = new JPanel();
        sidePanel.setBackground(Color.WHITE);
        sidePanel.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
        sidePanel.setBounds(2, 0, 198, 572);
        contentPane.add(sidePanel);
        sidePanel.setLayout(null);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setBounds(-14, -16, 206, 118);
        lblNewLabel.setEnabled(false);
        sidePanel.add(lblNewLabel);
        lblNewLabel.setBackground(Color.BLACK);
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setIcon(new ImageIcon(AdminPanel.class.getResource("/icon/QuizziesLogo.png")));

        // Back Button with Arrow Icon
        JButton btnBack = new JButton("←");
        btnBack.setFont(new Font("SansSerif", Font.BOLD, 20));
        btnBack.setForeground(Color.BLACK);
        btnBack.setBackground(Color.BLACK);
        btnBack.setBounds(6, 6, 178, 84);
        contentPane.add(btnBack);

        btnBack.addActionListener(e -> {
            new AdminPanel(); // Open the main window
            dispose(); // Close this window
        });

        // View Report
        JLabel viewReport = new JLabel("View Report");
        viewReport.setFont(new Font("Tahoma", Font.BOLD, 17));
        viewReport.setIcon(new ImageIcon(AddQuestionScreen.class.getResource("/icon/report.png")));
        viewReport.setBounds(6, 355, 171, 59);
        sidePanel.add(viewReport);
        viewReport.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent e) {
                dispose();
                new ViewReportScreen().setVisible(true);
            }
        });

        JLabel addQuestion = new JLabel("Add Question");
        addQuestion.setFont(new Font("Tahoma", Font.BOLD, 17));
        addQuestion.setIcon(new ImageIcon(AdminPanel.class.getResource("/icon/add.png")));
        addQuestion.setBounds(6, 142, 171, 59);
        sidePanel.add(addQuestion);
        addQuestion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new AddQuestionScreen();
                dispose();
            }
        });

        JLabel logOut = new JLabel("Logout");
        logOut.setIcon(new ImageIcon(AdminPanel.class.getResource("/icon/logout.png")));
        logOut.setFont(new Font("Tahoma", Font.BOLD, 17));
        logOut.setBounds(21, 507, 171, 59);
        sidePanel.add(logOut);

        logOut.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int a = JOptionPane.showConfirmDialog(null, "Do you want to Logout?", "Select", JOptionPane.YES_NO_OPTION);
                if (a == 0) {
                    dispose();
                    new LoginPage().setVisible(true);
                }
            }
        });

        JLabel updateQuestion = new JLabel("Update Question");
        updateQuestion.setIcon(new ImageIcon(AdminPanel.class.getResource("/icon/pen.png")));
        updateQuestion.setFont(new Font("Tahoma", Font.BOLD, 17));
        updateQuestion.setBounds(6, 213, 186, 59);
        sidePanel.add(updateQuestion);
        updateQuestion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new UpdateQuestionScreen();
            }
        });

        JLabel allQuestion = new JLabel("All Question");
        allQuestion.setIcon(new ImageIcon(AdminPanel.class.getResource("/icon/select.png")));
        allQuestion.setFont(new Font("Tahoma", Font.BOLD, 17));
        allQuestion.setBounds(6, 284, 171, 59);
        sidePanel.add(allQuestion);
        allQuestion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new AllQuestionScreen();
            }
        });

        // Stats Panel
        statsTextArea = new JTextArea();
        statsTextArea.setEditable(false);
        statsTextArea.setFont(new Font("Tahoma", Font.PLAIN, 20));
        statsTextArea.setMargin(new Insets(10, 10, 10, 10));
        JScrollPane statsScrollPane = new JScrollPane(statsTextArea);
        statsScrollPane.setPreferredSize(new Dimension(400, 200));
        statsScrollPane.setBounds(200, 150, 402, 332);
        contentPane.add(statsScrollPane);

        // Leaderboard Table
        String[] columnNames = {"Rank", "Player", "Average Score"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        table = new JTable(model);
        JScrollPane tableScrollPane = new JScrollPane(table);
        tableScrollPane.setBounds(602, 0, 398, 572);
        contentPane.add(tableScrollPane);

        JLabel lblNewLabel_1 = new JLabel("Statistical Summary:");
        lblNewLabel_1.setForeground(Color.WHITE);
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 25));
        lblNewLabel_1.setBounds(212, 94, 262, 44);
        contentPane.add(lblNewLabel_1);

        quizService = new QuizService();  // Initialize service layer
        loadStatistics();
        loadLeaderboard();

        setVisible(true);
    }

    /**
     * Loads the quiz statistics and displays them in the stats text area.
     */
    private void loadStatistics() {
        QuizStatistics stats = quizService.getQuizStatistics();
        if (stats != null) {
            statsTextArea.setText("Total Players: " + stats.getTotalPlayers() + "\n"
                    + "Overall Average Score: " + String.format("%.2f", stats.getAvgScore()) + "\n"
                    + "Best Beginner Score: " + stats.getBestBeginner() + "\n"
                    + "Best Intermediate Score: " + stats.getBestIntermediate() + "\n"
                    + "Best Advanced Score: " + stats.getBestAdvanced() + "\n"
                    + "Total Questions Attempted: " + stats.getTotalQuestions() + "\n"
                    + "Total Correct Answers: " + stats.getTotalCorrectAnswers());
        }
    }

    /**
     * Loads the leaderboard entries and populates the table with player ranks, names, and average scores.
     */
    private void loadLeaderboard() {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        model.setRowCount(0);

        java.util.List<LeaderboardEntry> leaderboard = quizService.getLeaderboard();
        int rank = 1;
        for (LeaderboardEntry entry : leaderboard) {
            model.addRow(new Object[]{rank++, entry.getUsername(), entry.getAvgScore()});
        }
    }
}
