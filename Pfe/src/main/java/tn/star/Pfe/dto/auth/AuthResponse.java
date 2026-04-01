package tn.star.Pfe.dto.auth;

import io.swagger.v3.oas.annotations.media.Schema;

public record AuthResponse(String accessToken, String refreshToken, String email, String role) {}