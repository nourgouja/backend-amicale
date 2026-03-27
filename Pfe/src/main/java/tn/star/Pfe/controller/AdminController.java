package tn.star.Pfe.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.star.Pfe.dto.auth.CreateUtilisateurRequest;

import tn.star.Pfe.dto.auth.UserResponse;
import tn.star.Pfe.entity.User;
import tn.star.Pfe.exceptions.NotFoundException;
import tn.star.Pfe.mapper.UserMapper;
import tn.star.Pfe.repository.UserRepository;
import tn.star.Pfe.service.AuthService;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AuthService authService;
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @GetMapping("/utilisateurs")
    public ResponseEntity<List<UserResponse>> listerTous() {
        return ResponseEntity.ok(
                userMapper.toResponseList(userRepository.findAll())
        );
    }

    @PostMapping("/utilisateurs")
    public ResponseEntity<UserResponse> creer(
            @Valid @RequestBody CreateUtilisateurRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(authService.creerUtilisateur(req, userMapper));
    }

    @PutMapping("/utilisateurs/{id}/activer")
    public ResponseEntity<Void> activer(@PathVariable int id) {
        User u = (User) userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Utilisateur non trouvé"));
        u.setActif(true);
        userRepository.save(u);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/utilisateurs/{id}/desactiver")
    public ResponseEntity<Void> desactiver(@PathVariable int id) {
        User u = (User) userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Utilisateur non trouvé"));
        u.setActif(false);
        userRepository.save(u);
        return ResponseEntity.noContent().build();
    }
}