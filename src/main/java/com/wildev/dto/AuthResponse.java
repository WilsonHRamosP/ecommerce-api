package com.wildev.dto;

public record AuthResponse(
        String accessToken,
        String tokenType
) {
}