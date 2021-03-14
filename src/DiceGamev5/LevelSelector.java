package DiceGamev5;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;

public class LevelSelector implements ActionListener, Serializable {

    //FIXME
    //Look into bufferImages
    //The image instance variables
    private ImageIcon topTile; // = new ImageIcon("dessertPic\\mountain.png");
    private ImageIcon bottomTile; // = new ImageIcon("dessertPic\\dessert.png");
    private ImageIcon road; // = new ImageIcon("dessertPic\\dessertTileWithRoad.png");
    private ImageIcon castleTile; // = new ImageIcon("dessertPic\\castleRoad.png");
    private ImageIcon playerIcon; // = new ImageIcon("dessertPic\\playerIcon.jpg");

    private Border border = BorderFactory.createLineBorder(Color.GREEN, 2);

    //Swing junk JFrame > JPanel > JButton
    private JFrame levelFrame = new JFrame();
    ;

    private JPanel topPanel = new JPanel();
    private JPanel bottomPanel = new JPanel();

    private JButton moveButton = new JButton();
    private JButton saveButton = new JButton();
    private JButton inventoryButton = new JButton();
    private JButton exitButton = new JButton();

    //Level Area = 1 = Dessert    2 = Forest
    //Player Pos should be 6 - 11; 6 being the beggining and 11 being the end
    private int levelArea;
    private int playerPos;
    private int rowTopPanel = 5; //row = <--->
    protected int colTopPanel = 6; //col = up or down
    private final int topPanelTotal = rowTopPanel * colTopPanel;
    private int castleLoc = colTopPanel + (colTopPanel - 1);

    //Since you are using a JLabel array type you need to give it a value
    //int[] array = new int[VALUE] else int[] array = {"test",....}
    private JLabel drawMapArray[] = new JLabel[topPanelTotal];

    //isTopPanel
    private boolean isLevelUp;

    LevelSelector() {

    }

    LevelSelector(int playerPos, int levelArea) {
        this.playerPos = playerPos;
        this.levelArea = levelArea;
        createSwing();
    }

    //        ImageIcon image = new ImageIcon("diceImg.png"); //Image creator
    //        label.setIcon(resizeIcon(image, introPanel.getWidth(), introPanel.getHeight()));

    //Prints out the top Panel
    public void arraryLabel() {
        for (int i = 0; i < topPanelTotal; i++) {
            //To get topPanelTotal Labels
            drawMapArray[i] = new JLabel();
            //drawMapArray[i].setBorder(border);

            //Draws the topTiles
            if (i < colTopPanel) {
                //drawMapArray[i].setIcon((topTile));
                drawMapArray[i].setIcon(resizeIcon(topTile, (topPanel.getWidth() / 5), topPanel.getHeight() / 5));

                //drawMapArray[i].setIcon(new ImageIcon("Images\\newAyaFear.jpg"));
                //resizeIcon(new ImageIcon("Images\\newAyaFear.jpg"), drawMapArray[i].getWidth(), drawMapArray[i].getHeight());
                //drawMapArray[i].setIcon(resizeIcon(new ImageIcon("Images\\newAyaFear.jpg"), drawMapArray[i].getWidth(), drawMapArray[i].getHeight()));
            }
            //Draw the castle tile
            else if (i == castleLoc) {
                //drawMapArray[i].setIcon(castleTile);
                drawMapArray[i].setIcon(resizeIcon(castleTile, topPanel.getWidth() / 5, topPanel.getHeight() / 5));
            }
            //Draws the roads before the castle tile
            else if (i < castleLoc) {
                //drawMapArray[i].setIcon(road);
                drawMapArray[i].setIcon(resizeIcon(road, topPanel.getWidth() / 5, topPanel.getHeight() / 5));
            }
            //Draws the bottomPart
            else {
                //drawMapArray[i].setIcon(bottomTile);
                drawMapArray[i].setIcon(resizeIcon(bottomTile, topPanel.getWidth() / 5, topPanel.getHeight() / 5));
            }
            //Adds the panel into the map
            topPanel.add(drawMapArray[i]);
        }
        //After everything is done, add the player position
        //drawMapArray[playerPos].setIcon(playerIcon);
        drawMapArray[playerPos].setIcon(resizeIcon(playerIcon, topPanel.getWidth() / 5, topPanel.getHeight() / 5));

    }


    //Gives the buttons purpose
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == moveButton) {

            //Makes it so you don't instatly transfer to the second area
            if (isLevelUp == false) {
                //Move freely into you get into the castle tile
                if (playerPos != castleLoc) {
                    int newPlayerPos = getPlayerPos() + 1;
                    //drawMapArray[newPlayerPos].setIcon(playerIcon);
                    drawMapArray[newPlayerPos].setIcon(resizeIcon(playerIcon, topPanel.getWidth() / 5, topPanel.getHeight() / 5));
                    drawMapArray[getPlayerPos()].setIcon(resizeIcon(road, topPanel.getWidth() / 5, topPanel.getHeight() / 5));
                    //drawMapArray[getPlayerPos()].setIcon(road);
                    setPlayerPos(newPlayerPos);
                }
            }
            if (playerPos == castleLoc) {
                //You can move pass the castleTile if the boolean is true
                if ((playerPos == castleLoc) && (isLevelUp == true)) {
                    setLevelArea(2);
                    setPlayerPos(colTopPanel);
                    levelFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                    levelFrame.dispose();
                    if (getLevelArea() == 2)
                        new GameEngine(getPlayerPos(), getLevelArea());
                }
                setLevelUp(true);
            }
        }

        //Inventory button actionListener
        if (e.getSource() == inventoryButton) {

        }

        //Save button actionListener
        if (e.getSource() == saveButton) {
            try {
                FileOutputStream fileIn = new FileOutputStream("DiceGame/Images/save.ser");
                ObjectOutputStream in = new ObjectOutputStream(fileIn);
                in.writeObject(this); //NEEDS TO IMPLEMENT SERIALIZABLE
                in.close();
                System.out.printf("Object saved!");
                //this = null;
            } catch (Exception ex) {
                ex.printStackTrace();
            }/*
            try{
                FileWriter saveData = new FileWriter("SaveFile.txt");
                //Write what's in the file line by line
                saveData.write(getPlayerPos() + "\n");
                saveData.write(getLevelArea() + "");

                saveData.close();
            }catch (IOException ioException) {
                ioException.printStackTrace();
            }*/
        }

        //Exit button actionListener
        if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }

    //Runs all of the methods below
    public void createSwing() {
        //Set up the JFrame
        createFrame(levelFrame);
        //Set the top and bottomPanel
        createPanel(topPanel, bottomPanel);

        //Create the buttons
        setButton(moveButton, "Move", bottomPanel);
        setButton(saveButton, "Save", bottomPanel);
        setButton(inventoryButton, "Inventory", bottomPanel);
        setButton(exitButton, "Exit", bottomPanel);
    }

    //Labels the buttons given
    private void setButton(JButton button, String setText, JPanel panel) {
        button.setText(setText);
        button.setFocusable(false);
        button.addActionListener(this);
        panel.add(button);
    }

    //Create the frame
    private void createFrame(JFrame frame) {
        frame.setSize(1000, 1000);
        frame.setLayout(new GridLayout(2, 1));
        frame.add(topPanel);
        frame.add(bottomPanel);
        frame.setVisible(true);
    }

    //Creates the labels with two paramters
    private void createPanel(JPanel panel, JPanel panelTwo) {
        panel.setLayout(new GridLayout(rowTopPanel, colTopPanel));
        panelTwo.setLayout(new GridLayout(2, 2));
        panelTwo.setBackground(Color.DARK_GRAY);
        panelTwo.setForeground(Color.CYAN);
    }

    //Resize the image
    public static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    //Accessors
    public int getPlayerPos() {
        return playerPos;
    }

    public boolean isLevelUp() {
        return isLevelUp;
    }

    public int getLevelArea() {
        return levelArea;
    }

    public JFrame getLevelFrame() {
        return levelFrame;
    }

    //Allows a different class to get colSize = playerPos
    public static int getColTopPanel() {
        LevelSelector col = new LevelSelector();
        return col.colTopPanel;
    }

    //Mutators


    public void setLevelArea(int levelArea) {
        this.levelArea = levelArea;
    }

    public void setLevelUp(boolean levelUp) {
        isLevelUp = levelUp;
    }

    public void setPlayerPos(int playerPos) {
        this.playerPos = playerPos;
    }

    public void setPlayerIcon(ImageIcon playerIcon) {
        this.playerIcon = playerIcon;
    }

    public void setTopTile(ImageIcon topTile) {
        this.topTile = topTile;
    }

    public void setRoad(ImageIcon road) {
        this.road = road;
    }

    public void setCastleTile(ImageIcon castleTile) {
        this.castleTile = castleTile;
    }

    public void setBottomTile(ImageIcon bottomTile) {
        this.bottomTile = bottomTile;
    }
}
