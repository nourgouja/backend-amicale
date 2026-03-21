package tn.star.Pfe.mapper;

import org.mapstruct.*;
import tn.star.Pfe.dto.offre.OffreResponse;
import tn.star.Pfe.entity.Offre;

import java.util.Base64;

@Mapper(componentModel = "spring")
public interface OffreMapper {

    @Mapping(source = "type", target = "typeOffre")
    @Mapping(source = "statut", target = "statutOffre")
    @Mapping(target = "imageBase64", expression = "java(encodeImage(offre))")
    @Mapping(target = "placeRestantes", expression = "java(offre.getPlacesRestantes())")
    OffreResponse toResponse(Offre offre);

    default String encodeImage(Offre offre) {
        return offre.getImage() != null ?
                Base64.getEncoder().encodeToString(offre.getImage()) : null;
    }
}