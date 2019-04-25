package com.epam.cip2.demo.controller;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;

@RestControllerAdvice
public class DefaultExceptionHandler {

    /**
     * Server exception handler
     *
     * @param e Exception
     * @return 404, "File not found"
     */
    @ExceptionHandler(FileNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String onServerException(Exception e) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ExceptionUtils.printRootCauseStackTrace(e, new PrintStream(out));
        return e.getMessage();
    }
}