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
import acm.util.RandomGenerator;
import ph.edu.dlsu.csc.mainprogram.cscConstants;
import ph.edu.dlsu.csc.myarraylistchan.MyArrayList;
import ph.edu.dlsu.csc.mylinkedlist.MyLinkedList;
import ph.edu.dlsu.csc.mystack.MyStack;

/* @author Patrick Matthew J. Chan [LBYCP12-EQ1]*/
public class ESpawner1 implements cscConstants{
    GCanvas gc;
    EBulletGen bulGen;
    Player pl;
    MyArrayList<EBehaved> curEne;
    int killCount=0;
    MyStack<EBehaved> waitEne;
    static int waitBeforeBoss=100;
    //rng
    RandomGenerator r=new RandomGenerator();
        
    public ESpawner1(GCanvas gc, Player p){//constructor
        this.gc=gc;
        bulGen=new EBulletGen(gc);
        curEne=new MyArrayList<>(100);
        killCount=0;
        waitEne=new MyStack<>(waitBeforeBoss+10);
        pl=p;
    }
    
    //other methods
    public void begin(){
        String[] a={MCHROME,MFIREFOX,MEXPLORER};
        for(int i=1;i<=waitBeforeBoss;i++){
            int gen=r.nextInt(0,2);
            GImage nowMinion=new GImage(a[gen]);
            MinionEntity me=new MinionEntity(gc,nowMinion);
            EBehaved eb=new EBehaved(gc, me, pl);
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
