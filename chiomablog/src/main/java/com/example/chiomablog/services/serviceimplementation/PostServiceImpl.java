package com.example.chiomablog.services.serviceimplementation;

import com.example.chiomablog.entities.Post;
import com.example.chiomablog.entities.User;
import com.example.chiomablog.enums.Categories;
import com.example.chiomablog.enums.Role;
import com.example.chiomablog.exceptions.InvalidPostException;
import com.example.chiomablog.exceptions.PostNotAllowedException;
import com.example.chiomablog.exceptions.illegalPostException;
import com.example.chiomablog.payload.ApiResponse;
import com.example.chiomablog.payload.PostRequestDto;
import com.example.chiomablog.payload.PostResponseDto;
import com.example.chiomablog.repositories.CommentRepository;
import com.example.chiomablog.repositories.PostRepository;
import com.example.chiomablog.repositories.UserRepository;
import com.example.chiomablog.services.PostServices;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;


@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostServices {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;

    @Override
    public ApiResponse createPost(PostRequestDto postRequestDto) {
        if (postRequestDto.role() == Role.ADMIN) {
            User user = userRepository.findByRole(postRequestDto.role()).orElseThrow(
                    () -> new illegalPostException("No Admin assigned yet")
            );
            Post post = Post.builder()
                    .postTitle(postRequestDto.postTitle())
                    .description(postRequestDto.description())
                    .categories(postRequestDto.category())
                    .user(user)
                    .imageUrl(postRequestDto.imageUrl())
                    .build();
            postRepository.save(post);
            PostResponseDto postResponseDto = PostResponseDto.builder()
                    .postTitle(post.getPostTitle())
                    .description(post.getDescription())
                    .build();
            return new ApiResponse("Post created", postResponseDto, HttpStatus.CREATED);
        } else {
            throw new PostNotAllowedException("Customer can't create posts");
        }
    }

    @Override
    public ApiResponse searchPost(String postTitle) {
        Post post = postRepository.findContainingPostTitle(postTitle).orElseThrow(()-> new InvalidPostException("Post not found"));
        if (post.getPostTitle().equals(postTitle)){
        PostResponseDto postResponseDto = PostResponseDto.builder()
                .postTitle(post.getPostTitle())
                .description(post.getDescription())
                .comment(post.getComment())
                .build();
            return new ApiResponse("Post found", postResponseDto, HttpStatus.ACCEPTED);
        }
        return new ApiResponse("Check postTitle", HttpStatus.BAD_REQUEST);
    }
}