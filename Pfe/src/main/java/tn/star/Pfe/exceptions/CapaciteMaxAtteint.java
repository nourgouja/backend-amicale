package tn.star.Pfe.exceptions;

public class CapaciteMaxAtteint extends RuntimeException {

    public CapaciteMaxAtteint() {
        super("Capacité maximale atteinte");
    }

    public CapaciteMaxAtteint(String message) {
        super(message);
    }
}