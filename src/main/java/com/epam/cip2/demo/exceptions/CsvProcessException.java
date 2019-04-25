package com.epam.cip2.demo.exceptions;

public class CsvProcessException extends RuntimeException {
    public CsvProcessException() {
    }

    /**
     * Create a new CsvProcessException
     *
     * @param message the detail message
     */
    public CsvProcessException(String message) {
        super(message);
    }

    /**
     * Create a new CsvProcessException
     *
     * @param cause the {@link Throwable} cause of exception
     */
    public CsvProcessException(Throwable cause) {
        super(cause);
    }

    /**
     * Create a new CsvProcessException
     *
     * @param message the detail message
     * @param cause   the {@link Throwable} cause of exception
     */
    public CsvProcessException(String message, Throwable cause) {
        super(message, cause);
    }
}
