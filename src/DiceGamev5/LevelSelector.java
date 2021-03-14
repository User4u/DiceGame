package DiceGamev5;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.ArrayList;

public class LevelSelector implements Serializable, KeyListener {

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


    private JPanel topPanel = new JPanel();

    private JButton moveButton = new JButton();
    private JButton saveButton = new JButton();
    private JButton inventoryButton = new JButton();
    private JButton exitButton = new JButton();

    //Level Area = 1 = Dessert    2 = Forest
    //Player Pos should be 6 - 11; 6 being the beggining and 11 being the end
    private int levelArea;
    private int playerPos;

    private int rowTopPanel = 7; //row = <--->
    protected int colTopPanel = 7; //col = up or down
    int borderFinder = rowTopPanel;
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

    //Use me to set up all the keys needed!
    //In the feature I hope to implement paint method to help smooth the animation!!!
    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int key = keyEvent.getKeyCode();
        if (key == KeyEvent.VK_UP) {

        }
        if (key == KeyEvent.VK_LEFT) {
            if (playerPos % 7 != 0) {
                //Move freely into you get into the castle tile
                System.out.println(playerPos);
                if (playerPos != castleLoc ) {
                    int newPlayerPos = getPlayerPos() - 1;
                    //drawMapArray[newPlayerPos].setIcon(playerIcon);
                    drawMapArray[newPlayerPos].setIcon(resizeIcon(playerIcon, topPanel.getWidth() / 5, topPanel.getHeight() / 5));
                    drawMapArray[getPlayerPos()].setIcon(resizeIcon(road, topPanel.getWidth() / 5, topPanel.getHeight() / 5));
                    //drawMapArray[getPlayerPos()].setIcon(road);
                    setPlayerPos(newPlayerPos);
                    System.out.println(playerPos);
                }
            }
        }

        //Pressing the right arrow key
        if (key == KeyEvent.VK_RIGHT) {
            if (isLevelUp == false) {
                //Move freely into you get into the castle tile
                if ( (playerPos != castleLoc)) {
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

        if (key == KeyEvent.VK_DOWN) {

        }
        //If I press Q closes the APP
        if (key == 81) {
            System.exit(0);
        }

        //Press the S button saves the game Hope to add animation to it sometime!
        if(key == 83){
            try {
                FileOutputStream fileIn = new FileOutputStream("Images/save.ser");
                ObjectOutputStream in = new ObjectOutputStream(fileIn);
                in.writeObject(this); //NEEDS TO IMPLEMENT SERIALIZABLE
                in.close();
                System.out.printf("Object saved!");
                //this = null;
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }


    //Runs all of the methods below
    public void createSwing() {
        //Set up the JFrame
        createFrame(levelFrame);
        //Set the top and bottomPanel
        createPanel(topPanel);
    }


    //Create the frame
    private void createFrame(JFrame frame) {
        frame.setSize(1000, 1000);
        frame.setLayout(new GridLayout(1, 1));
        frame.add(topPanel);
        frame.addKeyListener(this);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    //Creates the labels with two paramters
    private void createPanel(JPanel panel) {
        panel.setLayout(new GridLayout(rowTopPanel, colTopPanel));
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

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {

    }

}
