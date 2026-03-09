package tn.star.Pfe.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import tn.star.Pfe.enums.StatutInscription;

import java.time.LocalDateTime;

@Entity
@Table(name = "inscription" , uniqueConstraints = {@UniqueConstraint(ColumnNames={"adherent_id","offre_id"})})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Inscription {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="adherent_id")
    private Adherent adherent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="offre_id")
    private Offre offre;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private StatutInscription statutInscrip = StatutInscription.EN_ATTENTE;

    @CreationTimestamp
    private LocalDateTime dateInscription;

    @CreationTimestamp
    private LocalDateTime dateAnnulation;

    private double Montant;

    private String commentaire;
}
