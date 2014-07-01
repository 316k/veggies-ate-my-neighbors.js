/**
 * Veggie Hitler
 *
 * @author Guillaume Riou, Nicolas Hurtubise
 */
function VeggieHitler() {
    Combattant.apply(this);
    this.combattant_init();
    this.init();
}

VeggieHitler.prototype = new Combattant();

VeggieHitler.prototype.init = function() {
    this.attaque = 1;
    this.gentil = false;
    this.hitbox.h = 80;
    this.hitbox.w = 60;
    this.animationFrameRate = 5;
    this.etat = navigator.Etat.DEPLACEMENT;

    this.lineOfSight = new Rectangle();
    this.lineOfSight = new Rectangle();

    this.vie = 1;

    this.nbrImagesAnimation[navigator.Etat.DEPLACEMENT] = 6;
    this.nbrImagesAnimation[navigator.Etat.ATTAQUE] = 1;

    this.vitesseAction[navigator.Action.DEPLACEMENT] = -70;
    this.vitesseAction[navigator.Action.ATTAQUE] = 0.6;
    this.vitesseAction[navigator.Action.ACTION] = 0.7;

    this.sprites[navigator.Etat.DEPLACEMENT] = new RepresentationImage(["veggies", "hitler", "walking"]);
    this.sprites[navigator.Etat.ATTAQUE] = new RepresentationImage(["veggies", "hitler", "walking"]);
};

VeggieHitler.prototype.tic = function() {
    var swas = null;
    switch (this.etat) {
        case navigator.Etat.DEPLACEMENT:
            this.deplacer(this.getNbrActions(navigator.Action.DEPLACEMENT));
        case navigator.Etat.ATTAQUE:
            swas = this.action(this.getNbrActions(navigator.Action.ACTION));
            break;
    }

    return swas;
};

/**
 * @return Entite une nouvelle Swastika.
 */
VeggieHitler.prototype.action = function(nbrFois) {
    if (nbrFois) {
        var swastika = new Swastika();
        swastika.hitbox.x = this.hitbox.x + 30;
        swastika.hitbox.y = this.hitbox.y + 30;

        swastika.gentil = this.gentil;

        var vitesses = {};

        vitesses[navigator.Action.DEPLACEMENT] = (this.gentil ? 1 : -1) * Math.abs(swastika.vitesseAction[navigator.Action.DEPLACEMENT]);
        vitesses[navigator.Action.ATTAQUE] = swastika.vitesseAction[navigator.Action.ATTAQUE];

        swastika.vitesseAction = vitesses;

        return swastika;
    }

    return null;
};
