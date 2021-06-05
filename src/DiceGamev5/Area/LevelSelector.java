package DiceGamev5.Area;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class LevelSelector implements Serializable, KeyListener {

    /*------------------------------------------------------------
    ImageIcons
    -----------------------------------------------------------*/
    private ImageIcon topTile; // = new ImageIcon("dessertPic\\mountain.png");
    private ImageIcon bottomTile; // = new ImageIcon("dessertPic\\dessert.png");
    private ImageIcon road; // = new ImageIcon("dessertPic\\dessertTileWithRoad.png");
    private ImageIcon castleTile; // = new ImageIcon("dessertPic\\castleRoad.png");
    private ImageIcon playerIcon; // = new ImageIcon("dessertPic\\playerIcon.jpg");

    /*------------------------------------------------------------
    JFrame > JPanel > JButton
    -----------------------------------------------------------*/
    private JFrame levelFrame = new JFrame();

    private JPanel topPanel = new JPanel();
    //private JPanel botPanel = new JPanel();

    /*------------------------------------------------------------
    Level Area = 1 = Dessert    2 = Forest
    Player Pos should be 6 - 11; 6 being the beginning and 11 being the end
    -----------------------------------------------------------*/
    //Level Code (DESSERT,FORREST) -- position of the player
    private String levelArea;
    private int playerPos;

    //row = <--->
    private int rowTopPanel = 6;
    //col = up or down ^/ \/
    protected int colTopPanel = 6;
    int borderFinder = rowTopPanel;
    private final int topPanelTotal = rowTopPanel * colTopPanel;
    private int castleLoc = colTopPanel + (colTopPanel - 1);

    //Since you are using a JLabel array type you need to give it a value
    //int[] array = new int[VALUE] else int[] array = {"test",....}
    private JLabel drawMapArray[] = new JLabel[topPanelTotal];

    //isTopPanel
    private boolean isLevelUp;

    LevelSelector(int playerPos, String levelArea) {
        this.playerPos = playerPos;
        this.levelArea = levelArea;
        createSwing();
    }

    public LevelSelector() {

    }


    /*------------------------------------------------------------
    Print out the top panel
    -----------------------------------------------------------*/
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

    /*------------------------------------------------------------
    The program always listens to our keyboard
    Lambdas will make this too messy >_<
    -----------------------------------------------------------*/
    @Override
    public void keyPressed(KeyEvent keyEvent) {
        int key = keyEvent.getKeyCode();
        if (key == KeyEvent.VK_UP) {
            if (playerPos + 1 % borderFinder != 0) {
                //Move freely into you get into the castle tile
                System.out.println(playerPos);
                if (playerPos != castleLoc ) {
                    try {
                        int newPlayerPos = getPlayerPos() - borderFinder;
                        //drawMapArray[newPlayerPos].setIcon(playerIcon);
                        drawMapArray[newPlayerPos].setIcon(resizeIcon(playerIcon, topPanel.getWidth() / 5, topPanel.getHeight() / 5));
                        drawMapArray[getPlayerPos()].setIcon(resizeIcon(road, topPanel.getWidth() / 5, topPanel.getHeight() / 5));
                        //drawMapArray[getPlayerPos()].setIcon(road);
                        setPlayerPos(newPlayerPos);
                    }catch (ArrayIndexOutOfBoundsException e){

                    }
                }
            }
        }
        if (key == KeyEvent.VK_LEFT) {
            if (playerPos % borderFinder != 0) {
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
            if (playerPos % (borderFinder - 1)  != 0) {
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
            /*
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
            THIS IS A GOOD CLUE ON HOW TO COMBINE ICONS!!!!!
             */
        }

        if (key == KeyEvent.VK_DOWN) {
            if (playerPos + 1 % borderFinder != 0) {
                //Move freely into you get into the castle tile
                System.out.println(playerPos);
                if (playerPos != castleLoc ) {
                    try {
                        int newPlayerPos = getPlayerPos() + borderFinder;
                        //drawMapArray[newPlayerPos].setIcon(playerIcon);
                        drawMapArray[newPlayerPos].setIcon(resizeIcon(playerIcon, topPanel.getWidth() / 5, topPanel.getHeight() / 5));
                        drawMapArray[getPlayerPos()].setIcon(resizeIcon(road, topPanel.getWidth() / 5, topPanel.getHeight() / 5));
                        //drawMapArray[getPlayerPos()].setIcon(road);
                        setPlayerPos(newPlayerPos);
                    }catch (ArrayIndexOutOfBoundsException e){

                    }
                }
            }
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


    /*------------------------------------------------------------
    Creates the frame and creates the panel (top)
    Uses the new JFrame and new JPanel to proceed
    -----------------------------------------------------------*/
    public void createSwing() {
        createFrame(levelFrame);
        createPanel(topPanel);
    }


    /*------------------------------------------------------------
    Gives the newly created frame features
    -----------------------------------------------------------*/
    private void createFrame(JFrame frame) {
        frame.setSize(1000, 1000);
        //frame.setLayout(new GridLayout(1, 1));
        //TODO Check to see if this changes anything
        frame.setLayout(new BorderLayout());
        frame.add(topPanel);
        frame.addKeyListener(this);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /*------------------------------------------------------------
    Creates the panel with the data we given at the top of the class
    row x col
    -----------------------------------------------------------*/
    private void createPanel(JPanel panel) {
        panel.setLayout(new GridLayout(rowTopPanel, colTopPanel));
    }

    /*------------------------------------------------------------
    Allows us to resize the image correctly
    -----------------------------------------------------------*/
    public static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }

    /*------------------------------------------------------------
    Accessors
    -----------------------------------------------------------*/
    public int getPlayerPos() {
        return playerPos;
    }

    public boolean isLevelUp() {
        return isLevelUp;
    }

    public String getLevelArea() {
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


    /*------------------------------------------------------------
    Mutators
    -----------------------------------------------------------*/
    public void setLevelArea(String levelArea) {
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
