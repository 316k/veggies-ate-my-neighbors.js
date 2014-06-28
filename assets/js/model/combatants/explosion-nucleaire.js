/**
 * Explosion nucléaire qui tue tout.
 *
 * @author Nicolas Hurtubise
 */
function ExplosionNucleaire() {
    Entite.apply(this);
}

ExplosionNucleaire.prototype = new Entite();

ExplosionNucleaire.prototype.getAnimationCompteur = function() {
    return 0;
};

ExplosionNucleaire.prototype.getEtat = function() {
    return navigator.Etat.ATTAQUE;
};
