package com.example.chiomablog.payload;


import com.example.chiomablog.enums.Role;

public record UserResponseDto(String username, String email, Role role){
}