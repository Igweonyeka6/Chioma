package com.example.chiomablog.services;


import com.example.chiomablog.payload.ApiResponse;
import com.example.chiomablog.payload.UserRequestDto;
import com.example.chiomablog.payload.UserResponseDto;
import org.springframework.http.ResponseEntity;

public interface UserService {
    ResponseEntity<UserResponseDto> signup(UserRequestDto userRequestDto);

    ApiResponse login(UserRequestDto userRequestDto);
}
