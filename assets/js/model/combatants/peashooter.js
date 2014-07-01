/**
 * Plante Tire-pois.
 *
 * @author Guillaume Riou, Nicolas Hurtubise
 */
function Peashooter() {
    Combattant.apply(this);
    this.combattant_init();
    this.init();
}

Peashooter.prototype = new Combattant();

Peashooter.prototype.init = function() {
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

    this.pea_recharge = 0;
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
Peashooter.prototype.action = function(times) {
    this.remove_dead_ennemies();

    if (!this.cibles.length) {
        this.setEtat(navigator.Etat.ATTENTE);
        return null;
    }

    this.pea_recharge += times;

    if (this.pea_recharge > 1) {
        this.pea_recharge = 0;

        var pea = new Pois();
        pea.hitbox.x = this.hitbox.x + 30;
        pea.hitbox.y = this.hitbox.y + 30;
        return pea;
    }

    return null;
};
