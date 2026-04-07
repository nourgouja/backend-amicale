package tn.star.Pfe.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;
import tn.star.Pfe.entity.Adherent;
import tn.star.Pfe.enums.Role;
import tn.star.Pfe.repository.UserRepository;

@Configuration
@RequiredArgsConstructor
public class DataInitializer {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Bean
    CommandLineRunner initAdmin() {
        return args -> {

            if (userRepository.findByEmail("admin@test.com").isEmpty()) {

                Adherent admin = Adherent.builder()
                        .nom("Admin")
                        .prenom("Admin")
                        .email("admin@test.com")
                        .motDePasse(passwordEncoder.encode("admin123"))
                        .cin("00000000")
                        .telephone("00000000")
                        .role(Role.ADMIN)
                        .actif(true)
                        .build();

                userRepository.save(admin);

                System.out.println("ADMIN créé !");
            }
        };
    }
}