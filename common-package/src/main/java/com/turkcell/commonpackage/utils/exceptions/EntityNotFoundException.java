package com.turkcell.commonpackage.utils.exceptions;

public class EntityNotFoundException extends RuntimeException{
   public EntityNotFoundException(String message){
       super(message);
   }
}
