package tn.star.Pfe.dto.auth;

public record AuthResponse(String accessToken, String refreshToken, String email, String role){
}