package ca.qc.bdeb.inf203.model;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Classe abstraite qui représente une entitée en jeu.
 *
 * @author Nicolas Hurtubise, Guillaume Riou
 */
public class Combattant implements Cloneable {

    protected Etats etat;
    protected int vie = 6;
    protected ArrayList<Combattant> cibles;
    protected Equipement[] equipement;
    protected Rectangle hitbox;
    protected int animationCompteur;
    protected Rectangle lineOfSight;
    protected int animation;
    protected HashMap<Etats, Integer> nbImagesParActions;
    /**
     * Quantité de vie enlevée par une attaque.
     */
    protected int attaque;
    /**
     * Nombre de frames attendues pour attaquer une fois.
     */
    protected int attaqueRate;
    /**
     * Nb de pix/sec 16 est la vitesse de base d'un veggie
     */
    protected float vitesse = -16;
    protected RepresentationImage sprite;
    protected long tempsImmobile;
    protected long tempsPourAvancer;
    protected int sensDeplacement;
    /**
     * Dernier timestamp calculé.
     */
    protected long dernierTimestamp;

    public Combattant() {
        initialise();
    }

    /**
     * Copy constructor
     * @param combatant combatant à copier
     */
    public Combattant(Combattant combatant) {
        this.hitbox = combatant.getHitbox();
        this.sprite = combatant.getSprite();
        this.attaque = combatant.getAttaque();
        this.nbImagesParActions = combatant.nbImagesParActions;
        this.vie = combatant.getVie();
        this.equipement = combatant.getEquipement();
        this.vitesse = combatant.getVitesse();
        this.attaqueRate = combatant.getAttaqueRate();
        initialise();
    }

    public final void initialise() {
        this.dernierTimestamp = System.currentTimeMillis();
        this.hitbox = new Rectangle();
        this.lineOfSight = new Rectangle();
        this.cibles = new ArrayList();
        this.tempsPourAvancer = Math.abs((long) (1 / vitesse * 1000));
        if (this.vitesse > 0) {
            sensDeplacement = 1;
        } else {
            sensDeplacement = -1;
        }
        this.etat = Etats.DEPLACEMENT;
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

    public int getAnimation() {
        return animation % nbImagesParActions.get(etat);
    }

    public void setAnimationCompteur(int animationCompteur) {
        this.animation = animationCompteur;
    }

    public RepresentationImage getImg() {
        return sprite;
    }

    public HashMap<Etats, Integer> getNbImagesParActions() {
        return nbImagesParActions;
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
        this.tempsImmobile += (temps - this.dernierTimestamp);
        int nbIncrementPos = (int) (this.tempsImmobile / this.tempsPourAvancer);
        for (int i = 0; i < nbIncrementPos; i++) {
            this.hitbox.x += sensDeplacement;
            this.tempsImmobile -= this.tempsPourAvancer;
        }
        this.dernierTimestamp = temps;
    }

    public Combattant tic() {
        // effectuer l'action par rapport à l'état.
        Combattant retour = null;
        switch (etat) {
            case ATTAQUE:
                attaquer();
                break;
            case DEPLACEMENT:
                deplacer();
                break;
        }
        this.animationCompteur++;
        return retour;
    }

    /**
     * Si c'est le bon temps pour attaquer, enlever de la vie à toutes les
     * cibles et à leur équipement en fonction du l'attaque totale de l'entité
     * et de la défense totale des cibles.
     */
    protected void attaquer() {

        if (attaqueRate % animation == 0) {
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

    }

    /**
     * Action à faire lorsque quelque chose est dans la ligne de vue.
     *
     * @return
     */
    public Combattant action() {
        return null;
    }

    @Override
    protected Combattant clone() {
        try {
            return (Combattant) super.clone(); //To change body of generated methods, choose Tools | Templates.
        }
        catch (CloneNotSupportedException ex) {
            Logger.getLogger(Combattant.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
