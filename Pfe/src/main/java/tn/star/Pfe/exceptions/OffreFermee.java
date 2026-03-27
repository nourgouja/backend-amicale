package tn.star.Pfe.exceptions;

public class OffreFermee extends RuntimeException {

    public OffreFermee() {
        super("L'offre est fermée");
    }

    public OffreFermee(String message) {
        super(message);
    }
}