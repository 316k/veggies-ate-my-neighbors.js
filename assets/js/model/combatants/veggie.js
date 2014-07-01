/**
 * Veggie
 *
 * @author Guillaume Riou, Nicolas Hurtubise
 */
function Veggie() {
    Combattant.apply(this);
    this.combattant_init();
    this.init();
};
Veggie.prototype = new Combattant();

Veggie.prototype.init = function() {
    this.attaque = 1;
    this.gentil = false;
    this.hitbox.h = 80;
    this.hitbox.w = 60;
    this.animationFrameRate = 5;
    this.etat = navigator.Etat.DEPLACEMENT;

    this.vie = 20;

    this.nbrImagesAnimation[navigator.Etat.DEPLACEMENT] = 5;
    this.nbrImagesAnimation[navigator.Etat.ATTAQUE] = 7;

    this.vitesseAction[navigator.Action.DEPLACEMENT] = -16;
    this.vitesseAction[navigator.Action.ATTAQUE] = 1;

    this.sprites[navigator.Etat.DEPLACEMENT] = new RepresentationImage(["veggies", "normal", "walking"]);
    this.sprites[navigator.Etat.ATTAQUE] = new RepresentationImage(["veggies", "normal", "attack"]);
};
