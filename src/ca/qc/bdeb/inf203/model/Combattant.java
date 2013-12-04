package ca.qc.bdeb.inf203.model;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe abstraite qui représente un combatant en jeu.
 *
 * @author Nicolas Hurtubise, Guillaume Riou
 */
public abstract class Combattant extends Entite implements Cloneable {

    /**
     * État du combattant
     */
    protected Etats etat;
    /**
     * Points de vie
     */
    protected int vie = 6;
    /**
     * Les combattants visés par l'attaque du combattant
     */
    protected ArrayList<Combattant> cibles;
    /**
     * L'équipement sur le combattant
     */
    protected Equipement[] equipement = new Equipement[0];
    /**
     * Compteur d'animation (resetté à chaque changement d'État)
     */
    protected int animationCompteur;
    /**
     * Nombre de frames par seconde dans l'animation
     */
    protected int animationFrameRate = 1;
    /**
     * 
     */
    protected Rectangle lineOfSight;
    protected HashMap<Etats, Integer> nbrImagesParActions;
    /**
     * Quantité de vie enlevée par une attaque.
     */
    protected int attaque;
    /**
     * Nombre de frames attendues pour attaquer une fois.
     */
    protected int attaqueRate;
    /**
     * Vitesse en pixels/sec
     */
    protected float vitesse;
    /**
     * Dernier timestamp calculé.
     */
    protected long dernierDeplacementTimestamp;
    protected long derniereAnimationTimestamp;
    protected float pendingDeplacement;
    protected boolean isGentil;

    public Combattant() {
        super();
        initialise();
    }

    protected void initialise() {
        this.dernierDeplacementTimestamp = System.currentTimeMillis();
        this.derniereAnimationTimestamp = System.currentTimeMillis();
        this.hitbox = new Rectangle();
        this.lineOfSight = new Rectangle();
        this.cibles = new ArrayList();
        this.etat = Etats.DEPLACEMENT;
    }
    
    protected boolean isEnnemi(Combattant combattant) {
        return combattant.isGentil != this.isGentil;
    }

    public Etats getEtat() {
        return etat;
    }

    public void setEtat(Etats etat) {
        this.animationCompteur = 0;
        this.derniereAnimationTimestamp = System.currentTimeMillis();
        this.etat = etat;
    }

    public int getVie() {
        return vie;
    }

    public void incrementVie(int qutee) {
        this.vie += qutee;
    }

    public void setVie(int vie) {
        this.vie = vie;
    }

    public Equipement[] getEquipement() {
        return equipement;
    }

    public void setEquipement(Equipement[] equipements) {
        this.equipement = equipements;
    }

    public int getAttaque() {
        return attaque;
    }

    public void setAttaque(int attaque) {
        this.attaque = attaque;
    }

    public float getVitesse() {
        return vitesse;
    }

    public void setVitesse(int vitesse) {
        this.vitesse = vitesse;
    }

    public int getAnimationCompteur() {
        long ts = System.currentTimeMillis();

        if (ts - this.derniereAnimationTimestamp >= (1000.0 / (float) animationFrameRate)) {
            // On incrémente l'animation
            animationCompteur++;
            derniereAnimationTimestamp = ts;
        }

        return animationCompteur % nbrImagesParActions.get(etat);
    }

    public HashMap<Etats, Integer> getNbrImagesParActions() {
        return nbrImagesParActions;
    }

    public int getAttaqueRate() {
        return attaqueRate;
    }

    public RepresentationImage getSprite() {
        return sprite;
    }

    public void setImgRep(RepresentationImage imgRep) {
        this.sprite = imgRep;
    }

    public ArrayList<Combattant> getCibles() {
        return cibles;
    }

    public void setCibles(ArrayList<Combattant> cibles) {
        this.cibles = cibles;
    }

    public Rectangle getLineOfSight() {
        return lineOfSight;
    }

    public void setLineOfSight(Rectangle lineOfSight) {
        this.lineOfSight = lineOfSight;
    }

    /**
     * @FIXME : La logique ne prends pas la vitesse du combatant en compte et
     * semble louche
     */
    public void deplacer() {
        long temps = System.currentTimeMillis();
        long deltaTemps = temps - this.dernierDeplacementTimestamp;

        // v = dx/dT => dx = v*dt
        float deltaX = (vitesse * (deltaTemps / 1000.0f));

        pendingDeplacement += deltaX;

        int deplacement = (int) pendingDeplacement;

        pendingDeplacement -= deplacement;

        this.hitbox.x += deplacement;
        this.lineOfSight.x += deplacement;

        this.dernierDeplacementTimestamp = temps;
    }

    public Combattant tic() {
        // effectuer l'action par rapport à l'état.
        switch (etat) {
            case ATTAQUE:
                attaquer();
                break;
            case DEPLACEMENT:
                deplacer();
                break;
        }

        return null;
    }

    /**
     * Si c'est le bon temps pour attaquer, enlever de la vie à toutes les
     * cibles et à leur équipement en fonction du l'attaque totale de l'entité
     * et de la défense totale des cibles.
     */
    protected void attaquer() {

        float modificateur = 0;
        for (Combattant cible : cibles) {
            for (int i = 0; i < this.equipement.length; i++) {
                modificateur += this.equipement[i].getAttaque();
            }
            for (int i = 0; i < cible.equipement.length; i++) {
                modificateur -= cible.equipement[i].getDefense();
            }
            int attaqueTotale = this.attaque - (int) (this.attaque * modificateur);
            cible.incrementVie(attaqueTotale);
            for (Equipement equipement : cible.equipement) {
                if (equipement.isEndommageable()) {
                    equipement.incrementVies(this.attaque);
                }
            }
            if (cible.getVie() <= 0) {
                cible = null;
            }
        }

        boolean tousNull = true;

        for (Combattant cible : cibles) {
            if (cible != null) {
                tousNull = false;
            }
        }

        if (tousNull) {
            this.etat = Etats.DEPLACEMENT;
        }
    }

    /**
     * Action à faire lorsque quelque chose est dans la ligne de vue.
     *
     * @return
     */
    public abstract Entite action();

    @Override
    protected Combattant clone() {
        try {
            Combattant clone = (Combattant) super.clone();

            clone.initialise();

            return clone;
        }
        catch (CloneNotSupportedException ex) {
            Logger.getLogger(Combattant.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
