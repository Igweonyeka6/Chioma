package com.example.chiomablog.services;


import com.example.chiomablog.payload.ApiResponse;
import com.example.chiomablog.payload.CommentRequestDto;

public interface CommentService {
    ApiResponse createComment(CommentRequestDto commentRequestDto, String username , Long postId);
}
