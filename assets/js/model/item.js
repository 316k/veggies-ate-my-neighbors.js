/**
 * Item dans l'inventaire du joueur (graines de plantes, ...).
 *
 * @author Nicolas Hurtubise
 */
function Item(nom, vitesseRechargement, cout, combattant) {
    // Associated combattant
    this.nom = nom;
    this.vitesseRechargement = vitesseRechargement;
    // Price (in number of suns required)
    this.cout = cout;
    // recharge < 1 means "charging", recharge == 1 means "sready to use"
    this.recharge = 1;
    this.combattant = combattant;
    /**
     * Timestamp du dernier appel de la méthode tic
     */
    this.dernierTicTimestamp = window.performance.now();
}

Item.prototype.setVitesseRechargement = function(vitesseRechargement) {
    this.vitesseRechargement = vitesseRechargement;
};

Item.prototype.isUtilisable = function() {
    return this.recharge === 1 && navigator.Joueur.nbrSoleils >= this.cout;
};

/**
 * Incrémente le compteur de charge de l'objet.
 */
Item.prototype.tic = function() {
    var ts = window.performance.now();

    if (this.recharge < 1) {

        var deltaTemps = ts - this.dernierTicTimestamp;

        // v = dRecharge/dT => dRecharge = v*dT
        this.recharge += this.vitesseRechargement * deltaTemps;

        if (this.recharge > 1) {
            this.recharge = 1;
        }
    }

    this.dernierTicTimestamp = ts;
};
