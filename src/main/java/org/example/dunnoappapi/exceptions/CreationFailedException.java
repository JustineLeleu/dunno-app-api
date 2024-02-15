package org.example.dunnoappapi.exceptions;

import java.util.List;

public class CreationFailedException extends RuntimeException{
    List<String> errorMessages;
    public CreationFailedException(String errorMessage){
        super(errorMessage);
    }

    public CreationFailedException(List<String> errorMessages){
        this.errorMessages = errorMessages;
    }
}
