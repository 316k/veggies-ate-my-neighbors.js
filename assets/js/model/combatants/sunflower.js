/**
 * Plante qui produit du soleil.
 *
 * @author Guillaume Riou, Nicolas Hurtubise
 */
function Sunflower() {
    Combattant.apply(this);
    this.combattant_init();
    this.init();
}

Sunflower.prototype = new Combattant();

Sunflower.prototype.init = function() {
    this.gentil = true;
    this.hitbox.w = 80;
    this.hitbox.h = 80;
    this.lineOfSight = new Rectangle();
    this.animationFrameRate = 6;

    this.nbrImagesAnimation[navigator.Etat.ATTENTE] = 4;
    this.nbrImagesAnimation[navigator.Etat.DEPLACEMENT] = 5;
    this.nbrImagesAnimation[navigator.Etat.ATTAQUE] = 1;

    this.vitesseAction[navigator.Action.ACTION] = 1/30;

    this.sprites[navigator.Etat.ATTENTE] = new RepresentationImage(["plants", "sunflower"]);

    this.etat = navigator.Etat.ATTENTE;

    this.vie = 10;
    this.sun_recharge = 0;
};

Sunflower.prototype.tic = function() {
    var soleil = null;
    switch (this.etat) {
        case navigator.Etat.ATTENTE:
            soleil = this.action(this.getNbrActions(navigator.Action.ACTION));
            break;
    }

    return soleil;
};

Sunflower.prototype.action = function(times) {
    this.sun_recharge += times;

    if (this.sun_recharge > 1) {
        this.sun_recharge = 0;
        return new Soleil(25, new Point(this.hitbox.x, this.hitbox.y));
    }

    return null;
};
