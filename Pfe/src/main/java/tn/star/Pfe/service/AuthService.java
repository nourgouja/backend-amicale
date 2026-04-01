package tn.star.Pfe.service;

import jakarta.mail.MessagingException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import tn.star.Pfe.dto.auth.*;
import tn.star.Pfe.entity.*;
import tn.star.Pfe.exceptions.BadRequestException;
import tn.star.Pfe.exceptions.NotFoundException;
import tn.star.Pfe.mapper.UserMapper;
import tn.star.Pfe.repository.UserRepository;
import tn.star.Pfe.security.JwtUtils;
import tn.star.Pfe.security.UserPrincipal;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authManager;
    private final JwtUtils jwtUtils;

    // Login
    public AuthResponse login(LoginRequest req) {
        Authentication auth = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.email(), req.motDePasse())
        );

        UserPrincipal principal = (UserPrincipal) auth.getPrincipal();
        return buildAuthResponse(principal);
    }

    public AuthResponse refresh(String refreshToken) {
        if (!jwtUtils.validateToken(refreshToken)) {
            throw new BadRequestException("Refresh token invalide ou expiré");
        }
        //walet object
        String email = jwtUtils.extractEmail(refreshToken);
        Object u = userRepository.findByEmail(email)
                .orElseThrow(() -> new NotFoundException("Utilisateur non trouvé"));

        return buildAuthResponse(UserPrincipal.from((User) u));
    }

    public UserResponse creerUtilisateur(CreateUtilisateurRequest req, UserMapper mapper) {
        if (userRepository.existsByEmail(req.email())) {
            throw new BadRequestException("Email déjà utilisé");
        }

        String hash = passwordEncoder.encode(req.motDePasse());

        User u = switch (req.role()) {
            case ADHERENT -> Adherent.builder()
                    .email(req.email())
                    .motDePasse(hash)
                    .nom(req.nom())
                    .prenom(req.prenom())
                    .actif(false)
                    .build();

            case MEMBRE_BUREAU -> MembreBureau.builder()
                    .email(req.email())
                    .motDePasse(hash)
                    .nom(req.nom())
                    .prenom(req.prenom())
                    .poste(req.poste())
                    .actif(false)
                    .build();

            case ADMIN -> Admin.builder()
                    .email(req.email())
                    .motDePasse(hash)
                    .nom(req.nom())
                    .prenom(req.prenom())
                    .actif(false)
                    .build();
        };

        return mapper.toResponse(userRepository.save(u));
    }

    private AuthResponse buildAuthResponse(UserPrincipal p) {
        return new AuthResponse(
                jwtUtils.generateAccessToken(p),
                jwtUtils.generateRefreshToken(p.getUsername()),
                p.getUsername(),
                p.getAuthorities().iterator().next().getAuthority()
        );
    }
}