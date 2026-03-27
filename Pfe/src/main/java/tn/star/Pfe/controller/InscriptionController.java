package tn.star.Pfe.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import tn.star.Pfe.dto.inscription.InscriptionResponse;
import tn.star.Pfe.entity.Adherent;
import tn.star.Pfe.entity.User;
import tn.star.Pfe.exceptions.EligibiliteException;
import tn.star.Pfe.exceptions.NotFoundException;
import tn.star.Pfe.repository.UserRepository;
import tn.star.Pfe.security.UserPrincipal;
import tn.star.Pfe.service.InscriptionService;

import java.util.List;

@RestController
@RequestMapping("/inscriptions")
@RequiredArgsConstructor
public class InscriptionController {

    private final InscriptionService inscriptionService;
    private final UserRepository userRepository;

    @PostMapping("/inscrire/{offreId}")
    public ResponseEntity<InscriptionResponse> inscrire(
            @PathVariable int offreId,
            @AuthenticationPrincipal UserPrincipal principal) {

        Object user = userRepository
                .findByEmail(principal.getUsername())
                .orElseThrow(() -> new NotFoundException("Utilisateur non trouvé"));

        if (!(user instanceof Adherent adherent))
            throw new EligibiliteException("Seuls les adhérents peuvent s'inscrire");

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(inscriptionService.inscrire(offreId, adherent));
    }

    @PatchMapping("/annuler/{inscriptionId}")
    public ResponseEntity<InscriptionResponse> annuler(
            @PathVariable int inscriptionId,
            @AuthenticationPrincipal UserPrincipal principal) {

        Object user = userRepository
                .findByEmail(principal.getUsername())
                .orElseThrow(() -> new NotFoundException("Utilisateur non trouvé"));

        if (!(user instanceof Adherent adherent))
            throw new EligibiliteException("Seuls les adhérents peuvent annuler");

        return ResponseEntity.ok(inscriptionService.annuler(adherent, inscriptionId));
    }

    @GetMapping("/mesinscriptions")
    public ResponseEntity<List<InscriptionResponse>> mesInscriptions(
            @AuthenticationPrincipal UserPrincipal principal) {

        Object user = userRepository
                .findByEmail(principal.getUsername())
                .orElseThrow(() -> new NotFoundException("Utilisateur non trouvé"));

        if (!(user instanceof Adherent adherent))
            throw new EligibiliteException("Seuls les adhérents ont des inscriptions");

        return ResponseEntity.ok(inscriptionService.mesInscriptions(adherent));
    }

    @PatchMapping("/confirmer/{inscriptionId}")
    public ResponseEntity<InscriptionResponse> confirmer(@PathVariable int inscriptionId) {
        return ResponseEntity.ok(inscriptionService.confirmer(inscriptionId));
    }


    @GetMapping("/offre/{offreId}")
    public ResponseEntity<List<InscriptionResponse>> inscritsParOffre(@PathVariable int offreId) {
        return ResponseEntity.ok(inscriptionService.inscritsParOffre(offreId));
    }
}