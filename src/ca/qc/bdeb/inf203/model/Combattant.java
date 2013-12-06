package ca.qc.bdeb.inf203.model;

import ca.qc.bdeb.inf203.model.combatants.Projectile;
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
     * Points de vie
     */
    protected int vie = 6;
    /**
     * Les combattants visés par l'attaque du combattant
     */
    protected ArrayList<Combattant> cibles;
    /**
     * L'équipement sur le combattant
     *
     * @TODO éventuellement...
     */
    protected Equipement[] equipement = new Equipement[0];
    /**
     * Nombre de frames par seconde dans l'animation
     */
    protected int animationFrameRate = 1;
    /**
     *
     */
    protected Rectangle lineOfSight;
    protected HashMap<Etat, Integer> nbrImagesAnimation = new HashMap<>();
    /**
     * Quantité de vie enlevée par une attaque.
     */
    protected int attaque;
    /**
     * Vitesse en action/sec
     */
    protected HashMap<Action, Float> vitesseAction = new HashMap<>();

    public HashMap<Action, Float> getVitesseAction() {
        return vitesseAction;
    }

    public void setVitesseAction(HashMap<Action, Float> vitesseAction) {
        this.vitesseAction = vitesseAction;
    }
    protected HashMap<Action, Long> derniereActionTimestamp = new HashMap<>();
    protected HashMap<Action, Long> accumulateurAction = new HashMap<>();
    /**
     * Dernier timestamp calculé.
     */
    protected long derniereAnimationTimestamp;
    protected boolean gentil;

    public Combattant() {
        super();
        initialise();
    }

    protected void initialise() {
        this.derniereAnimationTimestamp = System.currentTimeMillis();

        this.accumulateurAction.put(Action.ACTION, 0l);
        this.accumulateurAction.put(Action.DEPLACEMENT, 0l);
        this.accumulateurAction.put(Action.ATTAQUE, 0l);

        this.derniereActionTimestamp.put(Action.ACTION, System.currentTimeMillis());
        this.derniereActionTimestamp.put(Action.DEPLACEMENT, System.currentTimeMillis());
        this.derniereActionTimestamp.put(Action.ATTAQUE, System.currentTimeMillis());

        this.hitbox = new Rectangle();
        this.lineOfSight = new Rectangle();
        this.cibles = new ArrayList();
        this.etat = Etat.DEPLACEMENT;

    }

    public boolean isEnnemi(Combattant combattant) {
        return combattant.gentil != this.gentil;
    }

    public boolean isGentil() {
        return gentil;
    }

    public void setGentil(boolean gentil) {
        this.gentil = gentil;
    }

    @Override
    public Etat getEtat() {
        return etat;
    }

    public void setEtat(Etat etat) {
        this.animationCompteur = 0;
        this.derniereAnimationTimestamp = System.currentTimeMillis();
        resetActions();
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

    @Override
    public int getAnimationCompteur() {
        long ts = System.currentTimeMillis();

        if (ts - this.derniereAnimationTimestamp >= (1000.0 / (float) animationFrameRate)) {
            // On incrémente l'animation
            animationCompteur++;
            derniereAnimationTimestamp = ts;
        }

        return animationCompteur % nbrImagesAnimation.get(etat);
    }

    public HashMap<Etat, Integer> getNbrImagesAnimation() {
        return nbrImagesAnimation;
    }

    public void multiplyStats(float multiplicateur) {
        this.vie *= multiplicateur;
        this.attaque *= multiplicateur;
        for (Action action : vitesseAction.keySet()) {
            vitesseAction.put(action, vitesseAction.get(action) * multiplicateur);
        }
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

    //retourne combien de fois faire une action selon l'action et sa vitesse.
    public int getNbrActions(Action action) {
        long temps = System.currentTimeMillis();
        long accumulateur = accumulateurAction.get(action);
        accumulateur += temps - derniereActionTimestamp.get(action);
        long tempsPourAction = (long) (1000 / vitesseAction.get(action));
        int nbrActions = (int) (accumulateur / tempsPourAction);
        accumulateur -= nbrActions * tempsPourAction;
        accumulateurAction.put(action, accumulateur);
        derniereActionTimestamp.put(action, temps);
        return nbrActions;
    }

    public void resetActions() {
        this.accumulateurAction.put(Action.ACTION, 0l);
        this.accumulateurAction.put(Action.DEPLACEMENT, 0l);
        this.accumulateurAction.put(Action.ATTAQUE, 0l);

        this.derniereActionTimestamp.put(Action.ACTION, System.currentTimeMillis());
        this.derniereActionTimestamp.put(Action.DEPLACEMENT, System.currentTimeMillis());
        this.derniereActionTimestamp.put(Action.ATTAQUE, System.currentTimeMillis());
    }

    public void deplacer(int deltaX) {
        this.hitbox.x += deltaX;
        this.lineOfSight.x += deltaX;
    }

    public Entite tic() {
        // effectuer l'action par rapport à l'état.
        switch (etat) {
            case ATTAQUE:
                attaquer(getNbrActions(Action.ATTAQUE));
                break;
            case DEPLACEMENT:
                deplacer(getNbrActions(Action.DEPLACEMENT));
                break;
        }

        return null;
    }

    /**
     * Logique d'attaque, les bouts commentés sont pour les équipements qui
     * seront ajoutés dans la version 2.0.
     */
    protected void attaquer(int nbFois) {

        for (int j = 0; j < nbFois; j++) {

            ArrayList<Entite> morts = new ArrayList<>();
            //float modificateur = 0;
            for (Combattant cible : cibles) {

//                for (int i = 0; i < this.equipement.length; i++) {
//                    modificateur += this.equipement[i].getAttaque();
//                }
//                for (int i = 0; i < cible.equipement.length; i++) {
//                    modificateur -= cible.equipement[i].getDefense();
//                }

                //int attaqueTotale = this.attaque - (int) (this.attaque * modificateur);

                cible.incrementVie(-this.attaque);
//                for (Equipement equipement : cible.equipement) {
//                    if (equipement.isEndommageable()) {
//                        equipement.incrementVies(-this.attaque);
//                    }
//                }
                if (cible.getVie() <= 0) {
                    morts.add(cible);
                }
            }
            this.cibles.removeAll(morts);

        }
        if (this.cibles.isEmpty()) {
            this.setEtat(Etat.DEPLACEMENT);
        }
    }

    /**
     * Action à faire lorsque quelque chose est dans la ligne de vue.
     *
     * @return
     */
    public abstract Entite action(int nbrFois);

    @Override
    protected Combattant clone() {
        try {
            Combattant clone = (Combattant) super.clone();
            clone.initialise();

            HashMap<Etat, RepresentationImage> sprites = new HashMap<>();

            for (Etat etat : this.sprites.keySet()) {
                sprites.put(etat, this.sprites.get(etat).clone());
            }

            clone.sprites = sprites;

            clone.vitesseAction = (HashMap<Action, Float>) vitesseAction.clone();
            clone.derniereActionTimestamp = (HashMap<Action, Long>) derniereActionTimestamp.clone();
            clone.accumulateurAction = (HashMap<Action, Long>) accumulateurAction.clone();

            return clone;
        } catch (CloneNotSupportedException ex) {
            Logger.getLogger(Combattant.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }
}
