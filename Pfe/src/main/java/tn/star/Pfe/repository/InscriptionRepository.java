package tn.star.Pfe.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import tn.star.Pfe.entity.Inscription;
import tn.star.Pfe.entity.Offre;
import tn.star.Pfe.enums.StatutInscription;

import java.util.List;

@Repository
public interface InscriptionRepository extends JpaRepository<Inscription, Integer> {

    List<Inscription> findByOffre(Offre offre);
    int countByOffreAndStatut(Offre offre, StatutInscription statut);
}