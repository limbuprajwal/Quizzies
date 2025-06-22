package org.personal.quizapplication.gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import org.personal.quizapplication.model.User;
import org.personal.quizapplication.services.AuthenticationService;

/**
 * The LoginPage class represents the GUI screen where users can log into the quiz application.
 * It allows users to enter their credentials (username, password) and select their role (Admin or Player).
 * Depending on the credentials, the user will be redirected to either the admin or player dashboard.
 * 
 * The class handles user input validation and authenticates the user using the AuthenticationService.
 */
public class LoginPage extends JFrame {

    private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> comboBox;

    /**
     * Constructs the LoginPage and sets up the layout, components, and event listeners.
     */
    public LoginPage() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 600);
        contentPane = new JPanel();
        contentPane.setBackground(new Color(48, 60, 81));
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

        setContentPane(contentPane);
        contentPane.setLayout(null);

        // Welcome Labels
        JLabel lblTitle = new JLabel("Welcome to the");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Tahoma", Font.PLAIN, 35));
        lblTitle.setBounds(95, 205, 334, 79);
        contentPane.add(lblTitle);

        JLabel lblSubTitle = new JLabel("Quizzies!");
        lblSubTitle.setForeground(Color.WHITE);
        lblSubTitle.setFont(new Font("Tahoma", Font.BOLD, 39));
        lblSubTitle.setBounds(95, 251, 378, 79);
        contentPane.add(lblSubTitle);

        JLabel lblTagline = new JLabel("Quizzies – Play. Learn. Conquer!");
        lblTagline.setForeground(new Color(182, 200, 223));
        lblTagline.setFont(new Font("SansSerif", Font.ITALIC, 13));
        lblTagline.setBounds(95, 327, 258, 16);
        contentPane.add(lblTagline);

        // Login Title
        JLabel lblLoginTitle = new JLabel("Log in");
        lblLoginTitle.setFont(new Font("Tahoma", Font.PLAIN, 25));
        lblLoginTitle.setForeground(Color.WHITE);
        lblLoginTitle.setBounds(570, 80, 140, 36);
        contentPane.add(lblLoginTitle);

        // Login Panel
        JPanel panel = new JPanel();
        panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(59, 78, 103), new Color(59, 78, 103)));
        panel.setBackground(new Color(59, 78, 103));
        panel.setBounds(570, 131, 297, 320);
        contentPane.add(panel);
        panel.setLayout(null);

        // Username Label and Text Field
        JLabel lblUsername = new JLabel("Username");
        lblUsername.setForeground(Color.WHITE);
        lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblUsername.setBounds(22, 27, 82, 25);
        panel.add(lblUsername);

        usernameField = new JTextField("Username");
        usernameField.setForeground(Color.GRAY);
        usernameField.setFont(new Font("SansSerif", Font.PLAIN, 13));
        usernameField.setBounds(22, 64, 220, 25);
        panel.add(usernameField);
        usernameField.setColumns(10);

        usernameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                if (usernameField.getText().equals("Username")) {
                    usernameField.setText("");
                    usernameField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (usernameField.getText().isEmpty()) {
                    usernameField.setForeground(Color.GRAY);
                    usernameField.setText("Username");
                }
            }
        });

        // Password Label and Password Field
        JLabel lblPassword = new JLabel("Password");
        lblPassword.setForeground(Color.WHITE);
        lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblPassword.setBounds(22, 101, 82, 25);
        panel.add(lblPassword);

        passwordField = new JPasswordField("Password");
        passwordField.setForeground(Color.GRAY);
        passwordField.setFont(new Font("SansSerif", Font.PLAIN, 13));
        passwordField.setBounds(22, 138, 220, 26);
        panel.add(passwordField);

        passwordField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                String passText = new String(passwordField.getPassword());
                if (passText.equals("Password")) {
                    passwordField.setText("");
                    passwordField.setEchoChar('•'); // Hide input as password
                    passwordField.setForeground(Color.BLACK);
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (passwordField.getPassword().length == 0) {
                    passwordField.setForeground(Color.GRAY);
                    passwordField.setText("Password");
                    passwordField.setEchoChar((char) 0); // Show text instead of dots
                }
            }
        });

        // Role Label and ComboBox
        JLabel lblRole = new JLabel("Role");
        lblRole.setForeground(Color.WHITE);
        lblRole.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblRole.setBounds(22, 188, 52, 20);
        panel.add(lblRole);

        comboBox = new JComboBox<>(new String[] { "Select", "Admin", "Player" });
        comboBox.setBounds(69, 188, 170, 25);
        comboBox.setForeground(Color.BLACK);
        comboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        panel.add(comboBox);

        // Login Button
        JButton loginButton = new JButton("Log in");
        loginButton.setBackground(Color.WHITE);
        loginButton.setForeground(Color.BLACK);
        loginButton.setBounds(22, 239, 117, 29);
        panel.add(loginButton);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String userType = (String) comboBox.getSelectedItem();

            // Check if any field is empty
            if (username.equals("Username") || password.equals("Password") || userType.equals("Select")) {
                JOptionPane.showMessageDialog(LoginPage.this, "Please fill in all fields!", "Login Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            handleLogin(username, password, userType);
        });

        // Register Link
        JLabel lblNoAccount = new JLabel("Don't have an account?");
        lblNoAccount.setForeground(Color.WHITE);
        lblNoAccount.setFont(new Font("SansSerif", Font.PLAIN, 13));
        lblNoAccount.setBounds(22, 280, 158, 16);
        panel.add(lblNoAccount);

        JLabel lblRegister = new JLabel("Register");
        lblRegister.setForeground(Color.WHITE);
        lblRegister.setBounds(179, 280, 61, 16);
        lblRegister.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        panel.add(lblRegister);

        lblRegister.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                new RegisterPage();
            }
        });

        // Icon
        JLabel lblIcon = new JLabel("");
        lblIcon.setEnabled(false);
        lblIcon.setIcon(new ImageIcon(LoginPage.class.getResource("/icon/QuestionMarks.png")));
        lblIcon.setBounds(82, 46, 391, 238);
        contentPane.add(lblIcon);
    }

    /**
     * Handles the login process by authenticating the user and redirecting them to the correct dashboard.
     * 
     * @param username The username entered by the user.
     * @param password The password entered by the user.
     * @param role The role selected by the user (Admin or Player).
     */
    private void handleLogin(String username, String password, String role) {
        User user = AuthenticationService.authenticateUser(username, password);

        if (user != null && user.getRole().equalsIgnoreCase(role)) {
            if (role.equalsIgnoreCase("Admin")) {
                openAdminDashboard(user);
            } else {
                openPlayerDashboard(user);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Invalid credentials or user type!", "Login Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Opens the Admin Dashboard when the user is successfully authenticated as an admin.
     * 
     * @param user The authenticated user object.
     */
    private void openAdminDashboard(User user) {
        new AdminPanel().setVisible(true);
        dispose();
    }

    /**
     * Opens the Player Dashboard when the user is successfully authenticated as a player.
     * 
     * @param user The authenticated user object.
     */
    private void openPlayerDashboard(User user) {
        new PlayerHomePage(user).setVisible(true);
        dispose();
    }

    /**
     * The main method to launch the LoginPage GUI.
     * 
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginPage frame = new LoginPage();
            frame.setVisible(true);
        });
    }
}
