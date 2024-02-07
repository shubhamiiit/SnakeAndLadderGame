package ImportFiles;

import java.io.FileReader;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import Strategy.MovementStrategy;

public class ImportJSON implements ImportType{
    public static int numberOfDice;
    public static int numberOfPlayers;
    public static int boardSize;
    public static int numberOfSnakes;
    public static int numberOfLadders;
    public static MovementStrategy strategy;

    @Override
    public void importFile(){
        try {
            // Load configuration from JSON file
            FileReader reader = new FileReader("config.json");
            JsonObject config = JsonParser.parseReader(reader).getAsJsonObject();

            // Extract configuration parameters
            numberOfPlayers = config.getAsJsonPrimitive("NumberOfPlayers").getAsInt();
            boardSize = config.getAsJsonPrimitive("BoardSize").getAsInt();
            numberOfSnakes = config.getAsJsonPrimitive("NumberOfSnakes").getAsInt();
            numberOfLadders = config.getAsJsonPrimitive("NumberOfLadders").getAsInt();
            numberOfDice = config.getAsJsonPrimitive("NumberOfDice").getAsInt();
            String movementStrategy = config.getAsJsonPrimitive("MovementStrategy").getAsString();
            strategy = getMovementStrategyName(movementStrategy);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getNumPlayers(){
        return numberOfPlayers;
    }

    @Override
    public int getBoardSize(){
        return boardSize;
    }

    @Override
    public int getNumSnakes(){
        return numberOfSnakes;
    }

    @Override
    public int getNumLadders(){
        return numberOfLadders;
    }

    @Override
    public int getNumDice(){
        return numberOfDice;
    }

    @Override
    public MovementStrategy getmovementStrategy(){
        return strategy;
    }
}
