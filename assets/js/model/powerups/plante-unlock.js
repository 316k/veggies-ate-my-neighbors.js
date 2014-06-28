/**
 * Power-up débloquant un type de plante.
 * @author Guillaume Riou, Nicolas Hurtubise
 */
function PlanteUnlock(item, position) {
    PowerUp.apply(this);

    this.item = item;
    this.largeur = 65;
    this.hauteur = 62;

    this.hitbox = new Rectangle(position.x, position.y, this.largeur, this.hauteur);
    this.animationFrameRate = 1;
    this.nbrImagesAnimation = 1;

    this.sprites[navigator.Etat.ATTENTE] = new RepresentationImage(["powerups", item.nom]);
}

PlanteUnlock.prototype = new PowerUp();

PlanteUnlock.prototype.action = function() {
    navigator.Joueur.debloquerItem(this.item);
};
