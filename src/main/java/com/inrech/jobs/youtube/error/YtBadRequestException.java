package com.inrech.jobs.youtube.error;

public class YtBadRequestException extends RuntimeException{
    public YtBadRequestException(String msg){
        super("Bad request with error: " + msg);
    }
}
