package com.epam.cip2.demo.controller;

import com.epam.cip2.demo.exceptions.FileNotFoundException;
import com.epam.cip2.demo.exceptions.NoColumnNameFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import static com.epam.cip2.demo.constant.MessageConstants.*;

@RestControllerAdvice
public class DefaultExceptionHandler {

    /**
     * Server exception handler
     *
     * @param e Exception
     * @return 400, "Bad request"
     */
    @ExceptionHandler({FileNotFoundException.class,
            IllegalArgumentException.class,
            NoColumnNameFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView onServerException(Exception e) {
        ModelAndView model = new ModelAndView(INDEX_MODEL);
        model.addObject(ERROR_ATTR, e.getMessage());
        return model;
    }

    /**
     * Server exception handler
     *
     * @param e Exception
     * @return 400, "Bad request. Empty csv file uploaded."
     */
    @ExceptionHandler(IndexOutOfBoundsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView emptyCsvFileUploaded(Exception e) {
        ModelAndView model = new ModelAndView(INDEX_MODEL);
        model.addObject(ERROR_ATTR, EMPTY_CSV_FILE_UPLOADED);
        return model;
    }

    /**
     * Server exception handler
     *
     * @param e Exception
     * @return 407, "Not acceptable. File is over the size limit."
     */
    @ExceptionHandler(IllegalStateException.class)
    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    public ModelAndView maxFileSizeLimitExceeded(IllegalStateException e) {
        ModelAndView model = new ModelAndView(INDEX_MODEL);
        model.addObject(ERROR_ATTR, MAX_SIZE_FILE_LIMIT);
        return model;
    }
}