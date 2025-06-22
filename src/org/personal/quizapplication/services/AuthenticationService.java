package org.personal.quizapplication.services;


import static org.personal.quizapplication.connection.ConnectionFactory.getConnection;
import java.sql.*;

import org.personal.quizapplication.model.User;

public class AuthenticationService {
	public static boolean registerUser(String username, String password, String role) {
        String sql = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            pstmt.setString(2, hashPassword(password)); // In production, use proper password hashing
            pstmt.setString(3, role.toUpperCase());
            
            int rowsAffected = pstmt.executeUpdate();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static User authenticateUser(String username, String password) {
        String sql = "SELECT * FROM users WHERE username = ? AND password = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            
            pstmt.setString(1, username);
            pstmt.setString(2, hashPassword(password)); // In production, use proper password verification
            
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
            	System.out.println(rs.getString("role"));
                return new User(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("role")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String hashPassword(String password) {
        // In production, use a proper password hashing algorithm like BCrypt
        // This is just for demonstration
        return password;
    }
}

