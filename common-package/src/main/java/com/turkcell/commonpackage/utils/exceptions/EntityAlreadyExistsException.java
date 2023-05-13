package com.turkcell.commonpackage.utils.exceptions;

public class EntityAlreadyExistsException extends RuntimeException{
    public EntityAlreadyExistsException(String message){
        super(message);
    }
}
