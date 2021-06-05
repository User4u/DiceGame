package DiceGamev5;

import DiceGamev5.Area.*;

import java.io.FileNotFoundException;

public class GameEngine {

    //Runs the Constructor with ONE parameter
    //Make it private -- No other object needs access to this!
    public static void main(String[] args) {
        new GameEngine(LevelSelector.getColTopPanel());
    }

    //Chains it so we can run two PARAMETER CONSTRUCTOR
    private GameEngine(int playerPos){
        this(playerPos, "DESSERT");
    }

    //Two parameter Constructor
    private GameEngine(int playerPos, String areaLevel){
        if(areaLevel.equals("DESSERT")){
            //Will have Dessert ready, but will go to the intro screen
            new IntroScreen();


        }else if(areaLevel.equals("FORREST")){
            //Will go straight to the Forrest
            //NOT REALLY USED ATM
            new ForestLevel(playerPos, areaLevel);
        }
    }

    //When you click newGame it will by default send you to stage one!
    //Stage 0 = is the Dev Room
    //Stage 1 = Dessert
    //Stage 2 = Forrest

}
