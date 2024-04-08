package com.inrech.jobs.youtube.requestapi;

public class RetrieveYoutubeException extends RuntimeException{
    RetrieveYoutubeException(String msg){
        super("Retrieve Youtube error with error message " + msg);
    }
}
