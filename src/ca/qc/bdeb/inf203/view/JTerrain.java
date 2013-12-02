package ca.qc.bdeb.inf203.view;

import ca.qc.bdeb.inf203.controller.CombatantsControlleur;
import ca.qc.bdeb.inf203.controller.JoueurControlleur;
import ca.qc.bdeb.inf203.model.Item;
import ca.qc.bdeb.inf203.model.RepresentationImage;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseAdapter;
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
    private Font scoreFont = new Font("Purisa", Font.BOLD, 19);
    private Font infosFont = new Font("Purisa", Font.BOLD, 32);
    private Font titleFont = new Font("Purisa", Font.BOLD, 56);

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

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getY() > 1 * TAILLE_CASE_Y) {
                    // Le clic est dans le terrain
                    CombatantsControlleur.clic(e.getPoint());
                } else {
                    // Le clic est dans le panel
                    
                }
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

        // Soleils
        String[] path = {"panel", "box", "sun"};
        RepresentationImage ri = new RepresentationImage(path);
        SpriteContainer sprite = new SpriteContainer(new Point(TAILLE_CASE_X/2, TAILLE_CASE_Y/8), ri, 0);
        
        blitSpriteContainer(g, sprite);
        
        g.setColor(Color.black);
        g.setFont(scoreFont);
        g.drawString("" + JoueurControlleur.getSoleils(), 19*TAILLE_CASE_X/32, 13*TAILLE_CASE_Y/16);

        // Game panel items
        Item[] items = JoueurControlleur.getItems();
        Point position = new Point(2 * TAILLE_CASE_X, (int) (1 / 8.0 * TAILLE_CASE_Y));
        for (Item item : items) {
            String[] itemPath = {"panel", "box", item.getNom()};
            RepresentationImage itemRi = new RepresentationImage(itemPath);
            SpriteContainer itemSprite = new SpriteContainer(position, itemRi, 0);

            blitSpriteContainer(g, itemSprite);


            g.setColor(item.getRecharge() == 1 ? Color.green : Color.red);

            g.fillRect((int) (position.x + 2 / 16.0 * TAILLE_CASE_X), (int) (11 / 16.0 * TAILLE_CASE_Y), (int) (item.getRecharge() * 19 / 32.0 * TAILLE_CASE_X) - 1, (int) (1 / 8.0 * TAILLE_CASE_Y));

            position.move((int) (position.x + 65 / 64.0 * TAILLE_CASE_X), position.y);
        }
        
        // Infos de niveau et de 

        // Combatants
        SpriteContainer[] sprites = CombatantsControlleur.getImages();

        for (SpriteContainer combatantSprite : sprites) {
            blitSpriteContainer(g, combatantSprite);
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
