package com.aluraChallenge.literatura.exceptions;

public class FetchAPIException extends RuntimeException {
    public FetchAPIException(String message) {
        super(message);
    }

    public FetchAPIException(String message, Throwable cause) {
        super(message, cause);
    }
}
