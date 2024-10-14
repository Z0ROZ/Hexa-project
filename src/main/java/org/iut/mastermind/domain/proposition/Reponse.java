package org.iut.mastermind.domain.proposition;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Collections.unmodifiableList;

public class Reponse {
    private final String motSecret;
    private final List<Lettre> resultat = new ArrayList<>();

    public Reponse(String mot) {
        this.motSecret = mot;
    }

    public Lettre lettre(int position) {
        return position >= 0 && position < resultat.size() ? resultat.get(position) : null;
    }

    // on construit le résultat en analysant chaque lettre
    // du mot proposé
    public void compare(String essai) {
        boolean[] lettresEvaluated = new boolean[motSecret.length()];

        // Première passe : évaluation des lettres placées
        for (int i = 0; i < essai.length(); i++) {
            if (essai.charAt(i) == motSecret.charAt(i)) {
                resultat.add(Lettre.PLACEE);
                lettresEvaluated[i] = true;
            } else {
                resultat.add(null);
            }
        }

        // Deuxième passe : évaluation des lettres non placées et incorrectes
        for (int i = 0; i < essai.length(); i++) {
            if (resultat.get(i) == null) {
                char lettreEssai = essai.charAt(i);
                boolean trouve = false;

                // Vérifier si la lettre est présente dans le mot secret
                for (int j = 0; j < motSecret.length(); j++) {
                    if (!lettresEvaluated[j] && motSecret.charAt(j) == lettreEssai) {
                        resultat.set(i, Lettre.NON_PLACEE);
                        trouve = true;
                        break;
                    }
                }

                // Si la lettre n'est pas trouvée dans le mot secret, elle est incorrecte
                if (!trouve) {
                    resultat.set(i, Lettre.INCORRECTE);
                }
            }
        }
    }


    // si toutes les lettres sont placées
    public boolean lettresToutesPlacees() {
        return resultat.stream().allMatch(l -> l == Lettre.PLACEE);
    }

    public List<Lettre> lettresResultat() {
        return unmodifiableList(resultat);
    }

}
