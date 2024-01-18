package com.example.chiomablog.exceptions;

public class InvalidPostException extends RuntimeException{
    public InvalidPostException(String s){
        super(s);
    }
}
