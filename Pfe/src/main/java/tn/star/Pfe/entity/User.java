package tn.star.Pfe.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import tn.star.Pfe.enums.Role;

import java.time.LocalDateTime;


@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.JOINED)
@Getter @Setter @AllArgsConstructor @NoArgsConstructor @SuperBuilder
public abstract class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Email(regexp = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$")
    private String email;
    private String nom;
    private String prenom;
    private String motDePasse;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder.Default
    private boolean actif = true;

    @CreationTimestamp
    private LocalDateTime createdAt;
    @UpdateTimestamp
    private LocalDateTime updatedAt;



}