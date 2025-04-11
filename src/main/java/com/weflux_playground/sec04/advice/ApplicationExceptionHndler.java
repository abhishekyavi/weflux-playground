package com.weflux_playground.sec04.advice;

import java.net.URI;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.weflux_playground.sec04.exceptions.CustomerNotFoundExceptions;
import com.weflux_playground.sec04.exceptions.InvalidInpuntException;

@ControllerAdvice
public class ApplicationExceptionHndler {
    @ExceptionHandler(CustomerNotFoundExceptions.class)
    public ProblemDetail handleException(CustomerNotFoundExceptions ex) {
        var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
        problemDetail.setType(URI.create("http://example.com/customer-not-found"));
        problemDetail.setTitle("Customer Not Found");

        return problemDetail;

    }

    @ExceptionHandler(InvalidInpuntException.class)
    public ProblemDetail handleException(InvalidInpuntException ex) {
        var problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
        problemDetail.setType(URI.create("http://example.com/invalid-input"));
        problemDetail.setTitle("Inavlid Input");

        return problemDetail;

    }

}
