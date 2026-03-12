package tn.star.Pfe.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.star.Pfe.dto.OffreRequest;
import tn.star.Pfe.dto.OffreResponse;
import tn.star.Pfe.entity.Offre;
import tn.star.Pfe.enums.StatutOffre;
import tn.star.Pfe.exceptions.BadRequestException;
import tn.star.Pfe.exceptions.NotFoundException;
import tn.star.Pfe.repository.OffreRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OffreService {

    private final OffreRepository offreRepository;

    public List<OffreResponse> listerOffresOuvertes() {
        return offreRepository.findByStatut(StatutOffre.OUVERTE)
                .stream().map(this::toResponse).toList();
    }

    public OffreResponse trouverParId(int id) {
        return toResponse(offreRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Offre introuvable : " + id)));
    }

    public List<OffreResponse> rechercherParTitre(String titre) {
        return offreRepository.findByTitreContainingIgnoreCase(titre)
                .stream().map(this::toResponse).toList();
    }

    @Transactional
    public OffreResponse creer(OffreRequest.CreateOffreRequest req) {
        if (!req.getDateFin().isAfter(req.getDateDebut()))
            throw new BadRequestException("La date de fin doit être après la date de début.");

        Offre offre = Offre.builder()
                .titre(req.getTitre())
                .description(req.getDescription())
                .type(req.getTypeOffre())
                .statut(StatutOffre.OUVERTE)
                .dateDebut(req.getDateDebut())
                .dateFin(req.getDateFin())
                .capaciteMax(req.getCapaciteMax())
                .prixParPersonne(req.getPrixParPersonne())
                .lieu(req.getLieu())
                .imageURL(req.getImageURL())
                .build();

        return toResponse(offreRepository.save(offre));
    }

    @Transactional
    public OffreResponse modifier(int id,
                                  OffreRequest.UpdateOffreRequest req) {
        Offre offre = offreRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Offre introuvable : " + id));

        if (offre.getStatut() == StatutOffre.ANNULEE)
            throw new BadRequestException("Impossible de modifier une offre annulée.");

        if (req.getTitre() != null)
            offre.setTitre(req.getTitre());
        if (req.getDescription() != null)
            offre.setDescription(req.getDescription());
        if (req.getTypeOffre() != null)
            offre.setType(req.getTypeOffre());
        if (req.getCapaciteMax() != null)
            offre.setCapaciteMax(req.getCapaciteMax());
        if (req.getPrixParPersonne() != 0.0)
            offre.setPrixParPersonne(req.getPrixParPersonne());
        if (req.getLieu() != null)
            offre.setLieu(req.getLieu());
        if (req.getDateDebut() != null)
            offre.setDateDebut(req.getDateDebut());
        if (req.getDateFin() != null)
            offre.setDateFin(req.getDateFin());

        return toResponse(offreRepository.save(offre));
    }

    @Transactional
    public OffreResponse fermer(int id) {
        Offre offre = offreRepository.findById(id).orElseThrow(() -> new NotFoundException("Offre introuvable : " + id));
        offre.setStatut(StatutOffre.FERMEE);
        return toResponse(offreRepository.save(offre));
    }

    @Transactional
    public void supprimer(int id) {
        if (!offreRepository.existsById(id))
            throw new NotFoundException("Offre introuvable : " + id);
        offreRepository.deleteById(id);
    }

    public OffreResponse toResponse(Offre o) {
        return OffreResponse.builder()
                .id(o.getId())
                .titre(o.getTitre())
                .description(o.getDescription())
                .typeOffre(o.getType())
                .statutOffre(o.getStatut())
                .dateDebut(o.getDateDebut())
                .dateFin(o.getDateFin())
                .capaciteMax(o.getCapaciteMax())
                .placeRestantes(o.getPlacesRestantes())
                .prixParPersonne(o.getPrixParPersonne())
                .lieu(o.getLieu())
                .imageURL(o.getImageURL())
                .createdAt(o.getCreatedAt())
                .updatedAt(o.getUpdatedAt())
                .build();
    }
}