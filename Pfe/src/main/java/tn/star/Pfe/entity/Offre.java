package tn.star.Pfe.entity;


import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import tn.star.Pfe.enums.StatutInscription;
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
    private int id;

    private String description;

    @Enumerated(EnumType.STRING)
    private TypeOffre type;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private StatutOffre statut = StatutOffre.OUVERTE;

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

    //methodes
    public int getPlacesRestantes(){
        long confirme = inscriptions.stream().filter(i->i.getStatut()== StatutInscription.CONFIRMEE).count();
        return capaciteMax-(int)confirme;
    }
}
