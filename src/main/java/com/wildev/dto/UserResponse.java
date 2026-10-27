package com.wildev.dto;

import com.wildev.entity.Role;

public record UserResponse(
        Long id,
        String fullName,
        String email,
        Role role
) {
}