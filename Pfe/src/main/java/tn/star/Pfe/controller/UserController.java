package tn.star.Pfe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import tn.star.Pfe.dto.auth.UpdateProfilRequest;
import tn.star.Pfe.dto.auth.UserResponse;
import tn.star.Pfe.entity.User;
import tn.star.Pfe.exceptions.NotFoundException;
import tn.star.Pfe.mapper.UserMapper;
import tn.star.Pfe.repository.UserRepository;
import tn.star.Pfe.security.UserPrincipal;

@RestController
@RequestMapping("/api/utilisateurs")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/profil")
    public ResponseEntity<UserResponse> getProfil(
            @AuthenticationPrincipal UserPrincipal principal) {
        User u = (User) userRepository.findById(principal.getId())
                .orElseThrow(() -> new NotFoundException("Utilisateur non trouvé"));
        return ResponseEntity.ok(userMapper.toResponse(u));
    }


    @PutMapping("/profil")
    public ResponseEntity<UserResponse> updateProfil(
            @AuthenticationPrincipal UserPrincipal principal,
            @RequestBody UpdateProfilRequest req) {
        User u = (User) userRepository.findById(principal.getId())
                .orElseThrow(() -> new NotFoundException("Utilisateur non trouvé"));

        if (req.nom() != null)    u.setNom(req.nom());
        if (req.prenom() != null) u.setPrenom(req.prenom());
        if (req.motDePasse() != null) {
            u.setMotDePasse(passwordEncoder.encode(req.motDePasse()));
        }
        return ResponseEntity.ok(userMapper.toResponse(userRepository.save(u)));
    }
}
