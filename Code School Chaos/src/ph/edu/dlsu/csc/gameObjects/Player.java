/* © 2017 by Patrick Matthew Chan */
package ph.edu.dlsu.csc.gameObjects;
//import java.lang.reflect.Field;//optional,for toString shortcut

import acm.graphics.GCanvas;
import acm.graphics.GCompound;
import acm.graphics.GImage;
import acm.graphics.GOval;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import static ph.edu.dlsu.csc.mainprogram.cscConstants.APPLICATION_HEIGHT;
import static ph.edu.dlsu.csc.mainprogram.cscConstants.APPLICATION_WIDTH;

/* @author Patrick Matthew J. Chan [LBYCP12-EQ1]*/
public class Player{
    GImage charSprite=new GImage("csc_character.png");
    private volatile boolean isFiring=false;
    
    public Player(){//constructor
        ;
    }
    
    //other methods
    public void addToGCanvas(GCanvas gc){
        charSprite.setLocation((APPLICATION_WIDTH-charSprite.getWidth())/2.0D,
                APPLICATION_HEIGHT*3/4.0D-charSprite.getHeight()/2.0D);
        gc.add(charSprite);
        gc.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {}

            @Override
            public void mouseMoved(MouseEvent e) {
                charSprite.setLocation(e.getX()-charSprite.getWidth()/2.0D,
                e.getY()-charSprite.getHeight()/2.0D);
                System.out.println("e.getX() = " + e.getX());
                System.out.println("e.getY() = " + e.getY());
            }
        });
        gc.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar()==' '){
                    isFiring=true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyChar()==' '){
                    isFiring=false;
                }
            }
            
        });
        
        Thread bulThr=new Thread(new Runnable() {
            @Override
            public void run() {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }
    
    
    
    // <editor-fold defaultstate="collapsed" desc="toString shortcut">
    /*//++toString shortcut
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        for (Field f: getClass().getDeclaredFields()) {
            try {
            result
            .append(f.getName())
            .append(" : ")
            .append(f.get(this))
            .append(System.getProperty("line.separator"));
            }
            catch (IllegalStateException ise) {
                result
                .append(f.getName())
                .append(" : ")
                .append("[cannot retrieve value]")
                .append(System.getProperty("line.separator"));
            }
            // nope
            catch (IllegalAccessException iae) {}
        }
        return result.toString();
    }*/
    // </editor-fold>
}
