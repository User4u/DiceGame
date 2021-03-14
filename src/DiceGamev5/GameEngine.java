package DiceGamev5;

import java.io.FileNotFoundException;

public class GameEngine {

    //private File saveData = new File("saveFile\\SaveFile.txt");

    GameEngine(int playerPos) throws IllegalAccessException, FileNotFoundException{
        this(playerPos, 1);
    }

    GameEngine(int playerPos, int areaLevel){
        if(areaLevel == 1){
            new IntroScreen();
        }else if(areaLevel == 2){
            new ForestLevel(playerPos, areaLevel);
        }
    }

    //When you click newGame it will by default send you to stage one!
    //Stage 0 = is the Dev Room
    //Stage 1 = Dessert
    //Stage 2 = Forest
    GameEngine() throws IllegalAccessException, FileNotFoundException {
        this(1);
    }
}
