package com.pz.NNServer.exceptions;

public class NNBuilderException extends RuntimeException{
    public NNBuilderException(String exMessage, Exception exception) {
        super(exMessage, exception);
    }

    public NNBuilderException(String exMessage) {
        super(exMessage);
    }
}
