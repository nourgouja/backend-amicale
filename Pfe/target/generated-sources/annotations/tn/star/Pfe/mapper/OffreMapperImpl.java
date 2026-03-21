package tn.star.Pfe.mapper;

import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;
import tn.star.Pfe.dto.offre.OffreResponse;
import tn.star.Pfe.entity.Offre;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2026-03-21T16:19:10+0100",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 17.0.15 (Microsoft)"
)
@Component
public class OffreMapperImpl implements OffreMapper {

    @Override
    public OffreResponse toResponse(Offre offre) {
        if ( offre == null ) {
            return null;
        }

        OffreResponse.OffreResponseBuilder offreResponse = OffreResponse.builder();

        offreResponse.typeOffre( offre.getType() );
        offreResponse.statutOffre( offre.getStatut() );
        offreResponse.id( offre.getId() );
        offreResponse.titre( offre.getTitre() );
        offreResponse.description( offre.getDescription() );
        offreResponse.dateDebut( offre.getDateDebut() );
        offreResponse.dateFin( offre.getDateFin() );
        offreResponse.capaciteMax( offre.getCapaciteMax() );
        offreResponse.prixParPersonne( offre.getPrixParPersonne() );
        offreResponse.lieu( offre.getLieu() );
        offreResponse.imageType( offre.getImageType() );
        offreResponse.imageNom( offre.getImageNom() );
        offreResponse.createdAt( offre.getCreatedAt() );
        offreResponse.updatedAt( offre.getUpdatedAt() );

        offreResponse.imageBase64( encodeImage(offre) );
        offreResponse.placeRestantes( offre.getPlacesRestantes() );

        return offreResponse.build();
    }
}
