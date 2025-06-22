package org.personal.quizapplication.model;

/**
 * Represents a user in the quiz application.
 * A user has an ID, username, and role.
 */
public class User {

    private int id;
    private String role;
    private String username;

    /**
     * Constructor to create a new User with specified details.
     * 
     * @param id the unique identifier for the user
     * @param username the username of the user
     * @param role the role of the user (e.g., "admin", "player")
     */
    public User(int id, String username, String role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    /**
     * Gets the unique identifier of the user.
     * 
     * @return the user's ID
     */
    public int getId() { 
        return id; 
    }

    /**
     * Gets the username of the user.
     * 
     * @return the username of the user
     */
    public String getUsername() { 
        return username; 
    }

    /**
     * Gets the role of the user (e.g., admin, player).
     * 
     * @return the role of the user
     */
    public String getRole() { 
        return role; 
    }
}
