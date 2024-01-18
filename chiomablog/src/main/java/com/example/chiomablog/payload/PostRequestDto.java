package com.example.chiomablog.payload;


import com.example.chiomablog.entities.User;
import com.example.chiomablog.enums.Categories;
import com.example.chiomablog.enums.Role;
public record PostRequestDto(String postTitle, String description, String imageUrl,
                             Categories category, Role role, User user) {
}