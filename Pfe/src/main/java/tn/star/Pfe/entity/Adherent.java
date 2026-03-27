package tn.star.Pfe.entity;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;

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

}
