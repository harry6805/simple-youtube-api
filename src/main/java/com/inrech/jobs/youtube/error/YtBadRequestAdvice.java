package com.inrech.jobs.youtube.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class YtBadRequestAdvice {
    @ResponseBody
    @ExceptionHandler(YtBadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public YtBadRequestError badRequestHandler(YtBadRequestException ex){
        YtBadRequestError e = new YtBadRequestError();
        e.setStatus(400);
        e.setError(ex.getMessage());
        return e;
    }
}
