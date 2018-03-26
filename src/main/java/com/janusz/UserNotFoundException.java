package com.janusz;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(Long userId){
        super("could not find person with id "+userId+".");
    }
}
