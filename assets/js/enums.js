/**
 * Énumération d'actions possibles pour un combatant
 * @author Nicolas Hurtubise, Guillaume Riou.
 */
function Action() {
    this.ATTAQUE = 0;
    this.DEPLACEMENT = 1;
    this.ACTION = 2;
}

navigator.Action = new Action();


/**
 * États possibles pour les combatants
 *
 * @author Guillaume Riou, Nicolas Hurtubise
 */
function Etat() {
    this.ATTAQUE = "attaque";
    this.DEPLACEMENT = "deplacement",
    this.ATTENTE = "attente";
}

navigator.Etat = new Etat();

function TerrainEvent() {
    /**
     * Une nouvelle vague de veggies a été lancée
     */
    this.NOUVELLE_VAGUE = 0,
    /**
     * La vague actuelle est rendue à 50% et les veggies se coordonnent pour attaquer en même temps
     */
    this.MASSIVE_ATTACK = 1,
    /**
     * Fin de la partie
     */
    this.GAME_OVER = 2,
    /**
     * Rien
     */
    this.NULL = 3;
}

navigator.TerrainEvent = new TerrainEvent();
