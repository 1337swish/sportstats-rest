package com.example.sportstats.service;

import org.springframework.http.HttpStatus;

/**
 * Exception class for sportstats services.
 *
 * @author Claes Andersson
 */
public class SportstatsServiceException extends RuntimeException {

    private HttpStatus httpStatusCode;

    public SportstatsServiceException(String message) {

        super(message);
    }

    public SportstatsServiceException(String message, Throwable cause) {

        super(message, cause);
    }

    public SportstatsServiceException(Throwable cause) {

        super(cause);
    }

    public SportstatsServiceException(String message, HttpStatus httpStatusCode) {

        super(message);
        this.httpStatusCode = httpStatusCode;
    }

    public HttpStatus getHttpStatusCode() {

        return httpStatusCode;
    }
}
