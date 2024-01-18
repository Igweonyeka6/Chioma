package com.example.chiomablog.services;


import com.example.chiomablog.payload.ApiResponse;

public interface LikeService {
    ApiResponse likePost(String postTitle);

    ApiResponse likeComment(Long commentId);




}
