package DiceGamev5.Area;

import javax.swing.*;

public class DessertLevel extends LevelSelector {

    public DessertLevel(int playerPos, String levelArea) {
        super(playerPos, levelArea);
        //Places the images in _____
        setDesign();
        //Prints the map
        arraryLabel();
    }

    //In windows you use \\ or /
    //Linux doesnt have the '\\' problem so \ or /
    @Override
    public void arraryLabel() {
        super.arraryLabel();
    }

    private void setDesign() {
        ImageIcon icon = new ImageIcon("Images/newAyaFear.jpg");
        //getClass().getClassLoader().getResource("

        setBottomTile(new ImageIcon(getClass().getClassLoader().getResource("dessert.png")));
        setRoad(new ImageIcon(getClass().getClassLoader().getResource("dessertTileWithRoad.png")));
        setPlayerIcon(new ImageIcon("dessertPic/playerIcon.png"));
        setCastleTile(new ImageIcon("dessertPic/castleRoad.png"));
        setTopTile(new ImageIcon("dessertPic/mountain.png"));
        setLevelArea("DESSERT");
    }
}
