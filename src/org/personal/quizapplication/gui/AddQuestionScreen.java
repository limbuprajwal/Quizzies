package org.personal.quizapplication.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.personal.quizapplication.connection.ConnectionFactory;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class AddQuestionScreen extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField questionField;
    private JTextField option1Field;
    private JTextField option2Field;
    private JTextField option3Field;
    private JTextField option4Field;
    private JTextField answerField;
    
    private JComboBox<String> difficultyComboBox;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AddQuestionScreen frame = new AddQuestionScreen();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public AddQuestionScreen() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 600);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(48, 60, 81));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JPanel sidePanel = new JPanel();
        sidePanel.setBackground(Color.WHITE);
        sidePanel.setBounds(2, 0, 198, 572);
        contentPane.add(sidePanel);
        sidePanel.setLayout(null);
        
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setEnabled(false);
        lblNewLabel.setBounds(-14, -16, 206, 118);
        sidePanel.add(lblNewLabel);
        lblNewLabel.setIcon(new ImageIcon(AddQuestionScreen.class.getResource("/icon/QuizziesLogo.png")));
        
        // Back Button with Arrow Icon
        JButton btnBack = new JButton("←");
        btnBack.setFont(new Font("SansSerif", Font.BOLD, 20));
        btnBack.setBounds(6, 6, 178, 84);
        contentPane.add(btnBack);
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminPanel(); // Open the main window
                dispose(); // Close this window
            }
        });
        
        // Add Question
        JLabel addQuestion = new JLabel("Add Question");
        addQuestion.setFont(new Font("Tahoma", Font.BOLD, 17));
        addQuestion.setIcon(new ImageIcon(AddQuestionScreen.class.getResource("/icon/add.png")));
        addQuestion.setBounds(6, 142, 171, 59);
        sidePanel.add(addQuestion);
        addQuestion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new AddQuestionScreen().setVisible(true);
            }
        });

        // Update Question
        JLabel updateQuestion = new JLabel("Update Question");
        updateQuestion.setFont(new Font("Tahoma", Font.BOLD, 17));
        updateQuestion.setIcon(new ImageIcon(AddQuestionScreen.class.getResource("/icon/pen.png")));
        updateQuestion.setBounds(6, 213, 186, 59);
        sidePanel.add(updateQuestion);
        updateQuestion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new UpdateQuestionScreen().setVisible(true);
            }
        });

        // All Questions
        JLabel allQuestions = new JLabel("All Questions");
        allQuestions.setFont(new Font("Tahoma", Font.BOLD, 17));
        allQuestions.setIcon(new ImageIcon(AddQuestionScreen.class.getResource("/icon/select.png")));
        allQuestions.setBounds(6, 284, 171, 59);
        sidePanel.add(allQuestions);
        allQuestions.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new AllQuestionScreen().setVisible(true);
            }
        });
        
     // Logout
        JLabel logOut = new JLabel("Logout");
        logOut.setFont(new Font("Tahoma", Font.BOLD, 17));
        logOut.setIcon(new ImageIcon(AddQuestionScreen.class.getResource("/icon/logout.png")));
        logOut.setBounds(21, 507, 171, 59);
        sidePanel.add(logOut);
        logOut.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int confirm = JOptionPane.showConfirmDialog(null, "Do you want to Logout?", "Select", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    dispose();
                    new LoginPage().setVisible(true);
                }
            }
        });
        
        // View Report
        JLabel viewReport = new JLabel("View Report");
        viewReport.setFont(new Font("Tahoma", Font.BOLD, 17));
        viewReport.setIcon(new ImageIcon(AddQuestionScreen.class.getResource("/icon/report.png")));
        viewReport.setBounds(6, 355, 171, 59);
        sidePanel.add(viewReport);
        viewReport.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                dispose();
                new ViewReportScreen().setVisible(true);
            }
        });

        JPanel panel = new JPanel();
        panel.setBorder(null);
        panel.setBackground(Color.LIGHT_GRAY);
        panel.setBounds(200, 0, 800, 572);
        contentPane.add(panel);
        panel.setLayout(null);
        
        JLabel lblNewLabel_2 = new JLabel("Add Question");
        lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel_2.setBounds(16, 16, 180, 39);
        panel.add(lblNewLabel_2);
        
        JLabel lblNewLabel_2_1 = new JLabel("Question");
        lblNewLabel_2_1.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel_2_1.setBounds(16, 79, 99, 39);
        panel.add(lblNewLabel_2_1);
        
        questionField = new JTextField();
        questionField.setBounds(140, 82, 542, 32);
        panel.add(questionField);
        questionField.setColumns(10);
        
        JLabel lblNewLabel_2_1_1 = new JLabel("Option 1");
        lblNewLabel_2_1_1.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel_2_1_1.setBounds(16, 145, 99, 39);
        panel.add(lblNewLabel_2_1_1);
        
        JLabel lblNewLabel_2_1_2 = new JLabel("Option 2");
        lblNewLabel_2_1_2.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel_2_1_2.setBounds(16, 196, 99, 39);
        panel.add(lblNewLabel_2_1_2);
        
        JLabel lblNewLabel_2_1_3 = new JLabel("Option 3");
        lblNewLabel_2_1_3.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel_2_1_3.setBounds(16, 247, 99, 39);
        panel.add(lblNewLabel_2_1_3);
        
        JLabel lblNewLabel_2_1_4 = new JLabel("Option 4");
        lblNewLabel_2_1_4.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel_2_1_4.setBounds(16, 298, 99, 39);
        panel.add(lblNewLabel_2_1_4);
        
        JLabel lblNewLabel_2_1_5 = new JLabel("Answer");
        lblNewLabel_2_1_5.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel_2_1_5.setBounds(16, 349, 99, 39);
        panel.add(lblNewLabel_2_1_5);
        
        option1Field = new JTextField();
        option1Field.setColumns(10);
        option1Field.setBounds(140, 145, 542, 32);
        panel.add(option1Field);
        
        option2Field = new JTextField();
        option2Field.setColumns(10);
        option2Field.setBounds(140, 205, 542, 32);
        panel.add(option2Field);
        
        option3Field = new JTextField();
        option3Field.setColumns(10);
        option3Field.setBounds(140, 256, 542, 32);
        panel.add(option3Field);
        
        option4Field = new JTextField();
        option4Field.setColumns(10);
        option4Field.setBounds(140, 307, 542, 32);
        panel.add(option4Field);
        
        answerField = new JTextField();
        answerField.setColumns(10);
        answerField.setBounds(140, 355, 542, 32);
        panel.add(answerField);
        
        JLabel lblNewLabel_2_1_5_1 = new JLabel("Difficulty");
        lblNewLabel_2_1_5_1.setFont(new Font("Tahoma", Font.BOLD, 20));
        lblNewLabel_2_1_5_1.setBounds(16, 413, 99, 39);
        panel.add(lblNewLabel_2_1_5_1);
        
        // ComboBox for Difficulty Levels
        difficultyComboBox = new JComboBox<>();
        difficultyComboBox.setModel(new DefaultComboBoxModel<>(new String[] { "Beginner", "Intermediate", "Advanced" }));
        difficultyComboBox.setBounds(140, 423, 237, 27);
        panel.add(difficultyComboBox);
        
        JButton btnClear = new JButton("Clear");
        btnClear.setBounds(351, 496, 117, 46);
        panel.add(btnClear);
        btnClear.addActionListener(e -> {
            // Clear all text fields
            questionField.setText("");
            option1Field.setText("");
            option2Field.setText("");
            option3Field.setText("");
            option4Field.setText("");
            answerField.setText("");
        });
        
        JButton btnAdd = new JButton("Add");
        btnAdd.setBounds(146, 496, 117, 46);
        panel.add(btnAdd);
        btnAdd.addActionListener(e -> saveQuestion());

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setBounds(549, 496, 117, 46);
        panel.add(btnCancel);
        btnCancel.addActionListener(e -> {
            // Redirect to Admin Panel
            dispose();
            new AdminPanel().setVisible(true);
        });

        setVisible(true);
    }

    private void saveQuestion() {
        String question = questionField.getText();
        String option1 = option1Field.getText();
        String option2 = option2Field.getText();
        String option3 = option3Field.getText();
        String option4 = option4Field.getText();
        String answer = answerField.getText();
        String difficulty = (String) difficultyComboBox.getSelectedItem();

        if (question.isEmpty() || option1.isEmpty() || option2.isEmpty() || option3.isEmpty() || option4.isEmpty() || answer.isEmpty() || difficulty.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            // Establish database connection
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/QuizApplication", "root", ""); // Updated DB credentials
            
            // Prepare the SQL statement with correct column names
            PreparedStatement stmt = connection.prepareStatement(
                "INSERT INTO questions (question_text, option1, option2, option3, option4, correct_option, difficulty) VALUES (?, ?, ?, ?, ?, ?, ?)"
            );
            
            // Set parameters
            stmt.setString(1, question); // Updated variable name
            stmt.setString(2, option1);
            stmt.setString(3, option2);
            stmt.setString(4, option3);
            stmt.setString(5, option4);
            stmt.setString(6, answer); // Updated variable name
            stmt.setString(7, difficulty);
            
            // Execute the query
            stmt.executeUpdate();
            
            // Show success message
            JOptionPane.showMessageDialog(this, "Question added successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            
            // Clear input fields
            clearFields();
            
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error adding question to the database.", "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }


    }

    private void clearFields() {
        questionField.setText("");
        option1Field.setText("");
        option2Field.setText("");
        option3Field.setText("");
        option4Field.setText("");
        answerField.setText("");
        difficultyComboBox.setSelectedIndex(0);

    }
}
