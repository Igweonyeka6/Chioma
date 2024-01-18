package com.example.chiomablog.payload;

import com.example.chiomablog.entities.Comment;
import lombok.Builder;

import java.io.Serializable;
import java.util.List;

@Builder
public record PostResponseDto(String postTitle, String description, List<Comment> comment)  {
}