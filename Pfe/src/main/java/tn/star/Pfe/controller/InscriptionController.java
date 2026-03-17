package tn.star.Pfe.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tn.star.Pfe.dto.inscription.InscriptionRequest;
import tn.star.Pfe.dto.inscription.InscriptionResponse;
import tn.star.Pfe.service.InscriptionService;

import java.util.List;

@RestController
@RequestMapping("/inscriptions")
// why put
@RequiredArgsConstructor
public class InscriptionController {

    // why final
    private final InscriptionService inscriptionService;

    @PostMapping("/inscrire/{adherentId}")
    public ResponseEntity<InscriptionResponse> inscrire(@PathVariable int adherentId, @Valid @RequestBody InscriptionRequest req) throws Exception{
        return ResponseEntity.status(HttpStatus.CREATED).body(inscriptionService.inscrire(adherentId,req));
    }

    @PatchMapping("/annuler/{adherentId}/{inscriptionId}")
    public ResponseEntity<InscriptionResponse> annuler(@PathVariable int adherentId, @PathVariable int inscriptionId) {
        return ResponseEntity.ok(inscriptionService.annuler(adherentId,inscriptionId));
    }

    @PatchMapping("/confirmer/{inscriptionId}")
    public ResponseEntity<InscriptionResponse> confirmer(@PathVariable int inscriptionId){
        return ResponseEntity.ok(inscriptionService.confirmer(inscriptionId));
    }

    @GetMapping("/all/{adherentId}")
    public ResponseEntity<List<InscriptionResponse>> mesInscription(@PathVariable int adherentId){
        return ResponseEntity.ok(inscriptionService.mesInscriptions(adherentId));
    }

    @GetMapping("/offre/{offreId}")
    public ResponseEntity<List<InscriptionResponse>> inscritsParOffre(@PathVariable int offreId){
        return ResponseEntity.ok(inscriptionService.inscritsParOffre(offreId));
    }
}
