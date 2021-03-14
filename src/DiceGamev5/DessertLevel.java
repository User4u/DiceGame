package DiceGamev5;

import javax.swing.*;

public class DessertLevel extends LevelSelector {

    DessertLevel(int playerPos, int levelArea) {
        super(playerPos, levelArea);
        setDesign();
        arraryLabel();
    }

    //In windows you use \\ or /
    //Linux doesnt have the '\\' problem so \ or /
    @Override
    public void arraryLabel() {
        super.arraryLabel();
    }

    public void setDesign() {
        ImageIcon icon = new ImageIcon("Images/newAyaFear.jpg");

        setBottomTile(new ImageIcon("dessertPic/dessert.png"));
        setRoad(new ImageIcon("dessertPic/dessertTileWithRoad.png"));
        setPlayerIcon(new ImageIcon("dessertPic/playerIcon.png"));
        setCastleTile(new ImageIcon("dessertPic/castleRoad.png"));
        setTopTile(new ImageIcon("dessertPic/mountain.png"));
        setLevelArea(1);
    }
}
