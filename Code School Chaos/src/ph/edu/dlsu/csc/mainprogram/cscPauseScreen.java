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
import acm.program.*;
import static acm.util.JTFTools.pause;
import acm.util.MediaTools;
import java.applet.AudioClip;

/**
 *
 * @author ChristophJohnEric
 */
public class cscPauseScreen extends GCanvas implements cscConstants {

    private final GRect overlay;
    private final String CLICK1_FONT;
    private final String CLICK2_FONT;
    private final static int CLICK12_OFFSET = 5;
    private boolean isPaused = false;
    public boolean isMouseInScreen=true;
    //AudioClip bgm = MediaTools.loadAudioClip("bgm.au");

    public cscPauseScreen(){
        overlay = new GRect(cscConstants.APPLICATION_WIDTH, cscConstants.APPLICATION_HEIGHT);
        CLICK1_FONT = "Courier-30";
        CLICK2_FONT = "Courier-12";
    }
    private double ctrX(GObject g){
        return (cscConstants.APPLICATION_WIDTH-g.getWidth())/2;
    }
    private void screenPause() {
        this.add(overlay);
        //bgm.stop();
        GLabel click1 = new GLabel("- PAUSED -");
        GLabel click2 = new GLabel("Click Anywhere to Resume");
        click1.setFont(CLICK1_FONT);
        click1.setColor(Color.WHITE);
        click2.setFont(CLICK2_FONT);
        click2.setColor(Color.WHITE);
        add(click1, ctrX(click1), (HEIGHT - click2.getHeight() - CLICK12_OFFSET
                + click1.getHeight()) / 2);
        add(click2, ctrX(click2), (HEIGHT + click1.getHeight() + CLICK12_OFFSET
                + click2.getHeight()) / 2);
        isPaused = true;
        while (isPaused) {//waitforclick
            pause(0);
        }
        //waitForClick();
        isPaused = false;
        isMouseInScreen = true;
        remove(overlay);
        remove(click1);
        remove(click2);
        //bgm.loop();
    }

    private static boolean isAutoPause = true;

    public void pauseGame() {
        isMouseInScreen = false;
    }

    /**
     * @param flag if this is set to true, the game pauses once the mouse leaves
     * the play area. The game can also be paused using the pauseGame() method.
     */
    public void setAutoPause(boolean flag) {
        isAutoPause = flag;
    }
}
