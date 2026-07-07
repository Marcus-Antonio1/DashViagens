package com.dashviagens.admin.dto;

public record LoginResponse(String token, long expiresInMinutes) {
}
