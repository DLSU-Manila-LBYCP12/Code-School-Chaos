package ph.edu.dlsu.csc.mainprogram;

/**
 *
 * @author Chan, Kitane, Velasco
 */
import acm.graphics.*;
import acm.io.IODialog;
import acm.program.*;
import java.awt.Color;
import java.awt.event.MouseEvent;

public class CodeSchoolChaos extends GraphicsProgram {

    private final cscButton playButton;
    private final cscButton upgradeButton;
    private final cscButton highScoresButton;
    private final cscButton tutorialButton;
    private GRoundRect exitButton;
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
    
    private String playerName;
    
    private final int APPLICATION_WIDTH = 500;
    private final int APPLICATION_HEIGHT = 700;

    private final int BUTTON_LENGTH = 175;
    private final int BUTTON_HEIGHT = 50;

    public CodeSchoolChaos() {
        playButton = new cscButton("Story Mode");
        upgradeButton = new cscButton("Upgrade Weapons");
        highScoresButton = new cscButton("High Scores");
        tutorialButton = new cscButton("Tutorials");
        background = new GImage("CSCBackground.png");
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
        storyMode = new GLabel("Story Mode");
        storyMode.setFont("MONTSERRAT-18");
        upgradeWeapons = new GLabel("Upgrade Weapons");
        upgradeWeapons.setFont("MONTSERRAT-18");
        highScores = new GLabel("High Scores");
        highScores.setFont("MONTSERRAT-18");
        nameDisplay = new GLabel("");
        nameDisplay.setFont("MONTSERRAT-15");
        tutorial = new GLabel("Tutorials");
        tutorial.setFont("MONTSERRAT-18");
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

    private void intro() {
        this.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
        this.setBackground(Color.black);
        pause(2000);
        add(teamBam, APPLICATION_WIDTH / 2 - teamBam.getWidth() / 2,
                APPLICATION_HEIGHT / 2 - teamBam.getHeight() / 2);
        pause(2000);
        remove(teamBam);
        add(project, APPLICATION_WIDTH / 2 - project.getWidth() / 2,
                APPLICATION_HEIGHT / 2 - project.getHeight() / 2);
        pause(2000);
        remove(project);
        add(code, APPLICATION_WIDTH / 2 - code.getWidth() / 2,
                APPLICATION_HEIGHT / 2 - code.getHeight() / 2);
        pause(500);
        remove(code);
        add(school, APPLICATION_WIDTH / 2 - school.getWidth() / 2,
                APPLICATION_HEIGHT / 2 - school.getHeight() / 2);
        pause(500);
        remove(school);
        add(chaos, APPLICATION_WIDTH / 2 - chaos.getWidth() / 2,
                APPLICATION_HEIGHT / 2 - chaos.getHeight() / 2);
        pause(500);
    }
    
    
    
    public void init() {
        intro();
        this.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
        this.setBackground(Color.white);
        add(background);
        pause(250);
        code.setColor(Color.black);
        school.setColor(Color.black);
        chaos.setColor(Color.BLACK);
        add(code, 225, 100);
        add(school, 225, 175);
        add(chaos, 225, 250);
        pause(200);
        playButton.setCommand("play");
        add(playButton, 275, 200);
        add(storyMode, playButton.getX() + playButton.getWidth() / 2 - storyMode.getWidth() / 2,
                playButton.getY() + playButton.getHeight() / 2 + storyMode.getHeight() / 2);
        upgradeButton.setCommand("upgrade");
        add(upgradeButton, 275, 260);
        add(upgradeWeapons, upgradeButton.getX() + upgradeButton.getWidth() / 2 - upgradeWeapons.getWidth() / 2,
                upgradeButton.getY() + upgradeButton.getHeight() / 2 + upgradeWeapons.getHeight() / 2);
        highScoresButton.setCommand("highscore");
        add(highScoresButton, 275, 320);
        add(highScores, highScoresButton.getX() + highScoresButton.getWidth() / 2 - highScores.getWidth() / 2,
                highScoresButton.getY() + highScoresButton.getHeight() / 2 + highScores.getHeight() / 2);
        tutorialButton.setCommand("tutorial");
        add(tutorialButton, 275, 380);
        add(tutorial, tutorialButton.getX() + tutorialButton.getWidth() / 2 - tutorial.getWidth() / 2,
                tutorialButton.getY() + tutorialButton.getHeight() / 2 + tutorial.getHeight() / 2);
        add(code, 225, 50);
        add(school, 225, 100);
        add(chaos, 225, 150);
        setupPlayerName();
        this.setSize(APPLICATION_WIDTH, APPLICATION_HEIGHT);
        add(nameDisplay, APPLICATION_WIDTH / 2 - APPLICATION_WIDTH / 4
                - nameDisplay.getWidth() / 2, APPLICATION_HEIGHT - 75);
        nameDisplay.setLabel("Welcome, " + playerName);
        addMouseListeners();
    }   
    
    public static void main(String[] args) {
        new CodeSchoolChaos().start(args);
    }
}
