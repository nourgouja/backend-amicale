package tn.star.Pfe.mapper;

import org.springframework.stereotype.Component;
import tn.star.Pfe.dto.inscription.InscriptionResponse;
import tn.star.Pfe.entity.Inscription;


@Component
public class InscriptionMapper {

    public InscriptionResponse toResponse(Inscription i) {
        return InscriptionResponse.builder()
                .id(i.getId())
                .offreId(i.getOffre().getId())
                .offreTitre(i.getOffre().getTitre())
                .adherentId(i.getAdherent().getId())
                .mailAdherent(i.getAdherent().getEmail())
                .statut(i.getStatut())
                .montant(i.getMontant())
                .statutPaiement(i.getStatutPaiement())
                .dateInscription(i.getDateInscription())
                .dateAnnulation(i.getDateAnnulation())
                .commentaire(i.getCommentaire())
                .build();
    }
}