package tn.star.Pfe.dto.auth;

import jakarta.validation.constraints.*;
import tn.star.Pfe.enums.Role;


public record CreateUtilisateurRequest(@NotBlank @Email String email, @NotBlank  String motDePasse, @NotBlank String nom, @NotBlank  String prenom, @NotNull Role role, String poste){
}