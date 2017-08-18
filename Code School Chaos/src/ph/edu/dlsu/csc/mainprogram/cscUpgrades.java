/*
Copyright (c) Patrick Chan, Christoph Kitane, Neil Velasco.
Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:
The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.
THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package ph.edu.dlsu.csc.mainprogram;

import acm.graphics.*;
import java.awt.*;

/**
 *
 * @author Christoph John Eric S. Kitane
 */
public class cscUpgrades implements cscConstants {

    private int bulletDamage;
    private int numLives;
    private GImage bullet;
    
    public cscUpgrades(){
        bulletDamage = cscConstants.INITIAL_DAMAGE;
        numLives = cscConstants.INITIAL_LIVES;
        bullet = new GImage("bullet.png");
    }

    public int damageUpgrade(int upgradeLevel) {
        switch (upgradeLevel) {
            case 1:
                bulletDamage = bulletDamage + 1 * cscConstants.DAMAGE_INCREMENT;
                break;
            case 2:
                bulletDamage = bulletDamage + 2 * cscConstants.DAMAGE_INCREMENT;
                break;
            case 3:
                bulletDamage = bulletDamage + 3 * cscConstants.DAMAGE_INCREMENT;
                break;
            case 4:
                bulletDamage = bulletDamage + 4 * cscConstants.DAMAGE_INCREMENT;
                break;
            case 5:
                bulletDamage = bulletDamage + 5 * cscConstants.DAMAGE_INCREMENT;
                break;
            case 6:
                bulletDamage = bulletDamage + 6 * cscConstants.DAMAGE_INCREMENT;
                break;
            case 7:
                bulletDamage = bulletDamage + 7 * cscConstants.DAMAGE_INCREMENT;
                break;
            case 8:
                bulletDamage = bulletDamage + 8 * cscConstants.DAMAGE_INCREMENT;
                break;
            case 9:
                bulletDamage = bulletDamage + 9 * cscConstants.DAMAGE_INCREMENT;
                break;
            default:
                bulletDamage = cscConstants.INITIAL_DAMAGE;
                break;
        }
        return bulletDamage;
    }

    public GImage bulletImageUpgrade(int bulletImage) {
        switch (bulletImage) {
            case 1:
                bullet.setImage(cscConstants.UPGRADE1);
                break;
            case 2:
                bullet.setImage(cscConstants.UPGRADE2);
                break;
            case 3:
                bullet.setImage(cscConstants.UPGRADE3);
                break;
            case 4:
                bullet.setImage(cscConstants.UPGRADE4);
                break;
            case 5:
                bullet.setImage(cscConstants.UPGRADE5);
                break;
            case 6:
                bullet.setImage(cscConstants.UPGRADE6);
                break;
            case 7:
                bullet.setImage(cscConstants.UPGRADE7);
                break;
            case 8:
                bullet.setImage(cscConstants.UPGRADE8);
                break;
            case 9:
                bullet.setImage(cscConstants.UPGRADE9);
                break;
            default:
                bullet.setImage("bullet.png");
                break;
        }
        return bullet;
    }
    
    public int numberOfLives(int livesUpgrade){
        if(livesUpgrade==0){
            numLives = cscConstants.INITIAL_LIVES;
        }else{
            numLives = numLives + livesUpgrade;
        }
        return numLives;
    }

}