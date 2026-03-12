package tn.star.Pfe.exceptions;

public class InscriptionExistants extends RuntimeException {
    public InscriptionExistants() {
        super("Vous êtes déjà inscrit à cette offre.");
    }
}