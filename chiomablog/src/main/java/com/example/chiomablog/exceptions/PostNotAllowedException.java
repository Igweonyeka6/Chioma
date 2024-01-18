package com.example.chiomablog.exceptions;

public class PostNotAllowedException extends RuntimeException {
    public PostNotAllowedException(String s) {
        super(s);
    }
}
