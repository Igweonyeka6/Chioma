package com.example.chiomablog.controller;

import com.example.chiomablog.payload.ApiResponse;
import com.example.chiomablog.payload.CommentRequestDto;
import com.example.chiomablog.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/")
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/{id}/comment")
    public ApiResponse createComment(@RequestBody CommentRequestDto commentRequestDto, String username, @Param("postId"+"username") Long postId){
    return commentService.createComment(commentRequestDto,username ,postId);
    }
}
