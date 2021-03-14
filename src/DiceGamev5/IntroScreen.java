package DiceGamev5;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//The value one is sent over
public class IntroScreen implements ActionListener {

    //JFrame
    private JFrame introFrame = new JFrame("Dice Game v0.5");

    //JPanels
    JPanel topPanel = new JPanel();
    JPanel bottomPanel = new JPanel();

    //JLabel
    JLabel welcomeLabel = new JLabel();

    //JButtons
    private JButton start = new JButton();
    private JButton load = new JButton();
    private JButton update = new JButton();
    private JButton exit = new JButton();

    //Images
    ImageIcon welcomePic = new ImageIcon("Images/diceImg.png");

    //Variables
    private int playerPos = 6;
    private int playerStage;
    private int dataArray[] = {playerPos, playerStage};

    //Contructor
    IntroScreen() {
        this(1);
    }

    IntroScreen(int playerStage) {
        this.playerStage = playerStage;
        run();
    }

    //Creates the JFrame and places Panel, Labels, and Buttons
    public void run() {

        //Create the JFrame and set it up
        introFrame.setSize(1000, 1000);
        introFrame.setLayout(new GridLayout(2, 1));

        //Label to add the image to topPanel JPanel plus resize the image to the size!
        welcomeLabel.setIcon(resizeIcon(welcomePic, introFrame.getWidth(), introFrame.getHeight() / 2));

        setPanel(topPanel, Color.GREEN, true);
        setPanel(bottomPanel, Color.BLUE, false);

        //Add the three buttons in it!!
        setButton(start, "Start");
        setButton(load, "Load Game");
        setButton(update, "Update Log");
        setButton(exit, "Exit");

        //Shows the JFrame
        introFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        introFrame.setLocationRelativeTo(null);
        introFrame.setVisible(true);
    }

    //Sets the panels
    private void setPanel(JPanel panel, Color color, boolean isTopPanel) {
        if (isTopPanel == false)
            panel.setLayout(new GridLayout(4, 1));
        panel.setBackground(color);
        if (isTopPanel == true)
            panel.add(welcomeLabel);
        if (isTopPanel == true)
            introFrame.add(welcomeLabel);
        if (isTopPanel == false)
            introFrame.add(panel);
    }

    //Sets the buttons
    private void setButton(JButton button, String setText) {
        button.setText(setText);
        button.setFocusable(false);
        button.addActionListener(this);
        bottomPanel.add(button);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Features for
        // ing the exit button
        if (e.getSource() == exit) {
            System.exit(0);
        }

        //Clicking the start button will send you to the dessert level @ position 6
        if (e.getSource() == start) {
            closeFrame();
            new DessertLevel(LevelSelector.getColTopPanel(), 1);

        }

        //Clicking on the load button will read your playerPos and StageArea
        //DIDNT USE SER METHID
        //TODO MAKE IT LOAD WITH SER METHOD UNSERIALIZABLE
        if (e.getSource() == load) {
            try {
                File readSave = new File("SaveFile.txt");
                Scanner myReader = new Scanner(readSave);

                while (myReader.hasNextInt()) {
                    setPlayerPos(myReader.nextInt());
                    setPlayerStage(myReader.nextInt());
                }

                myReader.close();
                closeFrame();
            } catch (FileNotFoundException fileNotFoundException) {
                fileNotFoundException.printStackTrace();
            }

            if (getPlayerStage() == 1) {
                new DessertLevel(getPlayerPos(), getPlayerStage());
            } else if (getPlayerStage() == 2) {
                new ForestLevel(getPlayerPos(), getPlayerStage());
            } else if (getPlayerStage() == 3) {

            }
        }
    }

    //Closes the frame
    private void closeFrame() {
        introFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        introFrame.dispose();
    }

    //Resizes the image
    public static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    //Accessor
    public int getPlayerPos() {
        return playerPos;
    }

    public int getPlayerStage() {
        return playerStage;
    }

    //Mutators
    public void setPlayerPos(int playerPos) {
        this.playerPos = playerPos;
    }

    public void setPlayerStage(int playerStage) {
        this.playerStage = playerStage;
    }
}
