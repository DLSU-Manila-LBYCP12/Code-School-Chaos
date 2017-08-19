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
package scrapped;
//import java.lang.reflect.Field;//optional,for toString shortcut

import acm.graphics.GCanvas;
import acm.graphics.GImage;
import acm.graphics.GOval;
import acm.util.JTFTools;
import java.awt.Color;
import static ph.edu.dlsu.csc.mainprogram.cscConstants.APPLICATION_HEIGHT;
import static ph.edu.dlsu.csc.mainprogram.cscConstants.APPLICATION_WIDTH;
import ph.edu.dlsu.csc.myarraylistchan.MyArrayList;

/* @author Patrick Matthew J. Chan [LBYCP12-EQ1]*/
public class BulletManager {
    MyArrayList<PlayerProjectile> playerBullets=null;
    volatile boolean startThread=false;
    GCanvas gc=null;
    //anotha one for enemy's    
    
    public BulletManager(GCanvas gc){//constructor
        playerBullets=new MyArrayList<>(1000);
        this.gc=gc;
    }
    
    //other methods
    public void add(PlayerProjectile p){
        playerBullets.add(p);
    }
    //another set for enemy's
    
    
    
    
    /*public void updateAll(){
    for(int i=1;i<=playerBullets.size();){
    if(!playerBullets.get(i).isActive()){
    playerBullets.remove(i);
    } else {
    i++;
    }
    playerBullets.get(i).updatePos(gc);
    GImage p=playerBullets.get(i).bullet;
    /*GOval o=new GOval(p.getX(),p.getY(),p.getWidth(),p.getHeight());
    o.setColor(Color.RED);
    gc.add(o);
}
System.out.println("updating...");
JTFTools.pause(500);
//another set for enemy's
}
*/
    synchronized Thread startBMThread(){//can probably ignore the synchronized part, idk
        startThread=true;
        Thread bmThr=new Thread(new Runnable() {
            @Override
            public void run() {
                while(startThread){
                    JTFTools.pause(1000);
                    if(!playerBullets.isEmpty()){
//                        updateAll();
                    }
                }
            }
        });
        bmThr.setPriority(Thread.MIN_PRIORITY);
        bmThr.start();
        return bmThr;
    }
    
    void stopBMThread(){
        startThread=false;
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
