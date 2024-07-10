package com.airlines.lmpairlines.advices;

import com.airlines.lmpairlines.exceptions.NotFoundException;
import com.airlines.lmpairlines.exceptions.UsernameExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleNotFound(){

    }
    @ExceptionHandler(UsernameExistsException.class)
    @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
    public void handleUsernameExists(){

    }
}
