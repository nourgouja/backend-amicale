package tn.star.Pfe.repository;

import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import tn.star.Pfe.entity.Offre;

public interface OffreRepository extends JpaRepository<Offre, Id> {
}
