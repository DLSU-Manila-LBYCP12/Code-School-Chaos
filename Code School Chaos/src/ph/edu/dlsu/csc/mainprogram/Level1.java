package ph.edu.dlsu.csc.mainprogram;

/**
 *
 * @author NeilOliver
 */
import acm.graphics.*;
import acm.program.*;

public class Level1 extends GraphicsProgram implements cscConstants {
    
    private GImage player;
    private GImage background;
    private GImage background2;
    private GRect rect = new GRect(50,50);
    
    public Level1(){
        background = new GImage("Classroom.png");
        background2 = new GImage("Classroom.png");
        player = new GImage("csc_character.png");
    }
    
    public void init(){
        this.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
        background.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
        background.sendBackward();
        background2.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
        background2.sendBackward();
        add(background);
        add(player,APPLICATION_WIDTH/2 - player.getWidth()/2 ,600);
    }
    
    private void move(){
        while(true){
            background.move(0, 1);
            pause(20);
            if(background.getY()>700){
                
            }
            if(background.getY()==1){
                add(background2,0,-background2.getHeight());
            }
            background2.move(0,1);
            pause(20);
        }
    }
    
    public void run(){
        move();
    }
    
    public static void main(String[] args){
        new Level1().start(args);
    }
}
