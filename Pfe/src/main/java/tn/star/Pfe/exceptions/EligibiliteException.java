package tn.star.Pfe.exceptions;

public class EligibiliteException extends RuntimeException {

    public EligibiliteException() {
        super("Problème d'éligibilité");
    }

    public EligibiliteException(String message) {
        super(message);
    }
}