package org.wojo.earnings_app.exception;

public class ConnectionFailedException extends RuntimeException {
    public ConnectionFailedException(String message, Throwable cause) {
        super(message, cause);
    }
}
