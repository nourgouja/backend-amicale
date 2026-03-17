package tn.star.Pfe.service;

import jakarta.transaction.Transactional;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.star.Pfe.dto.inscription.InscriptionRequest;
import tn.star.Pfe.dto.inscription.InscriptionResponse;
import tn.star.Pfe.entity.Inscription;
import tn.star.Pfe.entity.Offre;
import tn.star.Pfe.enums.StatutInscription;
import tn.star.Pfe.enums.StatutOffre;
import tn.star.Pfe.exceptions.CapaciteMaxAtteint;
import tn.star.Pfe.exceptions.InscriptionExistants;
import tn.star.Pfe.exceptions.NotFoundException;
import tn.star.Pfe.exceptions.OffreFermee;
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

    @Transactional
    public InscriptionResponse inscrire(int adherentId, InscriptionRequest req) throws Exception{
        Offre offre = offreRepository.findById(req.getOffreId()).orElseThrow(()-> new NotFoundException("offre introuvable:" + req.getOffreId()));

        if(offre.getStatut() != StatutOffre.OUVERTE) throw new OffreFermee();

        if(offre.getDateFin().isBefore(LocalDate.now())) throw new OffreFermee();

        if(inscriptionRepository.existsByOffreAndAdherentId(offre, adherentId)) throw new InscriptionExistants();

        int confirmes = inscriptionRepository.countByOffreAndStatut(offre, StatutInscription.CONFIRMEE);
        if (confirmes>= offre.getCapaciteMax()) throw new CapaciteMaxAtteint();

        // create inscription
        Inscription inscription=Inscription.builder()
                .offre(offre)
                .adherentId(adherentId)
                .mailAdherent(req.getMaillAdherent())
                .statut(StatutInscription.EN_ATTENTE)
                .montant(offre.getPrixParPersonne())
                .build();
        inscription=inscriptionRepository.save((inscription));
        return toResponse(inscription);
    }

    @Transactional
    public InscriptionResponse annuler(int adherentId, int inscriptionId){
        Inscription inscription = inscriptionRepository.findByIdAndAdherentId(inscriptionId, adherentId).orElseThrow(()->new NotFoundException("inscriptuon introuvable"));
        inscription.setStatut(StatutInscription.ANNULEE);
        inscription.setDateAnnulation(LocalDateTime.now());
        return toResponse(inscriptionRepository.save(inscription));
    }


    @Transactional
    public InscriptionResponse confirmer(int inscriptionId){
        Inscription inscription=inscriptionRepository.findById(inscriptionId).orElseThrow(()-> new NotFoundException("Inscription introuvzbkr"));
        inscription.setStatut(StatutInscription.CONFIRMEE);
        return toResponse(inscriptionRepository.save(inscription));
    }


    // research stream and map
    public List<InscriptionResponse> mesInscriptions(int adherentId){
        return inscriptionRepository.findByAdherentId(adherentId).stream().map(this::toResponse).toList();
    }

    public List<InscriptionResponse> inscritsParOffre(int offreId){
        Offre offre=offreRepository.findById(offreId).orElseThrow(()->new NotFoundException("offre introuvable"));
        return inscriptionRepository.findByOffre(offre).stream().map(this::toResponse).toList();
    }

    public InscriptionResponse toResponse(Inscription i){
        return InscriptionResponse.builder()
                .id(i.getId())
                .offreId(i.getOffre().getId())
                .offreTitre(i.getOffre().getTitre())
                .adherentId(i.getAdherentId())
                .mailAdherent(i.getMailAdherent())
                .statut(i.getStatut())
                .montant(i.getMontant())
                .dateInscription(i.getDateInscription())
                .dateAnnulation(i.getDateAnnulation())
                .commentaire(i.getCommentaire())
                .build();
    }

}
