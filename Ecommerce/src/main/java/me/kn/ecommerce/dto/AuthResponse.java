package me.kn.ecommerce.dto;

public record AuthResponse(Long userId, String username, String role, String message) {
}