package tn.star.Pfe.exceptions;

public class OffreFermee extends RuntimeException {
    public OffreFermee() {
        super("Cette offre n'accepte plus d'inscriptions.");
    }
}
