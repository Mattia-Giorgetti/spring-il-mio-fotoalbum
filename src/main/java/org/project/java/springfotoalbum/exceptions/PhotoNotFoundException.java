package org.project.java.springfotoalbum.exceptions;

public class PhotoNotFoundException extends RuntimeException {

    public PhotoNotFoundException(String message){
        super(message);
    }
}
