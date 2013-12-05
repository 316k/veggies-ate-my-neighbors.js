package ca.qc.bdeb.inf203.model;

/**
 * Événement ayant eu lieu dans le terrain
 *
 * @author Nicolas Hurtubise, Guillaume Riou
 */
public enum TerrainEvent {

    /**
     * Une nouvelle vague de veggies a été lancée
     */
    NOUVELLE_VAGUE,
    /**
     * La vague actuelle est rendue à 50% et les veggies se coordonnent pour attaquer en même temps
     */
    MASSIVE_ATTACK,
    /**
     * Fin de la partie
     */
    GAME_OVER,
    /**
     * Rien
     */
    NULL;
}
