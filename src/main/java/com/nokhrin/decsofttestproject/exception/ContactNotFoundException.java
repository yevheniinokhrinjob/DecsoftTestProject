package com.nokhrin.decsofttestproject.exception;

public class ContactNotFoundException extends RuntimeException {
    public ContactNotFoundException(String message){
        super(message);
    }
}
