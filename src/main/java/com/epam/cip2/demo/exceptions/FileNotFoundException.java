package com.epam.cip2.demo.exceptions;

public class FileNotFoundException extends RuntimeException {
    public FileNotFoundException() {
    }

    /**
     * Create a new FileNotFoundException
     *
     * @param message the detail message
     */
    public FileNotFoundException(String message) {
        super(message);
    }

    /**
     * Create a new FileNotFoundException
     *
     * @param cause the {@link Throwable} cause of exception
     */
    public FileNotFoundException(Throwable cause) {
        super(cause);
    }

    /**
     * Create a new FileNotFoundException
     *
     * @param message the detail message
     * @param cause   the {@link Throwable} cause of exception
     */
    public FileNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
