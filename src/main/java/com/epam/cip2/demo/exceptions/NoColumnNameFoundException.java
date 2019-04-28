package com.epam.cip2.demo.exceptions;

public class NoColumnNameFoundException extends RuntimeException {
    public NoColumnNameFoundException() {
    }

    /**
     * Create a new NoColumnNameFoundException
     *
     * @param message the detail message
     */
    public NoColumnNameFoundException(String message) {
        super(message);
    }

    /**
     * Create a new NoColumnNameFoundException
     *
     * @param cause the {@link Throwable} cause of exception
     */
    public NoColumnNameFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * Create a new NoColumnNameFoundException
     *
     * @param message the detail message
     * @param cause   the {@link Throwable} cause of exception
     */
    public NoColumnNameFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
