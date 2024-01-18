package com.example.chiomablog.exceptions;

public class UserExistsException extends RuntimeException{
    public UserExistsException(String s){
        super(s);
    }
}
