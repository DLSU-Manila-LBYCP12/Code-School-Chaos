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

/* @author Patrick Matthew J. Chan [LBYCP12-EQ1]*/
public class EBehaved {
    GCanvas gc;
    Player pl;
    int behaviorNo=0;
    MinionEntity m;//manipulated
    int rVel;
    
        
    public EBehaved(GCanvas gc,MinionEntity m,Player p){//constructor
        this.gc=gc;
        this.m=m;
        pl=p;
    }
    
    //other methods
    //0-drop
    //1-chase
    public void setBehavior(int i){
        behaviorNo=i;
    }
    
    public void setSpeed(int rVel){
        this.rVel=rVel;
    }
    
    public void tick(){
        switch(behaviorNo){
            case 1:
                m.chase(pl, rVel);
                break;
            default:
                m.move(0, rVel);
                break;
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
