package tn.star.Pfe.exceptions;

public class CapaciteMaxAtteint extends RuntimeException {
    public CapaciteMaxAtteint() {
        super("L'offre est complète. Aucune place disponible.");
    }
}