package tn.star.Pfe.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import tn.star.Pfe.enums.StatutOffre;
import tn.star.Pfe.enums.TypeOffre;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "offre")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Offre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    private String description;

    @Enumerated(EnumType.STRING)
    private TypeOffre typeoffre;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private StatutOffre statut = StatutOffre.OVERTE;

    private String titre;

    private LocalDate dateDebut;
    private LocalDate dateFin;

    private int capaciteMax;

    private double prixParPersonne;

    private String lieu;

    private String imageURL;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @CreationTimestamp
    private LocalDateTime updatedAt;

    @OneToMany(mappedBy= "offre" , cascade = CascadeType.ALL , orphanRemoval = true)
    @Builder.Default
    private List<Inscription> inscriptions = new ArrayList<>();


}
