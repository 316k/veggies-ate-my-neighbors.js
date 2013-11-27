package ca.qc.bdeb.inf203.controller;

import ca.qc.bdeb.inf203.model.RepresentationImage;
import ca.qc.bdeb.inf203.view.SpriteContainer;
import java.awt.Point;
import java.util.ArrayList;

/**
 *
 * @author Nicolas Hurtubise
 */
public class CombatantsControlleur {

    /**
     * Donne un SpriteContainer contenant l'information relative aux images et
     * positions des combatants en jeu
     *
     * @return Les informations relatives aux images des combatants en jeu
     */
    public static SpriteContainer[] getImages() {
        ArrayList<SpriteContainer> images = new ArrayList<>();

        int animation = (int) (System.currentTimeMillis() * 0.006 % 10);
        String path[] = {"plants", "pea-shooter", "shooting"};
        RepresentationImage ri = new RepresentationImage(path);

        images.add(new SpriteContainer(new Point(100, 50), ri, animation));

        animation = (int) (System.currentTimeMillis() * 0.012 % 4);
        String path2[] = {"powerups", "sun"};
        RepresentationImage ri2 = new RepresentationImage(path2);

        images.add(new SpriteContainer(new Point(500, 300), ri2, animation));

        animation = (int) (System.currentTimeMillis() * 0.012 % 8);
        String path3[] = {"plants", "pea"};
        RepresentationImage ri3 = new RepresentationImage(path3);

        images.add(new SpriteContainer(new Point(125 + (int) (System.currentTimeMillis() * 0.1 % 700), 70), ri3, animation));

        return images.toArray(new SpriteContainer[images.size()]);
    }
}
