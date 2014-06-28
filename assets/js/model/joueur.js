/**
 * Contient les données relatives au joueur (plantes disponibles, nombre de
 * soleils, high-score, ...)
 *
 * @author Nicolas Hurtubise, Guillaume Riou
 */
function Joueur() {
    /**
     * Plantes débloquées.
     */
    this.items = [];
    // First plants
    this.items.push(new Item("pea-shooter", 0.0015, 100, new Peashooter()));
    this.items.push(new Item("sunflower", 0.001, 50, new Sunflower()));
    this.nbrSoleils = 250;
    /**
     * Plante sélectionnée.
     * int/null
     */
    this.selection = null;
    /**
     * Nombre de veggies tués.
     */
    this.kills = 0;
}

/**
 * Recharge tous les items.
 */
Joueur.prototype.tic = function() {
    // Recharge des items
    for (var index in this.items) {
        this.items[index].tic();
    }
}

/**
 * Met la sélection à un item si celui-ci est chargé.
 * @param int/null selection
 */
Joueur.prototype.setSelection = function(selection) {
    if (selection != null && this.items[selection].isUtilisable()) {
        this.selection = selection;
    } else {
        this.selection = null;
    }
}

Joueur.prototype.getItem = function() {
    if (this.selection == null) {
        return null;
    }

    return this.items[this.selection];
};

Joueur.prototype.debloquerItem = function(item) {
    this.items.push(item);
};

/**
 * Utilise l'item sélectionné
 * @param Point position position où mettre le combattant.
 * @return Combattant Le combatant de L'item à la position sélectionnée.
 */
Joueur.prototype.useCurrentItem = function(position) {
    // Génère le combatant conféré par l'item
    var combattant = this.getItem().combattant.clone();
    combattant.hitbox.setLocation(position);
    combattant.lineOfSight.setLocation(position);

    this.getItem().recharge = 0;
    this.nbrSoleils -= this.getItem().cout;
    this.selection = null;

    return combattant;
}
