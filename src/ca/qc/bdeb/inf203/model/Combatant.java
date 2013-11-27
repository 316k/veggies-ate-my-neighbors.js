package ca.qc.bdeb.inf203.model;

import java.awt.Rectangle;
import java.util.ArrayList;



/**
 * Classe abstraite qui représente une entitée en jeu.
 *
 * @author Nicolas Hurtubise, Guillaume Riou
 */
public abstract class Combatant {


    private Etats etat;
    private int etat;
    private int vie;
    private ArrayList<Combatant> cibles;
    private Equipement[] equipements;
    private Rectangle hitbox;
    private int animationCompteur;
    private Rectangle lineOfSight;
    private int animation;
    /**
     * Quantité de vie enlevée par une attaque.
     */
    private int attaque;
    /**
     * Nb de pix/sec 16 est la vitesse de base d'un veggie
     */
    private float vitesse = -16;
    private RepresentationImage sprite;
    private long tempsImmobile;
    private long tempsPourAvancer;
    private int sensDeplacement;
    /**
     * Dernier timestamp calculé.
     */
    private long dernierTimestamp;

    public Combatant() {
        this.dernierTimestamp = System.currentTimeMillis();
        this.hitbox = new Rectangle();
        this.lineOfSight = new Rectangle();
        this.hitbox.height = 40;
        this.hitbox.width = 40;
        this.cibles = new ArrayList();
        this.tempsPourAvancer = (long) (1 / vitesse * 1000);
        if (this.vitesse > 0) {
            sensDeplacement = 1;
        } else {
            sensDeplacement = -1;
        }


    }

    public Rectangle getHitbox() {
        return hitbox;
    }


    public Etats getEtat() {
        return etat;
    }

    public void setEtat(Etats etat) {
        this.animationCompteur = 0;
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

    public Equipement[] getEquipements() {
        return equipements;
    }

    public void setEquipements(Equipement[] equipements) {
        this.equipements = equipements;
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
        return animation;
    }

    public void setAnimationCompteur(int animationCompteur) {
        this.animation = animationCompteur;
    }

    public RepresentationImage getImgRep() {
        return sprite;
    }

    public void setImgRep(RepresentationImage imgRep) {
        this.sprite = imgRep;
    }

    public ArrayList<Combatant> getCibles() {
        return cibles;
    }

    public void setCibles(ArrayList<Combatant> cibles) {
        this.cibles = cibles;
    }

    public Rectangle getLineOfSight() {
        return lineOfSight;
    }

    public void setLineOfSight(Rectangle lineOfSight) {
        this.lineOfSight = lineOfSight;
    }
    

    public void deplacer() {
        long temps = System.currentTimeMillis();
        this.tempsImmobile += temps - this.dernierTimestamp;
        int nbIncrementPos = (int) (this.tempsImmobile / this.tempsPourAvancer);
        for (int i = 0; i < nbIncrementPos; i++) {
            this.hitbox.x += sensDeplacement;
            this.tempsImmobile -= this.tempsPourAvancer;
        }
    }

    public void tic() {
        // effectuer l'action par rapport à l'état.
        switch (etat) {
            case ATTAQUE:
                attaquer();
                break;
            case DEPLACEMENT:
                deplacer();
                break;
        }
        this.animationCompteur++;

    }

    private void attaquer() {
        float modificateur = 0;
        for (Combatant cible : cibles) {
            for (int i = 0; i < this.equipements.length; i++) {
                modificateur += this.equipements[i].getAttaque();
            }
            for (int i = 0; i < cible.equipements.length; i++) {
                modificateur -= cible.equipements[i].getDefense();
            }
            int attaqueTotale = this.attaque - (int) (this.attaque * modificateur);
            cible.incrementVie(attaqueTotale);
            for (Equipement equipement : cible.equipements) {
                if (equipement.isEndommageable()) {
                    equipement.incrementVies(this.attaque);
                }
            }
            if (cible.getVie() <= 0) {
                cible = null;
            }
        }
        boolean tousNull = true;
        for (Combatant cible : cibles) {
            if(cible != null){
                tousNull = false;
            }
        }
        if(tousNull){
            this.etat = Etats.DEPLACEMENT;
        }
    }

    /**
     * Action à faire lorsque quelque chose est dans la ligne de vue.
     *
     * @return
     */
    public abstract Combatant action();

}
