package com.pz.NNServer.exceptions;

import org.springframework.mail.MailException;

public class SpringNNBuilderMailException extends RuntimeException {
    public SpringNNBuilderMailException(String exceptionMessage, MailException e) {
        super(exceptionMessage,e);
    }
    public SpringNNBuilderMailException(String exceptionMessage){
        super(exceptionMessage);
    }
}
