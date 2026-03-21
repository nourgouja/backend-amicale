package tn.star.Pfe.mapper;

import org.mapstruct.*;
import tn.star.Pfe.dto.inscription.InscriptionResponse;
import tn.star.Pfe.entity.Inscription;

@Mapper(componentModel = "spring")
public interface InscriptionMapper {

    @Mapping(source = "offre.id", target = "offreId")
    @Mapping(source = "offre.titre", target = "offreTitre")
    InscriptionResponse toResponse(Inscription inscription);

}