package com.epam.cip2.demo.controller;

import com.epam.cip2.demo.exceptions.FileNotFoundException;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static com.epam.cip2.demo.constant.MessageConstants.MAX_SIZE_FILE_LIMIT;

@RestControllerAdvice
public class DefaultExceptionHandler {

    /**
     * Server exception handler
     *
     * @param e Exception
     * @return 400, "Bad request"
     */
    @ExceptionHandler({FileNotFoundException.class,
            IllegalArgumentException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String onServerException(Exception e) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ExceptionUtils.printRootCauseStackTrace(e, new PrintStream(out));
        return e.getMessage();
    }

    /**
     * Server exception handler
     *
     * @param e Exception
     * @return 407, "Not acceptable"
     */
    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public String maxFileSizeLimitExceeded(IllegalStateException e) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ExceptionUtils.printRootCauseStackTrace(e, new PrintStream(out));
        return MAX_SIZE_FILE_LIMIT;
    }
}