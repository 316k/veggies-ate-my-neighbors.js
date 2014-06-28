/**
 * Pois (lancé par un tire-pois)
 *
 * @author Guillaume Riou, Nicolas Hurtubise
 */
function Pois() {
    Combattant.apply(this);
    this.initialise();
}

Pois.prototype = new Combattant();

Pois.prototype.initialise = function() {
    this.gentil = true;
    this.isProjectile = true;
    this.vitesseAction[navigator.Action.DEPLACEMENT] = 220;
    this.vitesseAction[navigator.Action.ATTAQUE] = 70;
    this.hitbox.w = 12;
    this.hitbox.h = 12;
    this.animationFrameRate = 5;
    this.nbrImagesAnimation[navigator.Etat.ATTAQUE] = 1;
    this.nbrImagesAnimation[navigator.Etat.ATTENTE] = 1;
    this.nbrImagesAnimation[navigator.Etat.DEPLACEMENT] = 9;

    this.attaque = 2;

    this.etat = navigator.Etat.DEPLACEMENT;
    this.sprites[navigator.Etat.DEPLACEMENT] = new RepresentationImage(["plants", "pea"]);
    this.sprites[navigator.Etat.ATTAQUE] = new RepresentationImage(["plants", "pea"]);
};

Pois.prototype.action = function(nbFois) {
    return null;
};
