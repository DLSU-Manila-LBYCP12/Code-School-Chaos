/* © 2017 by Patrick Matthew Chan */
package ph.edu.dlsu.csc.mainprogram;
import acm.graphics.*;
import acm.io.*;
import acm.program.*;
import acm.util.*;
import java.io.*;
import java.applet.*;
import java.awt.event.*;
import java.awt.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
/* @author Patrick Matthew J. Chan [LBYCP12-EQ1]*/
public class LevelTrialPMC extends GraphicsProgram implements cscConstants{
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~ Main Classes ~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    //main classes only for testing
    public static void main(String[] args) {
        new LevelTrialPMC().start(args);
    }
    public void init(){
        setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
    }
    public void run(){
        MoveBG=true;
        PauseBG=false;
        Thread BGMover=startMoveBGThread();
        
    }
    //~~~~~~~~~~~~~~~~~~~~~~~~~ Debugging & Misc ~~~~~~~~~~~~~~~~~~~~~~~~~//
    // <editor-fold defaultstate="collapsed" desc="p(),pl(),pel()">
    public static void p(Object a){//debug
        System.out.print(a+"");
    }
    public static void pl(Object a){//debug
        System.out.println(a+"");
    }
    public static void pl(){//debug
        System.out.println();
    }
    public static void pel(){//debug
        System.err.println();
    }
    // </editor-fold>
    //application size  //alternative method is setSize(x,y)
    //public static final int APPLICATION_WIDTH = 400;
    //public static final int APPLICATION_HEIGHT = 650;
    
    
    
    //~~~~~~~~~~~~~~~~~~~~~~~~~ Global Variables ~~~~~~~~~~~~~~~~~~~~~~~~~//
    //bg img
    Image bgPic=new GImage("Classroom.png").getImage();
    int bgScrollDelay=20;
    volatile boolean isMovingBG=false;
    volatile boolean MoveBG=false;
    volatile boolean PauseBG=false;
    
    
    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ Methods ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    /**
     * (don't be intimidated by thread, you can simply 
     *  stop it by setting MoveBG to false)
     * @return Thread that moves BG in function call
     */
    synchronized Thread startMoveBGThread(){//can probably ignore the synchronized part, idk
        Thread bgMovement=new Thread(new Runnable() {
            @Override
            public void run() {
                if(!isMovingBG){//will only work if bg is not already moving
                    isMovingBG=true;
                    //init
                    GImage bg1=new GImage(bgPic);
                    GImage bg2=new GImage(bgPic);
                    GImage bg3=new GImage(bgPic);
                    bg1.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
                    bg2.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
                    bg3.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
                    add(bg1);
                    add(bg2,0,-bg1.getHeight());
                    add(bg3,0,bg2.getY()-bg2.getHeight());
                    //move
                    while(MoveBG){
                        //replace
                        if(bg1.getY()>APPLICATION_HEIGHT+10){
                            double y2=bg2.getY();
                            double y3=bg3.getY();
                            double y4=bg3.getY()-bg3.getHeight();
                            remove(bg1);
                            remove(bg2);
                            remove(bg3);
                            add(bg1,0,y2);
                            add(bg2,0,y3);
                            add(bg3,0,y4);
                        }
                        //move
                        bg1.move(0,1);
                        bg2.move(0,1);
                        bg3.move(0,1);
                        do{
                           pause(bgScrollDelay); 
                        }
                        //delay is repeated if you want pause
                        while(PauseBG && MoveBG);
                    }
                    isMovingBG=false;
                }
            }
        });
        bgMovement.setPriority(Thread.MIN_PRIORITY);
        bgMovement.start();
        return bgMovement;
    }
}