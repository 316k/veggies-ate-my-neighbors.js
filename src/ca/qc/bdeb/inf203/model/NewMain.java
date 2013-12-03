package ca.qc.bdeb.inf203.model;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 1029172
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Terrain t = new Terrain();
        
        while(true){
            t.tic();
            if(!t.getEntites().isEmpty()){
                System.out.println(t.getEntites().get(0).hitbox.x);
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
