package org.personal.quizapplication.gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import org.personal.quizapplication.services.AuthenticationService;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * The {@code RegisterPage} class represents the registration page for the quiz application.
 * It allows users to input their username, password, confirm password, and select a role
 * (Admin or Player) for registration. It validates the user inputs and attempts to register
 * the user by calling the {@link AuthenticationService#registerUser(String, String, String)} method.
 */
public class RegisterPage extends JFrame {
    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JComboBox<String> comboBox;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;

    /**
     * Constructs a new {@code RegisterPage} and initializes the UI components for user registration.
     * It sets up the layout, labels, buttons, and event listeners.
     */
    public RegisterPage() {
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
        btnBack.setBounds(-16, 6, 107, 40);
        contentPane.add(btnBack);

        btnBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new LoginPage(); // Open the main window
                dispose(); // Close this window
            }
        });

        JLabel lblNewLabel = new JLabel("Welcome to the");
        lblNewLabel.setForeground(new Color(255, 255, 255));
        lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 35));
        lblNewLabel.setBounds(95, 205, 334, 79);
        contentPane.add(lblNewLabel);

        JLabel lblNewLabel_1 = new JLabel("Quizzies!");
        lblNewLabel_1.setForeground(new Color(255, 255, 255));
        lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 39));
        lblNewLabel_1.setBounds(95, 251, 378, 79);
        contentPane.add(lblNewLabel_1);

        JLabel lblNewLabel_2 = new JLabel("Quizzies – Play. Learn. Conquer!");
        lblNewLabel_2.setBackground(new Color(182, 200, 223));
        lblNewLabel_2.setForeground(new Color(182, 200, 223));
        lblNewLabel_2.setFont(new Font("SansSerif", Font.ITALIC, 13));
        lblNewLabel_2.setBounds(95, 327, 258, 16);
        contentPane.add(lblNewLabel_2);

        JLabel lblNewLabel_3 = new JLabel("Register");
        lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 25));
        lblNewLabel_3.setForeground(Color.WHITE);
        lblNewLabel_3.setBounds(570, 80, 140, 36);
        contentPane.add(lblNewLabel_3);

        JPanel panel = new JPanel();
        panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(59, 78, 103), new Color(59, 78, 103)));
        panel.setBackground(new Color(59, 78, 103));
        panel.setBounds(570, 131, 297, 344);
        contentPane.add(panel);
        panel.setLayout(null);

        JLabel lblNewLabel_4 = new JLabel("Username");
        lblNewLabel_4.setForeground(Color.WHITE);
        lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_4.setBounds(22, 27, 82, 25);
        panel.add(lblNewLabel_4);

        usernameField = new JTextField("Username");
        usernameField.setForeground(Color.LIGHT_GRAY);
        usernameField.setFont(new Font("SansSerif", Font.PLAIN, 13));
        usernameField.setColumns(10);
        usernameField.setBackground(Color.WHITE);
        usernameField.setBounds(22, 64, 220, 25);
        panel.add(usernameField);

        // Add FocusListener to handle placeholder behavior
        usernameField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (usernameField.getText().equals("Username")) {
                    usernameField.setText("");
                    usernameField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (usernameField.getText().isEmpty()) {
                    usernameField.setForeground(Color.LIGHT_GRAY);
                    usernameField.setText("Username");
                }
            }
        });

        JLabel lblNewLabel_4_1 = new JLabel("Password");
        lblNewLabel_4_1.setForeground(Color.WHITE);
        lblNewLabel_4_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_4_1.setBounds(22, 101, 82, 25);
        panel.add(lblNewLabel_4_1);

        passwordField = new JPasswordField("Password");
        passwordField.setForeground(Color.LIGHT_GRAY);
        passwordField.setFont(new Font("SansSerif", Font.PLAIN, 13));
        passwordField.setColumns(10);
        passwordField.setBackground(Color.WHITE);
        passwordField.setBounds(22, 138, 220, 25);
        panel.add(passwordField);

        // Add FocusListener to handle placeholder behavior
        passwordField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (new String(passwordField.getPassword()).equals("Password")) {
                    passwordField.setText("");
                    passwordField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (passwordField.getPassword().length == 0) {
                    passwordField.setForeground(Color.LIGHT_GRAY);
                    passwordField.setText("Password");
                }
            }
        });

        JLabel lblNewLabel_4_1_1 = new JLabel("Confirm Password");
        lblNewLabel_4_1_1.setForeground(Color.WHITE);
        lblNewLabel_4_1_1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblNewLabel_4_1_1.setBounds(22, 175, 184, 25);
        panel.add(lblNewLabel_4_1_1);

        confirmPasswordField = new JPasswordField("Confirm Password");
        confirmPasswordField.setForeground(Color.LIGHT_GRAY);
        confirmPasswordField.setFont(new Font("SansSerif", Font.PLAIN, 13));
        confirmPasswordField.setColumns(10);
        confirmPasswordField.setBackground(Color.WHITE);
        confirmPasswordField.setBounds(22, 207, 220, 25);
        panel.add(confirmPasswordField);

        // Add FocusListener to handle placeholder behavior
        confirmPasswordField.addFocusListener(new FocusListener() {
            public void focusGained(FocusEvent e) {
                if (new String(confirmPasswordField.getPassword()).equals("Confirm Password")) {
                    confirmPasswordField.setText("");
                    confirmPasswordField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(FocusEvent e) {
                if (confirmPasswordField.getPassword().length == 0) {
                    confirmPasswordField.setForeground(Color.LIGHT_GRAY);
                    confirmPasswordField.setText("Confirm Password");
                }
            }
        });

        JLabel lblRole = new JLabel("Role");
        lblRole.setForeground(Color.WHITE);
        lblRole.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblRole.setBounds(22, 256, 52, 20);
        panel.add(lblRole);

        comboBox = new JComboBox<>(new String[]{"Select", "Admin", "Player"});
        comboBox.setBounds(74, 244, 168, 49);
        comboBox.setForeground(Color.BLACK);
        comboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(comboBox);

        JButton btnRegister = new JButton("Register");
        btnRegister.setFont(new Font("SansSerif", Font.PLAIN, 13));
        btnRegister.setBackground(new Color(62, 78, 100));
        btnRegister.setForeground(new Color(62, 78, 100));
        btnRegister.setBounds(22, 294, 117, 29);
        panel.add(btnRegister);

        JLabel lblNewLabel_7 = new JLabel("");
        lblNewLabel_7.setEnabled(false);
        lblNewLabel_7.setBackground(new Color(0, 0, 0));
        lblNewLabel_7.setIcon(new ImageIcon(LoginPage.class.getResource("/icon/QuestionMarks.png")));
        lblNewLabel_7.setBounds(82, 46, 391, 238);
        contentPane.add(lblNewLabel_7);

        setVisible(true);

        // Register Button ActionListener
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                String confirmPassword = new String(confirmPasswordField.getPassword());
                String role = (String) comboBox.getSelectedItem();

                handleRegistration(username, password, confirmPassword, role);
            }
        });
    }

    /**
     * Handles the registration process by validating the user inputs and calling the
     * {@link AuthenticationService#registerUser(String, String, String)} method.
     *
     * @param username The user's chosen username.
     * @param password The user's chosen password.
     * @param confirmPassword The user's confirmation of the password.
     * @param role The selected role of the user (either "Admin" or "Player").
     */
    private void handleRegistration(String username, String password, String confirmPassword, String role) {
        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || role.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please fill in all fields.");
        } else if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, "Passwords do not match.");
        } else {
            boolean isRegistered = AuthenticationService.registerUser(username, password, role);
            if (isRegistered) {
                JOptionPane.showMessageDialog(this, "Registration successful!");
                new LoginPage(); // Redirect to login page after registration
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Registration failed. Try again.");
            }
        }
    }
}
