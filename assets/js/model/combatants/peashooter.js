/**
 * Plante Tire-pois.
 *
 * @author Guillaume Riou, Nicolas Hurtubise
 */
function Peashooter() {
    Combattant.apply(this);
    this.initialise();
}

Peashooter.prototype = new Combattant();

Peashooter.prototype.initialise = function() {
    this.vie = 10;
    this.gentil = true;
    this.hitbox.w = 80;
    this.hitbox.h = 80;
    this.lineOfSight.h = 80;
    this.lineOfSight.w = Terrain.TAILLE_CASE_X * Terrain.CASES_X;
    this.animationFrameRate = 6;
    this.nbrImagesAnimation = {};
    this.nbrImagesAnimation[navigator.Etat.ATTENTE] = 4;
    this.nbrImagesAnimation[navigator.Etat.ATTAQUE] = 11;
    this.etat = navigator.Etat.ATTENTE;

    this.vitesseAction[navigator.Action.ACTION] = 1;

    this.sprites[navigator.Etat.ATTENTE] = new RepresentationImage(["plants", "pea-shooter", "waiting"]);
    this.sprites[navigator.Etat.ATTAQUE] = new RepresentationImage(["plants", "pea-shooter", "shooting"]);
};

Peashooter.prototype.tic = function() {
    var pois = null;
    switch (this.etat) {
        case navigator.Etat.ATTAQUE:
            pois = this.action(this.getNbrActions(navigator.Action.ACTION));
            break;
    }

    return pois;
};

/**
 * L'action à distance d'un peashooter est de retourner un pois
 *
 * @return Entite
 */
Peashooter.prototype.action = function(nbFois) {
    var morts = [];

    for (var index in this.cibles) {
        var combattant = this.cibles[index];
        if (combattant.vie <= 0) {
            morts.push(combattant);
        }
    }

    this.cibles.remove_elements(morts);

    if (!this.cibles.length) {
        this.setEtat(navigator.Etat.ATTENTE);
        return null;
    }

    for (var i = 0; i < nbFois; i++) {
        var pois = new Pois();
        pois.hitbox.x = this.hitbox.x + 30;
        pois.hitbox.y = this.hitbox.y + 30;

        return pois;
    }

    return null;
};
