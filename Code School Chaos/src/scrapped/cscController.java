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
import java.util.*;
import acm.graphics.*;
import java.awt.*;
/**
 *
 * @author ChristophJohnEric
 */
public class cscController {
    private LinkedList<PlayerProjectile> b = new LinkedList<>();
    
    PlayerProjectile TempProjectile;
    
    public void tick(){
        for(int i=0; i<b.size();i++){
            TempProjectile = b.get(i);
            
            TempProjectile.tick();
        }
    }
    
    public void draw(Graphics g){
        for(int i=0; i<b.size();i++){
            TempProjectile = b.get(i);
            
            TempProjectile.draw(g);
        }
    }
    
    public void addProjectile(PlayerProjectile block){
        b.add(block);
    }
    
    public void removeProjectile(PlayerProjectile block){
        b.add(block);
    }
    
}
