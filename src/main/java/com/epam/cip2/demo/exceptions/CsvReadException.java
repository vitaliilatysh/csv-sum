package com.epam.cip2.demo.exceptions;

public class CsvReadException extends RuntimeException {
    public CsvReadException() {
    }

    /**
     * Create a new CsvReadException
     *
     * @param message the detail message
     */
    public CsvReadException(String message) {
        super(message);
    }

    /**
     * Create a new CsvReadException
     *
     * @param cause the {@link Throwable} cause of exception
     */
    public CsvReadException(Throwable cause) {
        super(cause);
    }

    /**
     * Create a new CsvReadException
     *
     * @param message the detail message
     * @param cause   the {@link Throwable} cause of exception
     */
    public CsvReadException(String message, Throwable cause) {
        super(message, cause);
    }
}
