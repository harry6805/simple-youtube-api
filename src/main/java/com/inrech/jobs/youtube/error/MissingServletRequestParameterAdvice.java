package com.inrech.jobs.youtube.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class MissingServletRequestParameterAdvice {
    @ResponseBody
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public YtBadRequestError badRequestHandler(MissingServletRequestParameterException ex){
        YtBadRequestError error = new YtBadRequestError();
        error.setError(ex.getMessage());
        error.setStatus(400);
        return error;
    }
}
