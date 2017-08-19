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

/* @author Patrick Matthew J. Chan [LBYCP12-EQ1]*/
public class PBulletEntity implements cscConstants{
    public int dmg=1;
    public int dx=5;
    public int dy=-10;
    //the GImage it manipulates, and the gc
    public GCanvas gc;
    public GImage gcBullet;
    
    
    public PBulletEntity(GCanvas gc,GImage gcBullet,int bulletDmg,int xVel,int yVel){
        this.gcBullet=gcBullet;
        this.gc=gc;
        //bulletInfo
        dmg=bulletDmg;
        dx=xVel;
        dy=-Math.abs(yVel);
    }
        
    
    //other methods
    public void tick(){
        gcBullet.move(dx, dy);
    }
    
    public boolean isInBounds(){
        double x=gcBullet.getX();
        double y=gcBullet.getY();
        return !(x<-15||x>APPLICATION_WIDTH+15||
                y<-15||y>APPLICATION_HEIGHT+15);
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
