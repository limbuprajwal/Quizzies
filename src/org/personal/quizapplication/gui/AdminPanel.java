package org.personal.quizapplication.gui;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * The {@code AdminPanel} class provides the administrative interface for managing quiz questions, viewing reports, and logging out.
 * It allows the admin to navigate to different sections of the quiz application like adding or updating questions,
 * viewing all questions, and logging out of the system.
 * This class extends {@link JFrame} and initializes the layout, buttons, and actions associated with the admin panel.
 * 
 * @author [Prajwal Limbu]
 * @version 1.0
 */
public class AdminPanel extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;

    /**
     * Main method to launch the AdminPanel GUI.
     * It creates an instance of {@code AdminPanel} and sets it visible.
     *
     * @param args Command line arguments (not used in this case).
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AdminPanel frame = new AdminPanel();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Constructor that initializes the admin panel layout, buttons, and actions.
     * This method sets up the side navigation panel, event listeners for button clicks, and defines the panel's overall layout.
     */
    public AdminPanel() {
        // Set the properties of the JFrame (window)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 600); // Window size
        contentPane = new JPanel();
        contentPane.setBackground(new Color(48, 60, 81));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Side panel containing the navigation buttons
        JPanel sidePanel = new JPanel();
        sidePanel.setBackground(Color.WHITE);
        sidePanel.setBorder(new EmptyBorder(0, 0, 0, 0));
        sidePanel.setBounds(2, 0, 198, 572);
        contentPane.add(sidePanel);
        sidePanel.setLayout(null);

        // Add the logo image on the side panel
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setBounds(-14, -16, 206, 118);
        lblNewLabel.setEnabled(false);
        sidePanel.add(lblNewLabel);
        lblNewLabel.setBackground(Color.BLACK);
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setIcon(new ImageIcon(AdminPanel.class.getResource("/icon/QuizziesLogo.png")));

        // Add 'Add Question' button and listener to navigate to AddQuestionScreen
        JLabel addQuestion = new JLabel("Add Question");
        addQuestion.setFont(new Font("Tahoma", Font.BOLD, 17));
        addQuestion.setIcon(new ImageIcon(AdminPanel.class.getResource("/icon/add.png")));
        addQuestion.setBounds(6, 142, 171, 59);
        sidePanel.add(addQuestion);
        addQuestion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                new AddQuestionScreen(); // Open AddQuestionScreen
                dispose(); // Close the current admin panel
            }
        });

        // Add 'Logout' button and listener to confirm and logout
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
                    dispose(); // Close current admin panel
                    new LoginPage().setVisible(true); // Open LoginPage
                }
            }
        });

        // Add 'Update Question' button and listener to navigate to UpdateQuestionScreen
        JLabel updateQuestion = new JLabel("Update Question");
        updateQuestion.setIcon(new ImageIcon(AdminPanel.class.getResource("/icon/pen.png")));
        updateQuestion.setFont(new Font("Tahoma", Font.BOLD, 17));
        updateQuestion.setBounds(6, 213, 186, 59);
        sidePanel.add(updateQuestion);
        updateQuestion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new UpdateQuestionScreen(); // Open UpdateQuestionScreeni 
            }
        });

        // Add 'All Question' button and listener to navigate to AllQuestionScreen
        JLabel allQuestion = new JLabel("All Question");
        allQuestion.setIcon(new ImageIcon(AdminPanel.class.getResource("/icon/select.png")));
        allQuestion.setFont(new Font("Tahoma", Font.BOLD, 17));
        allQuestion.setBounds(6, 284, 171, 59);
        sidePanel.add(allQuestion);
        allQuestion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new AllQuestionScreen(); // Open AllQuestionScreen
            }
        });

        // Add 'View Report' button and listener to navigate to ViewReportScreen
        JLabel viewReport = new JLabel("View Report\n");
        viewReport.setIcon(new ImageIcon(AdminPanel.class.getResource("/icon/report.png")));
        viewReport.setFont(new Font("Tahoma", Font.BOLD, 17));
        viewReport.setBounds(6, 355, 171, 59);
        sidePanel.add(viewReport);
        viewReport.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new ViewReportScreen(); // Open ViewReportScreen
            }
        });

        // Main content panel for additional content (if needed)
        JPanel panel = new JPanel();
        panel.setBorder(null);
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setBounds(200, 0, 800, 572);
        contentPane.add(panel);

        // Set the window visible
        setVisible(true);
    }
}
