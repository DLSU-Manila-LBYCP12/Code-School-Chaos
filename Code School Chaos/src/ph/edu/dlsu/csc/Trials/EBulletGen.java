/*
 * Copyright © Patrick Chan, Christoph Kitane, Neil Velasco.
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package ph.edu.dlsu.csc.Trials;
//import java.lang.reflect.Field;//optional,for toString shortcut

import acm.graphics.GCanvas;
import acm.graphics.GImage;
import ph.edu.dlsu.csc.mainprogram.cscConstants;
import static ph.edu.dlsu.csc.mainprogram.cscConstants.UPGRADE0;
import ph.edu.dlsu.csc.mylinkedlist.MyLinkedList;

/* @author Patrick Matthew J. Chan [LBYCP12-EQ1]*/
public class EBulletGen implements cscConstants {
    //defaults
    GImage bullet=new GImage(MBULLET);
    int dmg=1;
    int bulletSpeedX=0;
    int bulletSpeedY=-10;
    //list
    MyLinkedList<EBulletEntity> bulletList;
    //gc
    GCanvas gc=new GCanvas();
    
        
    public EBulletGen(GCanvas gc){//constructor
        this.gc=gc;
        bulletList=new MyLinkedList<EBulletEntity>();
    }
    
    public void setGC(GCanvas gc){
        this.gc=gc;
    }
    
    public void setBullet(String upgradeNo,int bulletDmg,int xVel,int yVel){
        bullet=new GImage(upgradeNo);
        dmg=bulletDmg;
        bulletSpeedX=xVel;
        bulletSpeedY=-Math.abs(yVel);
    }
    public void setBullet(GImage bullSprite,int bulletDmg,int xVel,int yVel){
        bullet=bullSprite;
        dmg=bulletDmg;
        bulletSpeedX=xVel;
        bulletSpeedY=-Math.abs(yVel);
    }
    
    private GImage newBulGImg(){
        return new GImage(bullet.getImage());
    }
    
    public synchronized void drawBullet(double xCtr,double yCtr){
        GImage newBull=newBulGImg();
        newBull.setLocation(xCtr-newBull.getWidth()/2,
                yCtr-newBull.getHeight()/2);
        gc.add(newBull);
        EBulletEntity newEntity=new EBulletEntity(gc, 
                newBull, dmg, bulletSpeedX, bulletSpeedY);
        bulletList.add(newEntity);
    }
    
    public void tick(){
        if(!bulletList.isEmpty()){
            for(int i=1;i<=bulletList.size();){
                EBulletEntity now=bulletList.get(i);
                if(now.isInBounds()){
                    now.tick();
                    i++;
                } else {
                    gc.remove(bulletList.get(i).gcBullet);
                    bulletList.remove(i);
                }
            }
        }
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
