package DiceGamev5.Area;

import javax.swing.*;

public class ForestLevel extends LevelSelector {

    public ForestLevel(int playerPos, String levelArea) {
        super(playerPos, levelArea); //ReRuns Level Selector
        setDesign();
        arraryLabel(); //Reruns topPanel!

    }


    //Overrides the old design with the current design
    public void setDesign() {
        setBottomTile(new ImageIcon("forestPic/grassTile.png")); //Changes the icon for something
        setRoad(new ImageIcon("forestPic/grassRoad.png"));
        setPlayerIcon(new ImageIcon("forestPic/forestPlayer.png"));
        setCastleTile(new ImageIcon("forestPic/forestCastle.png"));
        setTopTile(new ImageIcon("forestPic/forest.png"));
        setLevelArea("FORREST");
    }
}
