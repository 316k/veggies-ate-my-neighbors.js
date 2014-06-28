/**
 * @deprecated Use navigator.Joueur directly
 */
function JoueurController() {
    navigator.Joueur = new Joueur();
}

/**
 * @return le nombre de soleil du joueur
 */
JoueurController.prototype.getSoleils = function() {
    return navigator.Joueur.getSoleils();
}

/**
 * Ajoute un nombre de soleil au joueur.
 * @param int soleils nb à ajouter 
 */
JoueurController.prototype.addSoleils = function(soleils) {
    navigator.Joueur.addSoleils(soleils);
};

/**
 * Met la sélection du joueur à l'index spécifié par le paramètre.
 * @param int selection sélection.
 */
JoueurController.prototype.setSelection = function(selection) {
    navigator.Joueur.setSelection(selection);
}

/**
 * Secret.
 */
JoueurController.prototype.easterEgg = function() {
    var items = navigator.Joueur.getItems();
    for (var item in items) {
        items[item].setVitesseRechargement(100000);
    }
};
