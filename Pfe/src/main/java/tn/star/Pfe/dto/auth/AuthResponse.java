package tn.star.Pfe.dto.auth;

public record AuthResponse(String accessToken, String email, String role) {}