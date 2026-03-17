package tn.star.Pfe.dto.inscription;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.star.Pfe.enums.StatutInscription;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InscriptionResponse {

    private int id;
    private int offreId;
    private String offreTitre;
    private int adherentId;
    private String mailAdherent;
    private StatutInscription statut;
    private double montant;
    private LocalDateTime dateInscription;
    private LocalDateTime dateAnnulation;
    private String commentaire;


}
