package org.iut.mastermind.domain.partie;

import org.iut.mastermind.domain.proposition.Reponse;

public record ResultatPartie(Reponse resultat, boolean isTermine) {

    public static final ResultatPartie ERROR = new ResultatPartie(null, true);


    public static ResultatPartie of(Reponse resultat, boolean isTermine) {
        return new ResultatPartie(resultat, isTermine);
    }


    public static ResultatPartie error() {
        return ERROR;
    }


    public boolean isError() {
        return resultat == null && isTermine;
    }
}

