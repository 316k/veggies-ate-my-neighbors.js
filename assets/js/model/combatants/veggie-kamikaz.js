/**
 * Veggie kamikaz
 *
 * @author Guillaume Riou, Nicolas Hurtubise
 */
function VeggieKamikaz() {
    Combattant.apply(this);
    this.initialise();
}

VeggieKamikaz.prototype = new Combattant();

VeggieKamikaz.prototype.initialise = function() {
    this.attaque = 100;
    this.gentil = false;
    this.hitbox.height = 80;
    this.hitbox.width = 60;
    this.animationFrameRate = 5;

    this.vie = 20;

    this.nbrImagesAnimation[navigator.Etat.DEPLACEMENT] = 5;
    this.nbrImagesAnimation[navigator.Etat.ATTAQUE] = 7;

    this.vitesseAction[navigator.Action.DEPLACEMENT] = -116;
    this.vitesseAction[navigator.Action.ATTAQUE] = 0.6;

    this.sprites[navigator.Etat.DEPLACEMENT] = new RepresentationImage(["veggies", "kamikaz", "walking"]);
    this.sprites[navigator.Etat.ATTAQUE] = new RepresentationImage(["explosion"]);
};

/**
 * Meurt après avoir attaqué.
 * @param nbrFois 
 */
VeggieKamikaz.prototype.attaquer = function(nbrFois) {
    // Entite[]
    var aEnlever = [];
    for (var j = 0; j < nbFois; j++) {

        for (var index in this.cibles) {
            var cible = this.cibles[index];

            if (this.isEnnemi(cible)) {
                cible.vie -= this.attaque;
            } else {
                aEnlever.push(cible);
            }

        }
    }

    for (var index in this.cibles) {
        var cible = this.cibles[index];
        if (cible.vie <= 0) {
            aEnlever.push(cible);
        }
    }

    this.cibles = this.cibles.remove_elements(aEnlever);

    if (this.cibles.length == 0) {
        this.setEtat(navigator.Etat.DEPLACEMENT);
    }

    if (nbrFois > 0) {
        this.setVie(0);
    }
};

VeggieKamikaz.prototype.action = function(nbFois) {
    return null;
};
