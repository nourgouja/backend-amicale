package tn.star.Pfe.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.star.Pfe.dto.OffreRequest;
import tn.star.Pfe.dto.OffreResponse;
import tn.star.Pfe.service.OffreService;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OffreController {

    private final OffreService offreService;

    @GetMapping("/offres")
    public ResponseEntity<List<OffreResponse>> listerOuvertes() {
        return ResponseEntity.ok(offreService.listerOffresOuvertes());
    }

    @GetMapping("/offres/{id}")
    public ResponseEntity<OffreResponse> trouverParId(
            @PathVariable int id) {
        return ResponseEntity.ok(offreService.trouverParId(id));
    }

    @GetMapping("/offres/search")
    public ResponseEntity<List<OffreResponse>> rechercher(
            @RequestParam String titre) {
        return ResponseEntity.ok(offreService.rechercherParTitre(titre));
    }

    @PostMapping("/offres/creer")
    public ResponseEntity<OffreResponse> creer(
            @Valid @RequestBody OffreRequest.CreateOffreRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(offreService.creer(req));
    }

    @PutMapping("/offres/modifier/{id}")
    public ResponseEntity<OffreResponse> modifier(
            @PathVariable int id,
            @Valid @RequestBody OffreRequest.UpdateOffreRequest req) {
        return ResponseEntity.ok(offreService.modifier(id, req));
    }

    @PatchMapping("/offres/fermer/{id}")
    public ResponseEntity<OffreResponse> fermer(@PathVariable int id) {
        return ResponseEntity.ok(offreService.fermer(id));
    }

    @DeleteMapping("/offres/supprimer/{id}")
    public ResponseEntity<Void> supprimer(@PathVariable int id) {
        offreService.supprimer(id);
        return ResponseEntity.noContent().build();
    }
}