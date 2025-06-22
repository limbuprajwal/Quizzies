package org.personal.quizapplication.test;


import org.junit.jupiter.api.Test;
import org.personal.quizapplication.connection.ConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class ConnectionFactoryTest {

    /**
     * Test if a database connection is successfully established.
     */
    @Test
    public void testGetConnection_Success() {
        try (Connection connection = ConnectionFactory.getConnection()) {
            assertNotNull(connection, "Connection should not be null.");
            assertFalse(connection.isClosed(), "Connection should be open.");
        } catch (SQLException e) {
            fail("Exception should not occur: " + e.getMessage());
        }
    }

    /**
     * Test closing a valid database connection.
     */
    @Test
    public void testCloseConnection_Success() {
        try {
            Connection connection = ConnectionFactory.getConnection();
            assertNotNull(connection, "Connection should not be null.");

            ConnectionFactory.closeQuietly(connection);
            assertTrue(connection.isClosed(), "Connection should be closed.");
        } catch (SQLException e) {
            fail("Exception should not occur: " + e.getMessage());
        }
    }

    /**
     * Simulate a database connection failure by using an invalid database URL.
     */
    @Test
    public void testGetConnection_Failure() {
        try {
            System.setProperty("jdbc.url", "jdbc:mysql://invalid_url:3306/QuizApplication");
            Connection connection = ConnectionFactory.getConnection();
            fail("Connection should have failed, but it succeeded.");
        } catch (SQLException e) {
            assertNotNull(e.getMessage(), "SQLException should contain a message.");
        }
    }

    /**
     * Test database and table creation logic.
     */
    @Test
    public void testCreateDatabaseAndTables() {
        try {
            ConnectionFactory.main(new String[]{}); // Simulates database creation
            Connection connection = ConnectionFactory.getConnection();
            assertNotNull(connection, "Database connection should be established.");
            ConnectionFactory.closeQuietly(connection);
        } catch (SQLException e) {
            fail("Database creation test failed: " + e.getMessage());
        }
    }
}

