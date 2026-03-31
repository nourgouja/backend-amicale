package tn.star.Pfe.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;
import tn.star.Pfe.enums.StatutPaiement;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "adherent")
@DiscriminatorValue("Adherent")
@Getter
@NoArgsConstructor
@SuperBuilder
public class Adherent extends User{

    @OneToMany(mappedBy = "adherent", fetch= FetchType.LAZY)
    @Builder.Default
    private List<Inscription> inscriptions=new ArrayList<>();

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private StatutPaiement statutCotisation = StatutPaiement.EN_ATTENTE;

}
