package com.example.chiomablog.payload;


import com.example.chiomablog.entities.Post;
import com.example.chiomablog.entities.User;

public record CommentRequestDto(String comments, User user, Post post, String username) {
}