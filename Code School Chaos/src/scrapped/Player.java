/* © 2017 by Patrick Matthew Chan */
package scrapped;
//import java.lang.reflect.Field;//optional,for toString shortcut

import acm.graphics.GCanvas;
import acm.graphics.GCompound;
import acm.graphics.GImage;
import acm.graphics.GOval;
import acm.util.JTFTools;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import ph.edu.dlsu.csc.mainprogram.cscConstants;
import static ph.edu.dlsu.csc.mainprogram.cscConstants.APPLICATION_HEIGHT;
import static ph.edu.dlsu.csc.mainprogram.cscConstants.APPLICATION_WIDTH;

/* @author Patrick Matthew J. Chan [LBYCP12-EQ1]*/
public class Player{
    GCanvas gc=null;
    GImage bullet=new GImage(cscConstants.UPGRADE0);
    int dmg=1;
    GImage charSprite=new GImage("csc_character.png");
    private volatile boolean isMousePressed=false;
    private volatile boolean isKeyPressed=false;
    private volatile boolean isDeconstructed=false;
    BulletManager bm=new BulletManager(gc);
    int bulletSpeedX=5;
    int bulletSpeedY=-10;
    
    public Player(GCanvas gc,BulletManager bm,GImage bulletGraphic, int bulletDamage, int bulletSpeedX, int bulletSpeedY){//constructor
        this.gc=gc;
        this.bm=bm;
        bullet=bulletGraphic;
        dmg=bulletDamage;
        this.bulletSpeedX=bulletSpeedX;
        this.bulletSpeedY=-Math.abs(bulletSpeedY);
    }
    public Player(GCanvas gc,BulletManager bm,GImage bulletGraphic, int bulletDamage){//constructor
        this.gc=gc;
        this.bm=bm;
        bullet=bulletGraphic;
        dmg=bulletDamage;
    }
    public Player(GCanvas gc,BulletManager bm){//constructor
        this.gc=gc;
        this.bm=bm;
    }
    
    //other methods
    public void addToGCanvas(){
        charSprite.setLocation((APPLICATION_WIDTH-charSprite.getWidth())/2.0D,
                APPLICATION_HEIGHT*3/4.0D-charSprite.getHeight()/2.0D);
        gc.add(charSprite);
        gc.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                charSprite.setLocation(e.getX()-charSprite.getWidth()/2.0D,
                e.getY()-charSprite.getHeight()/2.0D);
                isMousePressed=true;
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                charSprite.setLocation(e.getX()-charSprite.getWidth()/2.0D,
                e.getY()-charSprite.getHeight()/2.0D);
                isMousePressed=false;
                //System.out.println("e.getX() = " + e.getX());
                //System.out.println("e.getY() = " + e.getY());
            }
        });
        gc.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyChar()==' '){
                    isKeyPressed=true;
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyChar()==' '){
                    isKeyPressed=false;
                }
            }
            
        });
        
        Thread bulThr=new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("bulThr running");
                while(!isDeconstructed){
                    JTFTools.pause(5000);
                    if(isKeyPressed||isMousePressed){
                        //PlayerProjectile a=new PlayerProjectile(bullet,dmg);
                        double xCtr=charSprite.getX()+charSprite.getWidth()/2;
                        double yPos=charSprite.getY()-bullet.getHeight()/2-2;
                        //a.fireAt(gc, xCtr, yPos, bulletSpeedX, bulletSpeedY);
                        //bm.add(a);
                        /*System.out.println("bm.playerBullets.size() = " + bm.playerBullets.size());*/
                    }
                }
            }
        });
        //bulThr.setPriority(Thread.MIN_PRIORITY);
        bulThr.start();
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

    @Override
    protected void finalize() throws Throwable {
        isDeconstructed=true;
        super.finalize();
    }
}
