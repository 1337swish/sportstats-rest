package com.example.sportstats.service;

/**
 * Exception class for sportstats services.
 *
 * @author Claes Andersson
 */
public class SportstatsServiceException extends RuntimeException {

    public SportstatsServiceException(String message) {

        super(message);
    }

    public SportstatsServiceException(String message, Throwable cause) {

        super(message, cause);
    }

    public SportstatsServiceException(Throwable cause) {

        super(cause);
    }
}
