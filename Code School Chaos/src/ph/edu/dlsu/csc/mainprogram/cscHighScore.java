/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ph.edu.dlsu.csc.mainprogram;

import acm.program.*;
import acm.graphics.*;
import static acm.util.JTFTools.pause;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import ph.edu.dlsu.csc.myarraylist.MyList;

/**
 *
 * @author ChristophJohnEric
 */
public class cscHighScore extends GCanvas implements cscConstants {

    private static final int HI_SCORES = 5;
    private MyList<String> names = new MyList<String>();
    private MyList<Integer> scores = new MyList<Integer>();
    private boolean hasInput = false;
    private final static String YAY_FONT = "SansSerif-30";
    private final static int YAY_Y_OFFSET = 10;
    private GLabel yay = new GLabel("NEW HIGH SCORE!");
    private GLabel hiScoreDisp = new GLabel("High  Scores");
    private final static int HI_SCORES_Y_OFFSET = 90;
    private final static int HI_SCORES_SPACING = 10;
    private final static String HI_SCORE_FONT = "SansSerif-20";
    private GLabel HSNames[] = new GLabel[HI_SCORES + 1];//lets just make [0] do nothing
    private GLabel HSScores[] = new GLabel[HI_SCORES + 1];//lets just make [0] do nothing
    private final static int NAMES_X_OFFSET = 20;
    private final static int SCORES_X_OFFSET = cscConstants.APPLICATION_WIDTH - 20;
    private final String CLICK_FONT = "SansSerif-25";
    private final int CLICK_Y_OFFSET = 110;
    private String name = "";

    private double ctrX(GObject g) {
        return (cscConstants.APPLICATION_WIDTH - g.getWidth()) / 2;
    }

    public cscHighScore(int score) {
        names.createList();
        scores.createList();
        String filePath = new File("").getAbsolutePath();
        FileReader in;
        BufferedReader br = null;
        try {
            in = new FileReader(filePath + "/Scores.dat");
            br = new BufferedReader(in);
        } catch (IOException e) {
            File a = new File(filePath + "/Scores.dat");
            try {
                a.createNewFile();
                in = new FileReader(filePath + "/Scores.dat");
                br = new BufferedReader(in);
            } catch (IOException ee) {
                System.err.println("FILE READ ERROR\n");
                ee.printStackTrace();
            }
        }
        MyList<String> buff = new MyList<>();
        buff.createList();
        String a = "";
        try {
            a = br.readLine();
        } catch (IOException e) {
            System.err.println("File read error");
            e.printStackTrace();
        }
        while (a != null) {
            try {
                buff.add(buff.size() + 1, a);
                a = br.readLine();
            } catch (IOException e) {
                System.err.println("File read error");
                e.printStackTrace();
            }
        }
        for (int i = 1; i <= buff.size() / 2; i++) {
            names.add(i, buff.get(i));
        }
        for (int i = 1; i <= buff.size() / 2; i++) {
            try {
                scores.add(i, Integer.parseInt(buff.get(buff.size() / 2 + i)));
            } catch (NumberFormatException e) {
                System.err.println(buff.get(i) + "parse int error");
                scores.add(i, 0);
            }
        }
    }

    private void checkHiScore(int score) {
        int size = scores.size();
        if (size == 0) {
            enterName(1, score);
            //return;
        } else {
            int i = size;
            for (; i > 0 && score > scores.get(i); i--) {//"outputs" index of higher score                
            }
            enterName(i + 1, score);
        }
    }

    public void keyPressed(KeyEvent e) {
        if (hasInput) {
            char a = e.getKeyChar();
            //System.out.println(a);
            if (name == "<Please Type Your Name>") {
                name = "";
            }
            if (a == '\n' || a == '\r') {
                hasInput = false;
            } else if (a == '\b' && !name.isEmpty()) {
                name = new String(name.substring(0, name.length() - 1));
            } else if (name.length() == 20) {
                name = new String(name.substring(0, 19) + a);
            } else if (a >= 32 && a <= 126) {
                name = new String(name + a);
            }
        }
    }

    private void enterName(int index, int score) {//index is where to insert entry
        yay.setVisible(false);
        yay.setFont(YAY_FONT);
        yay.setColor(new Color(0, 237, 0));
        add(yay, ctrX(yay), YAY_Y_OFFSET + yay.getHeight());
        hiScoreDisp.setFont(HI_SCORE_FONT);
        double fontSizeY = hiScoreDisp.getHeight();
        double curY = HI_SCORES_Y_OFFSET + fontSizeY;//current Y        
        add(hiScoreDisp, ctrX(hiScoreDisp), curY);
        curY += HI_SCORES_SPACING + fontSizeY;
        for (int i = 1; i <= names.size(); i++) {
            if (i < index) {
                HSNames[i] = new GLabel(i + ". " + names.get(i));
                HSScores[i] = new GLabel(scores.get(i) + "");
                HSNames[i].setFont(HI_SCORE_FONT);
                HSScores[i].setFont(HI_SCORE_FONT);
                add(HSNames[i], NAMES_X_OFFSET, curY);
                add(HSScores[i], SCORES_X_OFFSET - HSScores[i].getWidth(), curY);
            } else {
                HSNames[i] = new GLabel((i + 1) + ". " + names.get(i));
                HSScores[i] = new GLabel(scores.get(i) + "");
                HSNames[i].setFont(HI_SCORE_FONT);
                HSScores[i].setFont(HI_SCORE_FONT);
                add(HSNames[i], NAMES_X_OFFSET, curY);
                add(HSScores[i], SCORES_X_OFFSET - HSScores[i].getWidth(), curY);
            }

            curY += HI_SCORES_SPACING + fontSizeY;
        }
        if (index <= HI_SCORES) {
            yay.setVisible(true);
            for (int i = names.size(); i >= index; i--) {
                curY = HSNames[i].getY();
                for (int j = 0; j <= HI_SCORES_SPACING + fontSizeY; j++) {
                    HSNames[i].move(0, 1);
                    HSScores[i].move(0, 1);
                    pause(1);
                }
            }
            name = "<Please Type Your Name>";
            GLabel newName = new GLabel(index + ". " + name);
            //gameNo++;
            GLabel newScore = new GLabel(score + "");
            newName.setFont(HI_SCORE_FONT);
            newScore.setFont(HI_SCORE_FONT);
            add(newScore, -newScore.getWidth(), curY);
            add(newName, NAMES_X_OFFSET - SCORES_X_OFFSET, curY);
            newName.setColor(new Color(206, 206, 0));
            newScore.setColor(new Color(206, 206, 0));
            for (int i = 0; i <= SCORES_X_OFFSET; i++) {
                newName.move(1, 0);
                newScore.move(1, 0);
                pause(1);
            }
            //entry name
            hasInput = true;
            while (hasInput) {
                double x = newName.getX();
                double y = newName.getY();
                newName.setLabel(index + ". " + name);
                remove(newName);
                add(newName, x, y);
                pause(10);
            }
            //done input
            names.add(index, name);
            scores.add(index, score);
            if (names.size() > HI_SCORES) {
                names.remove(6);
                scores.remove(6);
                while (HSNames[5].getX() < WIDTH) {
                    HSNames[5].move(1, 0);
                    HSScores[5].move(1, 0);
                    pause(1);
                }
                remove(HSNames[5]);
                remove(HSScores[5]);
            }
        }

        //HS File Overwrite
        String filePath = new File("").getAbsolutePath();
        FileWriter out;
        BufferedWriter bw = null;
        try {
            out = new FileWriter(filePath + "/Scores.dat");
            bw = new BufferedWriter(out);
        } catch (IOException e) {
            System.err.println("FILE READ ERROR\n");
            e.printStackTrace();
        }
        MyList<String> buff = new MyList<>();
        buff.createList();
        for (int i = 1; i <= names.size(); i++) {
            buff.add(i, names.get(i));
        }
        for (int i = 1; i <= scores.size(); i++) {
            buff.add(buff.size() + 1, scores.get(i).toString());
        }
        for (int i = 1; i <= buff.size(); i++) {
            try {
                bw.append(buff.get(i));
                bw.newLine();
            } catch (IOException e) {
                System.out.println("File Write Error");
                e.printStackTrace();
            }
        }
        try {//necessary to update file
            bw.flush();
            bw.close();
        } catch (IOException e) {
            ;
        }
    }

}
