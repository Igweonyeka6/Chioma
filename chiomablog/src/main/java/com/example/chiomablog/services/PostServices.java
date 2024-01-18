package com.example.chiomablog.services;


import com.example.chiomablog.payload.ApiResponse;
import com.example.chiomablog.payload.PostRequestDto;

public interface PostServices {
    public ApiResponse createPost(PostRequestDto postRequestDto);

    ApiResponse searchPost(String postTitle);

}
