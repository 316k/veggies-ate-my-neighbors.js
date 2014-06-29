/**
 * Power-up conférant des unités solaires au joueur qui les rammasse.
 *
 * @author Guillaume Riou, Nicolas Hurtubise
 */
function Soleil(valeur, position, destination) {
    PowerUp.apply(this);
    /**
     * Nombre de soleils conférés par le clic sur le power-up
     */
    this.valeur = valeur;
    this.used = false;
    this.hitbox = new Rectangle(position.x, position.y, 40, 40);
    this.animationFrameRate = 12;
    this.nbrImagesAnimation = 4;
    this.vitesse = 60;
    this.destination = destination || position;

    this.sprites[navigator.Etat.ATTENTE] = new RepresentationImage(["powerups", "sun"]);
}

Soleil.prototype = new PowerUp();

/**
 * ajoute des soleils au joueur.
 */
Soleil.prototype.action = function() {
    navigator.Joueur.nbrSoleils += this.valeur;
    this.used = true;
};
