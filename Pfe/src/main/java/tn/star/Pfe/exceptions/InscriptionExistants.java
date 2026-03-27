package tn.star.Pfe.exceptions;

public class InscriptionExistants extends RuntimeException {

    public InscriptionExistants() {
        super("Inscription déjà existante");
    }

    public InscriptionExistants(String message) {
        super(message);
    }
}