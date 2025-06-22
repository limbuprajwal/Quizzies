package org.personal.quizapplication.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import org.personal.quizapplication.dao.QuestionDAO;
import org.personal.quizapplication.dao.impl.QuestionDAOImpl;
import org.personal.quizapplication.model.Question;
import java.sql.SQLException;

public class UpdateQuestionScreen extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField questionField;
    private JTextField option1Field;
    private JTextField option2Field;
    private JTextField option3Field;
    private JTextField option4Field;
    private JTextField answerField;
    private JTextField questionIDField;
    private QuestionDAO questionDAO;
    private JComboBox<String> difficultyBox;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    UpdateQuestionScreen frame = new UpdateQuestionScreen();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public UpdateQuestionScreen() {
        try {
            this.questionDAO = new QuestionDAOImpl();
        } catch (SQLException e) {
            e.printStackTrace();
        }

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
        lblNewLabel.setBounds(-14, -16, 206, 118);
        lblNewLabel.setEnabled(false);
        sidePanel.add(lblNewLabel);
        lblNewLabel.setBackground(Color.BLACK);
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setIcon(new ImageIcon(ViewReportScreen.class.getResource("/icon/QuizziesLogo.png")));
        
     // Back Button with Arrow Icon
        JButton btnBack = new JButton("←");
        btnBack.setFont(new Font("SansSerif", Font.BOLD, 20));
        btnBack.setForeground(Color.BLACK);
        btnBack.setBackground(Color.BLACK);
        btnBack.setBounds(6, 6, 178, 84);
        contentPane.add(btnBack);

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminPanel(); // Open the main window
                dispose(); // Close this window
            }
        });

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

        questionIDField = new JTextField();
        questionIDField.setBounds(532, 24, 91, 32);
        panel.add(questionIDField);
        questionIDField.setColumns(10);

        JLabel lblNewLabel_2 = new JLabel("Update Question");
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

        JButton btnNewButton = new JButton("Clear");
        btnNewButton.setBounds(420, 496, 117, 46);
        panel.add(btnNewButton);

        JButton btnAdd = new JButton("Update");
        btnAdd.setBounds(140, 496, 117, 46);
        panel.add(btnAdd);

        difficultyBox = new JComboBox<>();
        difficultyBox.setBounds(140, 423, 237, 27);
        difficultyBox.setModel(new DefaultComboBoxModel<>(new String[] {"Select", "Beginner", "Intermediate", "Advanced"}));
        panel.add(difficultyBox);

        JButton btnCancel = new JButton("Cancel");
        btnCancel.setBounds(565, 496, 117, 46);
        panel.add(btnCancel);

        // Search icon to search for a question by ID
        JLabel lblNewLabel_3 = new JLabel("");
        lblNewLabel_3.setIcon(new ImageIcon(UpdateQuestionScreen.class.getResource("/icon/search.png")));
        lblNewLabel_3.setBounds(621, 16, 61, 46);
        panel.add(lblNewLabel_3);

        lblNewLabel_3.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                searchQuestion(); // Call searchQuestion() when the icon is clicked
            }
        });

        // Delete Button
        JButton btnDelete = new JButton("Delete");
        btnDelete.setBounds(281, 496, 117, 46);
        panel.add(btnDelete);

        // Action Listeners
        btnAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                updateQuestion();
            }
        });

        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                clearFields();
            }
        });

        btnCancel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
                new AdminPanel().setVisible(true);
            }
        });

        btnDelete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteQuestion();
            }
        });

        setVisible(true);
    }

    private void searchQuestion() {
        String questionId = questionIDField.getText();
        if (questionId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a Question ID", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Question question = questionDAO.getQuestionById(Integer.parseInt(questionId));
        if (question != null) {
            populateFieldsWithQuestion(question);
        } else {
            JOptionPane.showMessageDialog(this, "No question found with this ID", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void updateQuestion() {
        String questionId = questionIDField.getText();
        String questionText = questionField.getText();
        String option1 = option1Field.getText();
        String option2 = option2Field.getText();
        String option3 = option3Field.getText();
        String option4 = option4Field.getText();
        String answer = answerField.getText();
        String difficulty = (String) difficultyBox.getSelectedItem();

        if (questionId.isEmpty() || questionText.isEmpty() || option1.isEmpty() || option2.isEmpty() || option3.isEmpty() || option4.isEmpty() || answer.isEmpty() || difficulty.equals("Select")) {
            JOptionPane.showMessageDialog(this, "All fields are required", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Question updatedQuestion = new Question(Integer.parseInt(questionId), questionText, option1, option2, option3, option4, answer, difficulty);

        boolean success = questionDAO.updateQuestion(updatedQuestion);
        if (success) {
            JOptionPane.showMessageDialog(this, "Question updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            clearFields();  // Clear the text fields after a successful update
        } else {
            JOptionPane.showMessageDialog(this, "Failed to update question", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void deleteQuestion() {
        String questionId = questionIDField.getText();
        if (questionId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a Question ID", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            boolean success = questionDAO.deleteQuestion(Integer.parseInt(questionId));
            if (success) {
                JOptionPane.showMessageDialog(this, "Question deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                clearFields();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to delete question", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Database error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void clearFields() {
        questionIDField.setText("");
        questionField.setText("");
        option1Field.setText("");
        option2Field.setText("");
        option3Field.setText("");
        option4Field.setText("");
        answerField.setText("");
        difficultyBox.setSelectedIndex(0);
    }

    private void populateFieldsWithQuestion(Question question) {
        questionField.setText(question.getQuestionText());
        option1Field.setText(question.getOption1());
        option2Field.setText(question.getOption2());
        option3Field.setText(question.getOption3());
        option4Field.setText(question.getOption4());
        answerField.setText(question.getCorrectOption());
        difficultyBox.setSelectedItem(question.getDifficulty());
    }
}
