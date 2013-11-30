package ca.qc.bdeb.inf203.view;

import ca.qc.bdeb.inf203.controller.TerrainControlleur;
import ca.qc.bdeb.inf203.model.RepresentationImage;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JPanel;

/**
 *
 * @author Nicolas Hurtubise, Guillaume Riou
 */
public class JTerrain extends JPanel {

    private final int CASES_X = 10;
    private final int CASES_Y = 6;
    private final int TAILLE_CASE_X = 80;
    private final int TAILLE_CASE_Y = 80;
    private final int WIDTH = CASES_X * TAILLE_CASE_X;
    private final int HEIGHT = CASES_Y * TAILLE_CASE_Y;
    private SpriteContainer[][] background;
    private SpriteContainer panel;
    /**
     * Les coordonnées du point correspondent à un nombre de cases en x et y
     */
    private Point caseSelectionnee = new Point(-1, -1);

    public JTerrain() {
        this.setLayout(null);
        background = new SpriteContainer[CASES_X][CASES_Y];

        generateBackground();

        String path[] = {"panel"};
        panel = new SpriteContainer(new Point(0, 0), new RepresentationImage(path), 0);

        this.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
//                if (JoueurControlleur.is) {
//                    caseSelectionnee.x = ;
//                }
            }
        });

        this.setVisible(true);
    }

    protected void blitSpriteContainer(Graphics g, SpriteContainer sprite) {
        g.drawImage(SpriteManager.getSprite(sprite.getRepresentationImage(), sprite.getAnimation()),
                sprite.getCoordonnee().x, sprite.getCoordonnee().y, this);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Background
        for (SpriteContainer[] spriteRow : background) {
            for (SpriteContainer sprite : spriteRow) {
                if (sprite != null) {
                    blitSpriteContainer(g, sprite);
                }
            }
        }

        // Game Panel
        blitSpriteContainer(g, panel);

        // Game panel infos


        // Combatants
        SpriteContainer[] sprites = TerrainControlleur.getImages();

        for (SpriteContainer sprite : sprites) {
            blitSpriteContainer(g, sprite);
        }

        // Refresh
        try {
            Thread.sleep(100);
        }
        catch (InterruptedException ex) {
            Logger.getLogger(JTerrain.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.repaint();
    }

    private void generateBackground() {
        Random rnd = new Random();

        // Gazon
        for (int x = 0; x < CASES_X - 1; x++) {
            for (int y = 0; y < CASES_Y; y++) {
                Point position = new Point(x * TAILLE_CASE_X, y * TAILLE_CASE_Y);
                String path[] = {"background", "grass"};
                RepresentationImage ri = new RepresentationImage(null, path);
                background[x][y] = new SpriteContainer(position, ri, rnd.nextInt(4));
            }
        }

        // Route
        String path[] = {"background", "road"};
        RepresentationImage route = new RepresentationImage(null, path);
        background[CASES_X - 1][0] = new SpriteContainer(new Point((CASES_X - 1) * TAILLE_CASE_X, 0), route, 0);
    }
}
