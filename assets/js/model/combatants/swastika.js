/**
 * Swastika (lancé par un Hitler végétarien).
 *
 * @author Guillaume Riou, Nicolas Hurtubise
 */
function Swastika() {
    Combattant.apply(this);
    this.initialise();
}

Swastika.prototype = new Combattant();

Swastika.prototype.initialise = function() {
    this.gentil = false;
    this.isProjectile = true;
    this.vitesseAction[Action.DEPLACEMENT] = -190;
    this.vitesseAction[Action.ATTAQUE] = 70;
    this.hitbox.w = 12;
    this.hitbox.h = 12;
    this.animationFrameRate = 19;
    this.nbrImagesAnimation[navigator.Etat.ATTAQUE] = 6;
    this.nbrImagesAnimation[navigator.Etat.DEPLACEMENT] = 6;

    this.attaque = 5;

    this.etat = navigator.Etat.DEPLACEMENT;

    this.sprites[navigator.Etat.DEPLACEMENT] = new RepresentationImage(["swastika"]);
    this.sprites[navigator.Etat.ATTAQUE] = new RepresentationImage(["swastika"]);
};
