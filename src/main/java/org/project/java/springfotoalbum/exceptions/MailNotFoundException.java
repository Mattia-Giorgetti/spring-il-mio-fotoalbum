package org.project.java.springfotoalbum.exceptions;

public class MailNotFoundException extends RuntimeException {

    public MailNotFoundException(String message){
        super(message);
    }
}
