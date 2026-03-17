package tn.star.Pfe.dto.inscription;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class InscriptionRequest {

    //offer id
    @NotNull(message = "l'id de loffre est obligatoire")
    private int offreId;
    private String maillAdherent;

}
