package com.example.demo.model;

public record AuthResponse(
        Integer id,
        String username,
        String email,
        String firstName,
        String lastName,
        String gender,
        String image,
        String accessToken,
        String refreshToken
) {
}
