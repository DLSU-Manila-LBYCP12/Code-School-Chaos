/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.csc.mainprogram;

import acm.graphics.GImage;
import java.awt.Color;
import java.awt.Font;

/**
 *
 * @author Christoph Kitane
 */
public interface cscConstants {
    /* Application width and height of the Game. */
    public static final int APPLICATION_WIDTH = 400;
    public static final int APPLICATION_HEIGHT = 700;
    
    /* Sizes of the Buttons */
    public static final int BUTTON_WIDTH = 175;
    public static final int BUTTON_HEIGHT = 50;
    public static final Font BFONT = new Font("MONTSERRAT",0,18);
    public static final Color BCLICKED = Color.DARK_GRAY;
    public static final Color BDEF_COLOR = new Color(186, 206, 235);
    public static final Color BHOVERING = Color.WHITE;
    
    /* Upgrades Initialization */
    public static final int INITIAL_DAMAGE = 1;
    public static final int INITIAL_LIVES = 3;    
    public static final int DAMAGE_INCREMENT = 10;    
    public static final String UPGRADE0 = "bullet.png";    
    public static final String UPGRADE1 = "bullet_up1.png";    
    public static final String UPGRADE2 = "bullet_up2.png";    
    public static final String UPGRADE3 = "bullet_up3.png";    
    public static final String UPGRADE4 = "bullet_up4.png";    
    public static final String UPGRADE5 = "bullet_up5.png";    
    public static final String UPGRADE6 = "bullet_up6.png";    
    public static final String UPGRADE7 = "bullet_up7.png";    
    public static final String UPGRADE8 = "bullet_up8.png";    
    public static final String UPGRADE9 = "bullet_up9.png";
    public static final String MBULLET = "bullet_minions.png";
    public static final String BBULLET = "bullet_boss.png";
    public static final String BAPPLE = "csc_apple_boss.png";
    public static final String BANDROID = "csc_android_boss.png";
    public static final String BWINDOWS = "csc_windows_boss.png";
    public static final String BLINUX = "csc_linux_boss.png";
    public static final String MEXPLORER = "csc_explorer_minion.png";
    public static final String MCHROME = "csc_chrome_minion.png";
    public static final String MFIREFOX = "csc_firefox_minion.png";
    
    /*images*/
    public static final GImage PBULLET0 = new GImage(UPGRADE0);
    public static final GImage PBULLET1 = new GImage(UPGRADE1);
    public static final GImage PBULLET2 = new GImage(UPGRADE2);
    public static final GImage PBULLET3 = new GImage(UPGRADE3);
    public static final GImage PBULLET4 = new GImage(UPGRADE4);
    public static final GImage PBULLET5 = new GImage(UPGRADE5);
    public static final GImage PBULLET6 = new GImage(UPGRADE6);
    public static final GImage PBULLET7 = new GImage(UPGRADE7);
    public static final GImage PBULLET8 = new GImage(UPGRADE8);
    public static final GImage PBULLET9 = new GImage(UPGRADE9);
    public static final GImage IMGMBULLET = new GImage("bullet_minions.png");
    public static final GImage IMGBBULLET = new GImage("bullet_boss.png");
    public static final GImage IMGBAPPLE = new GImage("csc_apple_boss.png");
    public static final GImage IMGBANDROID = new GImage("csc_android_boss.png");
    public static final GImage IMGBWINDOWS = new GImage("csc_windows_boss.png");
    public static final GImage IMGBLINUX = new GImage("csc_linux_boss.png");
    public static final GImage IMGMEXPLORER = new GImage("csc_explorer_minion.png");
    public static final GImage IMGMCHROME = new GImage("csc_chrome_minion.png");
    public static final GImage IMGMFIREFOX = new GImage("csc_firefox_minion.png");
    
    /* ACM Frame */
    public static final int ACM_FRAME_OFFSET_X = 16;
    public static final int ACM_FRAME_OFFSET_Y = 100;
    
    /*Bullets..?*/
    public static final int BULLET_DELAY=20;
    
    /* Offsets */
    public static final int LVLSELECT_OFFSET_X = 25;
    
}
