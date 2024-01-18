package com.example.chiomablog.controller;

import com.example.chiomablog.payload.ApiResponse;
import com.example.chiomablog.payload.PostRequestDto;
import com.example.chiomablog.services.PostServices;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class PostController {

    private final PostServices postServices;

@PostMapping("/post")
    public ApiResponse createPost(@RequestBody PostRequestDto postRequestDto){
    return postServices.createPost(postRequestDto);
    }

    @GetMapping("/searchPost")
    public ApiResponse searchPost(@Param("post_title") String postTitle){
    return postServices.searchPost(postTitle);
    }
}
