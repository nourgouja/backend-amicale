package tn.star.Pfe.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import tn.star.Pfe.dto.inscription.InscriptionResponse;
import tn.star.Pfe.entity.Inscription;
import tn.star.Pfe.entity.Offre;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-21T16:19:11+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.15 (Microsoft)"
)
@Component
public class InscriptionMapperImpl implements InscriptionMapper {

    @Override
    public InscriptionResponse toResponse(Inscription inscription) {
        if ( inscription == null ) {
            return null;
        }

        InscriptionResponse.InscriptionResponseBuilder inscriptionResponse = InscriptionResponse.builder();

        inscriptionResponse.offreId( inscriptionOffreId( inscription ) );
        inscriptionResponse.offreTitre( inscriptionOffreTitre( inscription ) );
        inscriptionResponse.id( inscription.getId() );
        inscriptionResponse.adherentId( inscription.getAdherentId() );
        inscriptionResponse.mailAdherent( inscription.getMailAdherent() );
        inscriptionResponse.statut( inscription.getStatut() );
        inscriptionResponse.montant( inscription.getMontant() );
        inscriptionResponse.dateInscription( inscription.getDateInscription() );
        inscriptionResponse.dateAnnulation( inscription.getDateAnnulation() );
        inscriptionResponse.commentaire( inscription.getCommentaire() );

        return inscriptionResponse.build();
    }

    private int inscriptionOffreId(Inscription inscription) {
        if ( inscription == null ) {
            return 0;
        }
        Offre offre = inscription.getOffre();
        if ( offre == null ) {
            return 0;
        }
        int id = offre.getId();
        return id;
    }

    private String inscriptionOffreTitre(Inscription inscription) {
        if ( inscription == null ) {
            return null;
        }
        Offre offre = inscription.getOffre();
        if ( offre == null ) {
            return null;
        }
        String titre = offre.getTitre();
        if ( titre == null ) {
            return null;
        }
        return titre;
    }
}
