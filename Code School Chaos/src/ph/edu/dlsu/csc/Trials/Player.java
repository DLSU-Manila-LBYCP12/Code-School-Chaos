/* © 2017 by Patrick Matthew Chan */
package ph.edu.dlsu.csc.Trials;
//import java.lang.reflect.Field;//optional,for toString shortcut

import scrapped.BulletManager;
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
import static ph.edu.dlsu.csc.mainprogram.cscConstants.BULLET_DELAY;

/* @author Patrick Matthew J. Chan [LBYCP12-EQ1]*/
public class Player implements cscConstants{
    //other
    GCanvas gc=null;
    GImage charSprite=new GImage("csc_character.png");
    //bullet
    GImage bullet=new GImage(cscConstants.UPGRADE6);
    int dmg=1;
    BulletManager bm=new BulletManager(gc);
    int bulletSpeedX=0;
    int bulletSpeedY=-10;
    //bulletGen
    volatile PBulletGen bulletGen=new PBulletGen(gc);
    //thread
    private volatile boolean isMousePressed=false;
    private volatile boolean isKeyPressed=false;
    private volatile boolean isDeconstructed=false;
    
    
    public Player(GCanvas gc){
        this.gc=gc;//default bullet
    }
    
    public void setBullet(String upgradeNo,int bulletDmg,int xVel,int yVel){
        bullet=new GImage(upgradeNo);
        dmg=bulletDmg;
        bulletSpeedX=xVel;
        bulletSpeedY=-Math.abs(yVel);
        //gen
        bulletGen.setBullet(bullet, dmg, bulletSpeedX, bulletSpeedY);
    }
    
    
    //other methods
    /**
     * @return bullet shooting thread
     */
    public synchronized Thread addToGCanvas(){
        //the character
        charSprite.setLocation((APPLICATION_WIDTH-charSprite.getWidth())/2.0D,
                APPLICATION_HEIGHT*3/4.0D-charSprite.getHeight()/2.0D);
        gc.add(charSprite);
        gc.addMouseMotionListener(new MouseMotionListener() {
            @Override
            public void mouseDragged(MouseEvent e) {
                double newX=e.getX()-charSprite.getWidth()/2.0D;
                double newY=e.getY()-charSprite.getHeight()/2.0D;
                if(newX<-10){
                    newX=-10;
                } else if (newX+charSprite.getWidth()>APPLICATION_WIDTH+10){
                    newX=APPLICATION_WIDTH-charSprite.getWidth()+10;
                }
                if(newY<-10){
                    newY=-10;
                } else if(newY+charSprite.getHeight()>gc.getHeight()+10){
                    newY=gc.getHeight()-charSprite.getHeight()+10;
                }
                charSprite.setLocation(newX,newY);
                isMousePressed=true;
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                double newX=e.getX()-charSprite.getWidth()/2.0D;
                double newY=e.getY()-charSprite.getHeight()/2.0D;
                if(newX<-10){
                    newX=-10;
                } else if (newX+charSprite.getWidth()>APPLICATION_WIDTH+10){
                    newX=APPLICATION_WIDTH-charSprite.getWidth()+10;
                }
                if(newY<-10){
                    newY=-10;
                } else if(newY+charSprite.getHeight()>gc.getHeight()+10){
                    newY=gc.getHeight()-charSprite.getHeight()+10;
                }
                charSprite.setLocation(newX,newY);
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
        
        //bullets
        bulletGen.setBullet(bullet, dmg, bulletSpeedX, bulletSpeedY);
        Thread bulThr=new Thread(new Runnable() {
            @Override
            public void run() {
                while(!isDeconstructed){
                    JTFTools.pause(BULLET_DELAY);
                    bulletGen.tick();
                    //System.out.println("tick1");
                    JTFTools.pause(BULLET_DELAY);
                    bulletGen.tick();
                    //System.out.println("tick2");
                    if(isKeyPressed || isMousePressed){
                        bulletGen.setGC(gc);//this cannot be removed
                        bulletGen.drawBullet(charSprite.getX()+charSprite.getWidth()/2.0D
                                , charSprite.getY());
                        //System.out.println("bulletdrawn");
                    }
                }
            }
        });
        bulThr.setPriority(Thread.MIN_PRIORITY);
        bulThr.start();
        return bulThr;
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
