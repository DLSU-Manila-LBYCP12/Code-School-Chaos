/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.csc.mainprogram;

import acm.graphics.*;
import acm.program.*;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 *
 * @author ChristophJohnEric
 */
public class cscButton extends GCompound implements cscConstants, MouseListener {

    private GRoundRect button;
    private GLabel buttonText;
    private int command = 0;

    public cscButton(String text) {
        this.button = new GRoundRect(BUTTON_WIDTH, BUTTON_HEIGHT);
        this.button.setFilled(true);
        this.button.setColor(BDEF_COLOR);
        this.button.setFillColor(Color.white);
        this.buttonText = new GLabel("");
        this.buttonText.setFont(BFONT);
        this.buttonText.setColor(Color.white);
        add(this.button);
        add(this.buttonText);
        setLabel(text);
        addMouseListener(this);
    }

    public void setLabel(String text) {
        this.buttonText.setLabel(text);
        this.buttonText.setLocation((BUTTON_WIDTH - this.buttonText.getWidth()) / 2.0D,
                (BUTTON_HEIGHT + this.buttonText.getAscent()) / 2.0D - 1.0D);
    }
    
    public void setCommand(String command){
        if(command.equals("play")){
            this.command = 1;
        }
        else if(command.equals("upgrade")){
            this.command = 2;
        }
        else if(command.equals("highscore")){
            this.command = 3;
        }
        else if(command.equals("tutorial")){
            this.command = 4;
        }
    }

    public void mousePressed(MouseEvent me) {
        this.button.setFillColor(BCLICKED);
        this.buttonText.setColor(Color.white);
    }

    public void mouseReleased(MouseEvent me) {
        this.button.setFillColor(BDEF_COLOR);
    }

    public void mouseEntered(MouseEvent me) {
        this.button.setFillColor(cscConstants.BHOVERING);
    }

    public void mouseExited(MouseEvent me) {
        this.button.setFillColor(cscConstants.BDEF_COLOR);
    }

    public void mouseClicked(MouseEvent me){
        
    }
}
