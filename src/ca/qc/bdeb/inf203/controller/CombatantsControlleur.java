package ca.qc.bdeb.inf203.controller;

import ca.qc.bdeb.inf203.model.RepresentationImage;
import ca.qc.bdeb.inf203.view.SpriteContainer;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Nicolas Hurtubise
 */
public class CombatantsControlleur {

    /**
     *
     * Donne un SpriteContainer contenant l'information relative aux images et
     * positions des combatants en jeu
     *
     * @return Les informations relatives aux images des combatants en jeu
     */
    public static SpriteContainer[] getImages() {
        ArrayList<SpriteContainer> images = new ArrayList<>();

        int colorisation[] = {127, 127, 127};
        int animation = 0;
        String path[] = {"plants", "sunflower"};
        RepresentationImage ri = new RepresentationImage(colorisation, path);

        images.add(new SpriteContainer(new Point(100, 50), ri, animation));

        int colorisation2[] = {127, 127, 127};
        String path2[] = {"powerups", "sun"};
        RepresentationImage ri2 = new RepresentationImage(colorisation2, path2);

        images.add(new SpriteContainer(new Point(500, 300), ri2, ++animation));



        return images.toArray(new SpriteContainer[images.size()]);
    }
}
