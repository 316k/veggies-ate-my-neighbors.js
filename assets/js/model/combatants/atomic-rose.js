/**
 * Rose nucléaire qui tue tout sur le terrain.
 *
 * @author Guillaume Riou, Nicolas Hurtubise
 */
function AtomicRose() {
    Combattant.apply(this);
    this.combattant_init();
    this.init();
}
AtomicRose.prototype = new Combattant();

AtomicRose.prototype.init = function() {
    this.vie = 30;
    this.gentil = true;
    this.hitbox.w = 80;
    this.hitbox.h = 80;
    this.lineOfSight.x = 0;
    this.lineOfSight.y = 0;
    this.lineOfSight.w = Terrain.TAILLE_CASE_X * Terrain.CASES_X;
    this.lineOfSight.h = Terrain.TAILLE_CASE_Y * Terrain.CASES_Y;

    this.animationFrameRate = 6;

    this.nbrImagesAnimation = {};
    this.nbrImagesAnimation[navigator.Etat.ATTENTE] = 1;
    this.nbrImagesAnimation[navigator.Etat.ATTAQUE] = 11;
    this.etat = navigator.Etat.ATTENTE;

    this.vitesseAction[navigator.Action.ATTAQUE] = 1/3;
    this.vitesseAction[navigator.Action.ACTION] = 1/3;

    this.sprites[navigator.Etat.ATTENTE] = new RepresentationImage(["plants", "atomic-rose"]);
    this.sprites[navigator.Etat.ATTAQUE] = new RepresentationImage(["explosion"]);
};

AtomicRose.prototype.tic = function() {
    var entite = null;

    switch (this.etat) {
        case navigator.Etat.ATTAQUE:
            entite = this.action(this.getNbrActions(navigator.Action.ATTAQUE));
            break;
    }

    return entite;
};

/**
 * @param nbFois si c'est 0, pas d'explosion, sinon, explose.
 * @return Entite une explosion.
 */
AtomicRose.prototype.action = function(times) {

    if (times > 0) {
        return new ExplosionNucleaire();
    }

    return null;
};
