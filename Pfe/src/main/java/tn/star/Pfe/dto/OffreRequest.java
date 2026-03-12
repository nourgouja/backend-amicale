//package tn.star.Pfe.dto;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import tn.star.Pfe.enums.TypeOffre;
//
//import java.time.LocalDate;
//
//@NoArgsConstructor
//public class OffreRequest {
//    @Data
//    public static class CreateOffreRequest{
//        private String titre;
//        private String description;
//        private TypeOffre typeOffre;
//        private LocalDate dateDebut;
//        private  LocalDate dateFin;
//        private int capaciteMax;
//        private double prixParPersonne;
//        private String lieu;
//        private String imageURL;
//    }
//
//    @Data
//    public static class UpdateOffreRequest{
//        private String titre;
//        private String description;
//        private TypeOffre typeOffre;
//        private LocalDate dateDebut;
//        private  LocalDate dateFin;
//        private int capaciteMax;
//        private double prixParPersonne;
//        private String lieu;
//        private String imageURL;
//    }
//}
package tn.star.Pfe.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import tn.star.Pfe.enums.TypeOffre;

import java.math.BigDecimal;
import java.time.LocalDate;

public class OffreRequest {

    @Data
    public static class CreateOffreRequest {

        @NotBlank(message = "Le titre est obligatoire")
        @Size(max = 200)
        private String titre;

        @NotBlank(message = "La description est obligatoire")
        private String description;

        @NotNull(message = "Le type est obligatoire")
        private TypeOffre typeOffre;

        @NotNull(message = "La date de début est obligatoire")
        private LocalDate dateDebut;

        @NotNull(message = "La date de fin est obligatoire")
        private LocalDate dateFin;

        @NotNull @Min(1)
        private int capaciteMax;

        @NotNull
        private double prixParPersonne;

        private String lieu;
        private String imageURL;
    }

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