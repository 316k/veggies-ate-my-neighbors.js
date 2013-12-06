package ca.qc.bdeb.inf203.model;

/**
 * Représentation abstraite du sprite d'un model.
 *
 * @author Guillaume Riou, Nicolas Hurtubise
 */
public class RepresentationImage implements Cloneable {

    /**
     * RGB
     */
    private int[] colorisation;
    /**
     * Chemin à utiliser pour le blit de l'image. Chaque case est un step dans
     * la hiérarchie de fichier.
     */
    private String[] path;

    public void setColorisation(int[] colorisation) {
        this.colorisation = colorisation;
    }

    public void setPath(String[] path) {
        this.path = path;
    }

    /**
     * Constructeur
     *
     * @param colorisation
     * @param path
     */
    public RepresentationImage(int[] colorisation, String[] path) {
        this.colorisation = colorisation;
        this.path = path;
    }

    public RepresentationImage(RepresentationImage rep) {
        this.colorisation = rep.colorisation;
        this.path = rep.path;
    }

    public RepresentationImage(String[] path) {
        this.path = path;
        this.colorisation = null;
    }

    public int[] getColorisation() {
        return colorisation;
    }

    public String[] getPath() {
        return path;
    }

    @Override
    protected RepresentationImage clone() throws CloneNotSupportedException {
        return (RepresentationImage) super.clone();
    }
}
