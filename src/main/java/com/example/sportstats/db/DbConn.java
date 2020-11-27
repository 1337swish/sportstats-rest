package com.example.sportstats.db;

import org.javalite.activejdbc.Base;

/**
 * Database connector class for a mySQL database. Uses the Singleton pattern for
 * maximum of one connector at a time.
 */
public class DbConn {

    private static final String DB_NAME = "sportstats";
    private static final String USER = "root";
    private static final String PASSWORD = "cdgg55ur82a";
    private static final String CONNECTION_URL = "jdbc:mysql://localhost:3306/" + DB_NAME + "?serverTimezone=UTC";

    private static DbConn instance = null;

    private DbConn() {
    }

    /**
     * Gets an instance of the database connector.
     *
     * @return the database connector.
     */
    public static DbConn getInstance() {
        if (instance == null) {
            instance = new DbConn();
        }
        return instance;
    }

    /**
     * Opens a connection to the database.
     */
    public void open() {

        Base.open("com.mysql.cj.jdbc.Driver", CONNECTION_URL, USER, PASSWORD);
    }

    /**
     * Closes the connection to the database.
     */
    public void close() {

        Base.close();
    }

}
