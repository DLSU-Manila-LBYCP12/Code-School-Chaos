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

    public cscButton(String text) {
        this.button = new GRoundRect(BUTTON_WIDTH, BUTTON_HEIGHT);
        this.button.setFilled(true);
        this.button.setColor(BDEF_COLOR);
        this.button.setFillColor(Color.white);
        this.buttonText = new GLabel("");
        this.buttonText.setFont(BFONT);
        this.buttonText.setColor(Color.black);
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

    public void mousePressed(MouseEvent me) {
        this.button.setFillColor(cscConstants.BCLICKED);
        this.buttonText.setColor(Color.white);
    }

    public void mouseReleased(MouseEvent me) {
    }

    public void mouseEntered(MouseEvent me) {
        this.button.setFillColor(cscConstants.BHOVERING);
    }

    public void mouseExited(MouseEvent me) {
        this.button.setFillColor(cscConstants.BDEF_COLOR);
    }

    public void mouseClicked(MouseEvent me) {
    }
}
