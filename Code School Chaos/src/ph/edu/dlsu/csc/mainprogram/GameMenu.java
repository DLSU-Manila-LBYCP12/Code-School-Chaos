package ph.edu.dlsu.csc.mainprogram;//toph, fix this

/**
 *
 * @author Chan, Kitane, Velasco
 */
import scrapped.Level1z;
import acm.graphics.*;
import acm.io.IODialog;
import acm.program.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;
import ph.edu.dlsu.csc.Trials.LevelTrial;

public class GameMenu extends GraphicsProgram implements cscConstants {

    private final cscButton playButton;
    private final cscButton upgradeButton;
    private final cscButton highScoresButton;
    private final cscButton tutorialButton;
    private final cscButton endlessModeButton;
    private final cscButton storyModeButton;
    private GImage background;
    private final GLabel code;
    private final GLabel school;
    private final GLabel chaos;
    private final GLabel teamBam;
    private final GLabel project;
    private final GLabel storyMode;
    private final GLabel upgradeWeapons;
    private final GLabel highScores;
    private final GLabel nameDisplay;
    private final GLabel tutorial;
    private final GRoundRect rect1;
    private final GRoundRect rect2;
    private GLabel label;

    private String playerName;

    private final int APPLICATION_WIDTH = 500;
    private final int APPLICATION_HEIGHT = 700;

    private final int BUTTON_LENGTH = 175;
    private final int BUTTON_HEIGHT = 50;

    public GameMenu() {
        playButton = new cscButton("Play Game!");
        upgradeButton = new cscButton("Upgrade Weapons");
        highScoresButton = new cscButton("High Scores");
        tutorialButton = new cscButton("Tutorials");
        endlessModeButton = new cscButton("Endless Mode");
        storyModeButton = new cscButton("Story Mode");
        background = new GImage("CSCBackground.png");
        background.setSize(cscConstants.APPLICATION_WIDTH+cscConstants.ACM_FRAME_OFFSET_X, cscConstants.APPLICATION_HEIGHT);
        teamBam = new GLabel("Team BAM Productions");
        teamBam.setFont("MONTSERRAT-20");
        teamBam.setColor(Color.white);
        project = new GLabel("A Project for LBYCP12");
        project.setFont("MONTSERRAT-20");
        project.setColor(Color.white);
        code = new GLabel("CODE");
        code.setColor(Color.white);
        code.setFont("COURIER NEW-BOLD-50");
        school = new GLabel("SCHOOL");
        school.setColor(Color.white);
        school.setFont("MONTSERRAT-45");
        chaos = new GLabel("CHAOS");
        chaos.setFont("SCORCHED EARTH-45");
        chaos.setColor(Color.white);
        storyMode = new GLabel("Play Game!");
        storyMode.setFont("MONTSERRAT-18");
        upgradeWeapons = new GLabel("Upgrade Weapons");
        upgradeWeapons.setFont("MONTSERRAT-18");
        highScores = new GLabel("High Scores");
        highScores.setFont("MONTSERRAT-18");
        nameDisplay = new GLabel("");
        nameDisplay.setFont("MONTSERRAT-15");
        tutorial = new GLabel("Tutorials");
        tutorial.setFont("MONTSERRAT-18");
        rect1 = new GRoundRect(200, 75);
        rect2 = new GRoundRect(225, 100);
        label = new GLabel("");
    }

    private void setupPlayerName() {
        IODialog dialog = getDialog();
        while (true) {
            String name = dialog.readLine("Enter your name!");
            if (!name.isEmpty()) {
                playerName = name;
                return;
            }
            dialog.println("Please enter a name.");
        }
    }

    private void intro() {//add sound effects for epicness
        this.setBackground(Color.black);
        pause(500);
        add(teamBam, APPLICATION_WIDTH - teamBam.getWidth()*2,
                APPLICATION_HEIGHT / 2 - teamBam.getHeight() / 2);
        pause(2000);
        remove(teamBam);
        add(project, APPLICATION_WIDTH - project.getWidth() * 2,
                APPLICATION_HEIGHT / 2 - project.getHeight() / 2);
        pause(1500);
        remove(project);
        pause(500);
        add(code, APPLICATION_WIDTH - code.getWidth() * 3,
                APPLICATION_HEIGHT / 2 - code.getHeight() / 2);
        pause(500);
        remove(code);
        add(school, APPLICATION_WIDTH - school.getWidth() * 2,
                APPLICATION_HEIGHT / 2 - school.getHeight() / 2);
        pause(500);
        remove(school);
        add(chaos, getWidth()/2 - chaos.getWidth()/2,
                APPLICATION_HEIGHT / 2 - chaos.getHeight() / 2);
        pause(500);
    }

    private void codeSchoolChaos() {
        add(code, 225, 100);
        add(school, 225, 175);
        add(chaos, 225, 250);
    }

    public void init() {
        intro();
        this.setBackground(Color.white);
        add(background);
        pause(250);
        code.setColor(Color.black);
        school.setColor(Color.black);
        chaos.setColor(Color.BLACK);
        codeSchoolChaos();
        pause(200);
        playButton.setCommand("play");
        add(playButton, getWidth()-playButton.getWidth(), 200);
        add(storyMode, playButton.getX() + playButton.getWidth() / 2 - storyMode.getWidth() / 2 - 2,
                playButton.getY() + playButton.getHeight() / 2 + storyMode.getAscent() / 2 - 3);//-2 & -3 for shadow effect
        upgradeButton.setCommand("upgrade");
        add(upgradeButton, playButton.getX(), 260);
        add(upgradeWeapons, upgradeButton.getX() + upgradeButton.getWidth() / 2 - upgradeWeapons.getWidth() / 2 - 2,
                upgradeButton.getY() + upgradeButton.getHeight() / 2 + upgradeWeapons.getAscent() / 2 - 3);
        highScoresButton.setCommand("highscore");
        add(highScoresButton, playButton.getX(), 320);
        add(highScores, highScoresButton.getX() + highScoresButton.getWidth() / 2 - highScores.getWidth() / 2 - 2,
                highScoresButton.getY() + highScoresButton.getHeight() / 2 + highScores.getAscent() / 2 - 3);
        tutorialButton.setCommand("tutorial");
        add(tutorialButton, playButton.getX(), 380);
        add(tutorial, tutorialButton.getX() + tutorialButton.getWidth() / 2 - tutorial.getWidth() / 2 - 2,
                tutorialButton.getY() + tutorialButton.getHeight() / 2 + tutorial.getAscent() / 2 - 3);
        add(code, getWidth()-code.getWidth(), 50);
        add(school, getWidth()-school.getWidth(), 100);
        add(chaos, getWidth()-chaos.getWidth(), 150);
        setupPlayerName();
        nameDisplay.setLabel("Welcome, " + playerName);
        add(nameDisplay, getWidth() - nameDisplay.getWidth()*(1.5D), APPLICATION_HEIGHT - 75);
        addMouseListeners();
    }

    public void run() {
        //remove menu bar:
        Frame[] fs = Frame.getFrames();
        for (Frame f : fs) {
            f.setMenuBar(null);
        }
    }

    private boolean isClickedPlayButton(double x, double y) {
        return (x >= playButton.getX() && x <= playButton.getX() + BUTTON_LENGTH
                && y >= playButton.getY() && y <= playButton.getY() + BUTTON_HEIGHT);
    }

    private boolean isClickedUpgradeButton(double x, double y) {
        return (x >= upgradeButton.getX() && x <= upgradeButton.getX() + BUTTON_LENGTH
                && y >= upgradeButton.getY() && y <= upgradeButton.getY() + BUTTON_HEIGHT);
    }

    private boolean isClickedScoreButton(double x, double y) {
        return (x >= highScoresButton.getX() && x <= highScoresButton.getX() + BUTTON_LENGTH
                && y >= highScoresButton.getY() && y <= highScoresButton.getY() + BUTTON_HEIGHT);
    }

    private boolean isClickedTutorialButton(double x, double y) {
        return (x >= highScoresButton.getX() && x <= highScoresButton.getX() + BUTTON_LENGTH
                && y >= highScoresButton.getY() && y <= highScoresButton.getY() + BUTTON_HEIGHT);
    }

    private boolean isClickedStoryButton(double x, double y) {
        return (x >= storyModeButton.getX() && x <= storyModeButton.getX() + BUTTON_LENGTH
                && y >= storyModeButton.getY() && y <= storyModeButton.getY() + BUTTON_HEIGHT);
    }

    private boolean isClickedEndlessButton(double x, double y) {
        return (x >= endlessModeButton.getX() && x <= endlessModeButton.getX() + BUTTON_LENGTH
                && y >= endlessModeButton.getY() && y <= endlessModeButton.getY() + BUTTON_HEIGHT);
    }

    public void mouseClicked(MouseEvent me) {
        if (isClickedPlayButton(me.getX(), me.getY())) {
            removeAll();
            add(background);
            codeSchoolChaos();
            levelSelection();
        } else if (isClickedUpgradeButton(me.getX(), me.getY())) {
            //call upgrade window
        } else if (isClickedScoreButton(me.getX(), me.getY())) {
            removeAll();
            new cscHighScore().checkHiScore(0);
        } else if (isClickedTutorialButton(me.getX(), me.getY())) {
            //call tutorial button
        } else if (isClickedStoryButton(me.getX(), me.getY()) && story) {
            System.out.println("clickedSto");
            displayMessage("Coming soon");
        } else if (isClickedEndlessButton(me.getX(), me.getY()) && endlessMode) {
            System.out.println("clickedEnd");
            removeAll();
            new LevelTrial().makeLevel(getGCanvas());
        }
    }

    private boolean endlessMode;
    private boolean story;

    private void levelSelection() {
        System.out.println("level select");
        GLabel endless = new GLabel("Endless Mode");
        endless.setFont("MONTSERRAT-18");
        GLabel storyMode = new GLabel("Story Mode");
        storyMode.setFont("MONTSERRAT-18");

        add(endlessModeButton, getWidth()-endlessModeButton.getWidth(), 300);
        endlessMode = true;
        add(endless, endlessModeButton.getX() + endlessModeButton.getWidth() / 2 - endless.getWidth() / 2 - 2,
                endlessModeButton.getY() + endlessModeButton.getHeight() / 2 + endless.getAscent() / 2 - 3);
        add(storyModeButton, endlessModeButton.getX(), 375);
        story = true;
        add(storyMode, storyModeButton.getX() + storyModeButton.getWidth() / 2 - storyMode.getWidth() / 2 - 2,
                storyModeButton.getY() + storyModeButton.getHeight() / 2 + storyMode.getAscent() / 2 - 3);
        //new Level1z().begin();
    }

    public void displayMessage(String message) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new JFrame("Note");
                frame.setResizable(false);
                frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                GCanvas canvas = new GCanvas();
                GLabel label = new GLabel(message);
                label.setFont("MONTSERRAT-18");
                canvas.add(label,200 / 2
                - label.getWidth() / 2, 50);
                frame.setContentPane(canvas);
                frame.setSize(200, 100);
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
            }
        });
        }

    public static void main(String[] args) {
        GameMenu menu=new GameMenu();
        menu.setPreferredSize(new Dimension(cscConstants.APPLICATION_WIDTH+cscConstants.ACM_FRAME_OFFSET_X,cscConstants.APPLICATION_HEIGHT));
        menu.setMaximumSize(new Dimension(cscConstants.APPLICATION_WIDTH+cscConstants.ACM_FRAME_OFFSET_X,cscConstants.APPLICATION_HEIGHT));
        menu.setMinimumSize(new Dimension(cscConstants.APPLICATION_WIDTH+cscConstants.ACM_FRAME_OFFSET_X,cscConstants.APPLICATION_HEIGHT));
        
        JFrame frame = new JFrame("Code School Chaos.exe");
        frame.add(menu);
        frame.pack();
        frame.setSize(new Dimension(cscConstants.APPLICATION_WIDTH+cscConstants.ACM_FRAME_OFFSET_X,
            cscConstants.APPLICATION_HEIGHT));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        
        menu.start(args);
    }
}
