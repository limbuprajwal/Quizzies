package org.personal.quizapplication.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import org.personal.quizapplication.dao.QuestionDAO;
import org.personal.quizapplication.dao.impl.QuestionDAOImpl;
import org.personal.quizapplication.model.Question;
import java.util.List;

/**
 * The AllQuestionScreen class represents the GUI screen where the admin can view all questions in the quiz application.
 * This screen displays a table with all the questions stored in the database and provides navigation options for adding, 
 * updating, and viewing reports.
 * 
 * It also handles actions such as navigating to different screens and logging out.
 * The screen uses the QuestionDAO to fetch data from the database and displays the questions in a table format.
 */
public class AllQuestionScreen extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTable table;
    private QuestionDAO questionDAO;

    /**
     * The main method launches the AllQuestionScreen GUI.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    AllQuestionScreen frame = new AllQuestionScreen();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * Constructs the AllQuestionScreen and sets up the layout, components, and event listeners.
     */
    public AllQuestionScreen() {
        try {
            // Initialize DAO to interact with the database
            this.questionDAO = new QuestionDAOImpl();
        } catch (Exception e) {
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
        sidePanel.setBorder(new EmptyBorder(0, 0, 0, 0));
        sidePanel.setBounds(2, 0, 198, 572);
        contentPane.add(sidePanel);
        sidePanel.setLayout(null);

        // Side panel logo setup
        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setBounds(-14, -16, 206, 118);
        lblNewLabel.setEnabled(false);
        sidePanel.add(lblNewLabel);
        lblNewLabel.setBackground(Color.BLACK);
        lblNewLabel.setForeground(Color.BLACK);
        lblNewLabel.setIcon(new ImageIcon(AllQuestionScreen.class.getResource("/icon/QuizziesLogo.png")));
        
        // Back Button with Arrow Icon
        JButton btnBack = new JButton("←");
        btnBack.setFont(new Font("SansSerif", Font.BOLD, 20));
        btnBack.setForeground(Color.BLACK);
        btnBack.setBackground(Color.BLACK);
        btnBack.setBounds(6, 6, 178, 84);
        contentPane.add(btnBack);

        // Action for Back button
        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AdminPanel(); // Open the main window
                dispose(); // Close this window
            }
        });
        
        // Logout Label setup
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

        // Add Question setup
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

        // Update Question setup
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

        // All Questions setup
        JLabel allQuestions = new JLabel("All Questions");
        allQuestions.setFont(new Font("Tahoma", Font.BOLD, 17));
        allQuestions.setIcon(new ImageIcon(AddQuestionScreen.class.getResource("/icon/select.png")));
        allQuestions.setBounds(6, 284, 171, 59);
        sidePanel.add(allQuestions);

        // View Report setup
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

        // Scroll Pane setup
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(196, 0, 804, 572);
        contentPane.add(scrollPane);

        // Table setup
        table = new JTable();
        scrollPane.setViewportView(table);

        // Fetch and display all questions in the table
        displayQuestions();

        setVisible(true);
    }

    /**
     * Fetches all questions from the database using the QuestionDAO and displays them in the JTable.
     * If the database retrieval fails, an error message is shown to the user.
     */
    private void displayQuestions() {
        try {
            // Fetch all questions from the database by passing an empty string to search for all
            List<Question> questions = questionDAO.getAllQuestions(""); // Empty string for no filtering
            
            // Prepare the table model with the required columns
            DefaultTableModel model = new DefaultTableModel();
            model.addColumn("ID");
            model.addColumn("Question");
            model.addColumn("Option 1");
            model.addColumn("Option 2");
            model.addColumn("Option 3");
            model.addColumn("Option 4");
            model.addColumn("Answer");
            model.addColumn("Difficulty");

            // Add each question to the table model
            for (Question question : questions) {
                model.addRow(new Object[]{
                        question.getId(),
                        question.getQuestionText(),
                        question.getOption1(),
                        question.getOption2(),
                        question.getOption3(),
                        question.getOption4(),
                        question.getCorrectOption(),
                        question.getDifficulty()
                });
            }

            // Set the model to the table
            table.setModel(model);

            // Adjust column widths
            table.getColumnModel().getColumn(1).setPreferredWidth(400); // "Question" column
            table.getColumnModel().getColumn(0).setPreferredWidth(50);  // ID column width (optional)
            table.getColumnModel().getColumn(2).setPreferredWidth(145); // Option 1 column width (optional)
            table.getColumnModel().getColumn(3).setPreferredWidth(145); // Option 2 column width (optional)
            table.getColumnModel().getColumn(4).setPreferredWidth(145); // Option 3 column width (optional)
            table.getColumnModel().getColumn(5).setPreferredWidth(145); // Option 4 column width (optional)
            table.getColumnModel().getColumn(6).setPreferredWidth(100); // Answer column width (optional)
            table.getColumnModel().getColumn(7).setPreferredWidth(120); // Difficulty column width (optional)

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Failed to load questions", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
