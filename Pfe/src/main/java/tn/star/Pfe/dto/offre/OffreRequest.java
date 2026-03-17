package tn.star.Pfe.dto.offre;

import lombok.Data;
import tn.star.Pfe.enums.TypeOffre;

import java.time.LocalDate;

public class OffreRequest {


    @Data
    public static class UpdateOffreRequest {
        private String titre;
        private String description;
        private TypeOffre typeOffre;
        private LocalDate dateDebut;
        private LocalDate dateFin;
        private Integer capaciteMax;
        private double prixParPersonne;
        private String lieu;
    }
}