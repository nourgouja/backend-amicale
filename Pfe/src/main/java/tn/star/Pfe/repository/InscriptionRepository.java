package tn.star.Pfe.repository;

import jakarta.persistence.Id;
import org.springframework.data.jpa.repository.JpaRepository;
import tn.star.Pfe.entity.Inscription;

public interface InscriptionRepository extends JpaRepository<Inscription, Id> {
}
