package tn.star.Pfe.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import tn.star.Pfe.enums.StatutInscription;
import tn.star.Pfe.enums.StatutPaiement;

import java.time.LocalDateTime;

@Entity
@Table(name = "inscription")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "adherent_id")
    private Adherent adherent;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private StatutPaiement statutPaiement = StatutPaiement.EN_ATTENTE;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="offre_id")
    private Offre offre;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private StatutInscription statut = StatutInscription.EN_ATTENTE;

    @CreationTimestamp
    private LocalDateTime dateInscription;


    private LocalDateTime dateAnnulation;

    private double montant;

    private String commentaire;




}
