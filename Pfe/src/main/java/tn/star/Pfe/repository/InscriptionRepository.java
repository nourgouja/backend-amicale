package tn.star.Pfe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.star.Pfe.entity.Inscription;
import tn.star.Pfe.entity.Offre;
import tn.star.Pfe.enums.StatutInscription;

import java.util.List;
import java.util.Optional;

@Repository
public interface InscriptionRepository extends JpaRepository<Inscription, Integer> {

    List<Inscription> findByOffre(Offre offre);

    boolean existsByOffreAndAdherentId(Offre offre, int adherentId);

    List<Inscription> findByAdherentId(int adherentId);

    Optional<Inscription> findByIdAndAdherentId(int id, int adherentId);

    List<Inscription> findByStatut(StatutInscription statut);

    List<Inscription> findByOffreAndStatut(Offre offre, StatutInscription statut);

    int countByOffreAndStatut(Offre offre, StatutInscription statut);
}