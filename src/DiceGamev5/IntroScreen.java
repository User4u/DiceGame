package DiceGamev5;

import jdk.jfr.EventSettings;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import DiceGamev5.Area.*;

//The value one is sent over
public class IntroScreen implements ActionListener {

    /*------------------------------------------------------------
    Clip
     -----------------------------------------------------------*/
    Clip clip;

    /*------------------------------------------------------------
    JFrame
     -----------------------------------------------------------*/
    private JFrame introFrame = new JFrame("Dice Game v0.5");

    /*------------------------------------------------------------
    JPanel
     -----------------------------------------------------------*/
    JPanel topPanel = new JPanel();
    JPanel bottomPanel = new JPanel();

    /*------------------------------------------------------------
    JLabel
     -----------------------------------------------------------*/
    JLabel welcomeLabel = new JLabel();

    /*------------------------------------------------------------
    JButtons
     -----------------------------------------------------------*/
    private JButton start = new JButton();
    private JButton load = new JButton();
    private JButton update = new JButton();
    private JButton exit = new JButton();

    /*------------------------------------------------------------
    Images
     -----------------------------------------------------------*/
    //ImageIcon welcomePic = new ImageIcon(String.valueOf(getClass().getResourceAsStream("Images/diceImg.png")));
    //ImageIcon welcomePic = new ImageIcon(getClass().getClassLoader().getResource("diceImg.png"));
    //ImageIcon welcomePic = new ImageIcon(getClass().getClassLoader().getResource("jack.png"));
    ImageIcon welcomePic = new ImageIcon("Images/jack.png");

    /*------------------------------------------------------------
    Variables
     -----------------------------------------------------------*/
    private int playerPos = 6;
    private String playerStage;



    //Contructor
    IntroScreen() {
        this("DESSERT");
    }

    //One paramater constructor and runs the 'run()' method
    private IntroScreen(String playerStage) {
        this.playerStage = playerStage;
        run();
    }

    /*------------------------------------------------------------
    Create the JFrame, places the panels, labels, and the buttons
     -----------------------------------------------------------*/
    private void run() {

        //Size of the JFrame
        introFrame.setSize(1000, 1000);
        //Layout define
        introFrame.setLayout(new GridLayout(2, 1));

        //Label to add the image to topPanel JPanel plus resize the image to the size!
        welcomeLabel.setIcon(resizeIcon(welcomePic, introFrame.getWidth(), introFrame.getHeight() / 2));

        //Not really needed but we will delete later
        setPanel(topPanel, Color.GREEN, true);
        setPanel(bottomPanel, Color.BLUE, false);

        //Add the three buttons in it!! Practice lambda's!
        setButton(start, "Start", event ->{
            if (event.getSource() == start) {
                clip.stop();
                closeFrame();
                //FIXME LevelSelector, DessertLevel, ForestLevel
                new DessertLevel(LevelSelector.getColTopPanel(), "DESSERT");
            }
        });
        setButton(load, "Load Game", event ->{
            load.setEnabled(false);
        });
        setButton(update, "Update Log", event ->{
            update.setEnabled(false);
        });
        setButton(exit, "Exit", event -> {
            if (event.getSource() == exit) {
                clip.stop();
                System.exit(0);
            }
        });

        //Audio that is played in the beginning of the program
        String soundName = "Images/qBGMq.wav";
        AudioInputStream audioInputStream;
        try {
            audioInputStream = AudioSystem.getAudioInputStream(new File(soundName).getAbsoluteFile());
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }

        //Displays the JFrame
        introFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        introFrame.setLocationRelativeTo(null);
        introFrame.setVisible(true);
    }

    /*------------------------------------------------------------
    Set the panels depending on what the argument is given
    -----------------------------------------------------------*/
    private void setPanel(JPanel panel, Color color, boolean isTopPanel) {
        if (isTopPanel == false) {
            panel.setLayout(new GridLayout(4, 1));
            panel.setBackground(color);
        }if (isTopPanel == true)
            panel.add(welcomeLabel);
        if (isTopPanel == true)
            introFrame.add(welcomeLabel);
        if (isTopPanel == false)
            introFrame.add(panel);
    }

    /*------------------------------------------------------------
    Set the buttons and give it meaning
     -----------------------------------------------------------*/
    private void setButton(JButton button, String setText, ActionListener event) {
        button.setText(setText);
        button.setFocusable(false);
        button.addActionListener(event);
        bottomPanel.add(button);
    }

    /*------------------------------------------------------------
     Not needed with Lambdas anymore -- WILL KEEP IN CASE CODE BREAKS MID WAY!
     -----------------------------------------------------------*/
    @Override
    public void actionPerformed(ActionEvent e) {
        /*Features for
        // ing the exit button
        //if (e.getSource() == exit) {
         //   clip.stop();
          //  System.exit(0);
        //}

        //Clicking the start button will send you to the dessert level @ position 6
        if (e.getSource() == start) {
            clip.stop();
            closeFrame();
            new DessertLevel(LevelSelector.getColTopPanel(), "DESSERT");

        }

        //Clicking on the load button will read your playerPos and StageArea
        //DIDNT USE SER METHID
        //TODO MAKE IT LOAD WITH SER METHOD UNSERIALIZABLE
        if (e.getSource() == load) {
            try {
                clip.stop();
                File readSave = new File(String.valueOf(getClass().getResource("SaveFile.txt")));
                Scanner myReader = new Scanner(readSave);

                while (myReader.hasNextInt()) {
                    setPlayerPos(myReader.nextInt());
                    setPlayerStage(myReader.nextLine());
                }

                myReader.close();
                closeFrame();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }

            if (getPlayerStage().equals("DESSERT")) {
                new DessertLevel(getPlayerPos(), getPlayerStage());
            } else if (getPlayerStage().equals("FOREST")) {
                new ForestLevel(getPlayerPos(), getPlayerStage());
            } else if (getPlayerStage().equals("HIDDEN")) {

            }
        }
        */
    }

    /*------------------------------------------------------------
    Closes the introFrame
    -----------------------------------------------------------*/
    private void closeFrame() {
        introFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        introFrame.dispose();
    }

    /*------------------------------------------------------------
    Allow us to resize the image to the correct size
    -----------------------------------------------------------*/
    public static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    //Accessor
    public int getPlayerPos() {
        return playerPos;
    }

    public String getPlayerStage() {
        return playerStage;
    }

    //Mutators
    public void setPlayerPos(int playerPos) {
        this.playerPos = playerPos;
    }

    public void setPlayerStage(String playerStage) {
        this.playerStage = playerStage;
    }
}
