package tn.star.Pfe.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.star.Pfe.dto.inscription.InscriptionResponse;
import tn.star.Pfe.entity.Adherent;
import tn.star.Pfe.entity.Inscription;
import tn.star.Pfe.entity.Offre;
import tn.star.Pfe.enums.StatutInscription;
import tn.star.Pfe.enums.StatutOffre;
import tn.star.Pfe.enums.StatutPaiement;
import tn.star.Pfe.exceptions.*;
import tn.star.Pfe.mapper.InscriptionMapper;
import tn.star.Pfe.repository.InscriptionRepository;
import tn.star.Pfe.repository.OffreRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class InscriptionService {

    private final InscriptionRepository inscriptionRepository;
    private final OffreRepository offreRepository;
    private final InscriptionMapper inscriptionMapper;
    private StatutPaiement statut;

    public InscriptionResponse inscrire(int offreId, Adherent adherent) {

        if (!adherent.isActif())
            throw new EligibiliteException("Votre compte est désactivé");

        Offre offre = offreRepository.findById(offreId)
                .orElseThrow(() -> new NotFoundException("Offre non trouvée"));

        if (offre.getStatut() != StatutOffre.OUVERTE)
            throw new OffreFermee("L'offre n'est pas ouverte");
        if (offre.getDateFin().isBefore(LocalDate.now()))
            throw new BadRequestException("L'offre est expirée");
        if (inscriptionRepository.existsByOffreAndAdherent(offre, adherent))
            throw new InscriptionExistants("Déjà inscrit à cette offre");
        if (offre.getPlacesRestantes() <= 0)
            throw new CapaciteMaxAtteint("Plus de places disponibles");

        Inscription ins = Inscription.builder()
                .offre(offre).adherent(adherent)
                .montant(offre.getPrixParPersonne())
                .build();
        return inscriptionMapper.toResponse(
                inscriptionRepository.save(ins), null);
    }


    @Transactional
    public InscriptionResponse annuler(Adherent adherent, int inscriptionId) {
        Inscription inscription = inscriptionRepository
                .findByIdAndAdherent(inscriptionId, adherent)
                .orElseThrow(() -> new NotFoundException("Inscription introuvable"));

        inscription.setStatut(StatutInscription.ANNULEE);
        inscription.setDateAnnulation(LocalDateTime.now());

        return inscriptionMapper.toResponse(inscriptionRepository.save(inscription), null);
    }

    @Transactional
    public InscriptionResponse confirmer(int inscriptionId) {
        Inscription inscription = inscriptionRepository.findById(inscriptionId)
                .orElseThrow(() -> new NotFoundException("Inscription introuvable"));

        inscription.setStatut(StatutInscription.CONFIRMEE);

        return inscriptionMapper.toResponse(inscriptionRepository.save(inscription), null);
    }

    public List<InscriptionResponse> mesInscriptions( Adherent adherent) {
        return inscriptionRepository.findByAdherent(adherent)
                .stream()
                .map(i -> inscriptionMapper.toResponse(i, null))
                .toList();
    }

    public List<InscriptionResponse> inscritsParOffre(int offreId) {
        Offre offre = offreRepository.findById(offreId)
                .orElseThrow(() -> new NotFoundException("Offre introuvable"));

        return inscriptionRepository.findByOffre(offre)
                .stream()
                .map(i -> inscriptionMapper.toResponse(i, null))
                .toList();
    }

    @Transactional
    public Object mettreAjourPaiement(int inscriptionId) {
        Inscription inscription = inscriptionRepository.findById(inscriptionId).orElseThrow(()-> new NotFoundException("inscription non trouve"));
        inscription.setStatutPaiement(statut);
        return inscriptionMapper.toResponse(inscriptionRepository.save(inscription), null);

    }
}