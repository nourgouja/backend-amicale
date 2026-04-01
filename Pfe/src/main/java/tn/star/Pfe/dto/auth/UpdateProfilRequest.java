package tn.star.Pfe.dto.auth;


public record UpdateProfilRequest(
        String nom,
        String prenom,
        String motDePasse
) {}