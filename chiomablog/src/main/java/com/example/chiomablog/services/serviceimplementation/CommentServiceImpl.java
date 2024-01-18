package com.example.chiomablog.services.serviceimplementation;

import com.example.chiomablog.entities.Comment;
import com.example.chiomablog.entities.Post;
import com.example.chiomablog.entities.User;
import com.example.chiomablog.exceptions.InvalidPostException;
import com.example.chiomablog.payload.ApiResponse;
import com.example.chiomablog.payload.CommentRequestDto;
import com.example.chiomablog.payload.CommentResponseDto;
import com.example.chiomablog.repositories.CommentRepository;
import com.example.chiomablog.repositories.PostRepository;
import com.example.chiomablog.repositories.UserRepository;
import com.example.chiomablog.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    @Override
    public ApiResponse createComment(CommentRequestDto commentRequestDto, String username, Long postId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new InvalidPostException("Post doesn't exist"));
        User user1 = userRepository.findByUsername(username);
        Comment comment = Comment.builder()
                .comments(commentRequestDto.comments())
                .post(post)
                .user(user1)
                .build();
        commentRepository.save(comment);
        CommentResponseDto commentResponseDto = CommentResponseDto.builder()
                .id(comment.getId())
                .comments(commentRequestDto.comments())
                .build();
        return new ApiResponse("Comment successful",commentResponseDto);
    }
}
