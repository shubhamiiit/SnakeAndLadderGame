package ImportFiles;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;
import Strategy.MovementStrategy;

public class ImportYML implements ImportType{
    // Class variables to store configuration parameters
    private static int numberOfDice;
    private static int numberOfPlayers;
    private static int boardSize;
    private static int numberOfSnakes;
    private static int numberOfLadders;
    private static MovementStrategy strategy;

    @Override
    public void importFile(){
        // Load configuration from YAML file
        try{
            // Create a YAML parser
            Yaml yaml = new Yaml();

            // Open an InputStream to read the YAML file
            InputStream inputStream = new FileInputStream(new File("config.yml"));

            // Parse the YAML content into a Map
            @SuppressWarnings("unchecked")
            Map<String, Object> config = (Map<String, Object>) yaml.load(inputStream);

            //Extract configuration parameters
            numberOfPlayers = (int) config.get("NumberOfPlayers");
            boardSize = (int) config.get("BoardSize");
            numberOfSnakes = (int) config.get("NumberOfSnakes");
            numberOfLadders = (int) config.get("NumberOfLadders");
            numberOfDice = (int) config.get("NumberOfDice");
            String movementStrategy = (String) config.get("MovementStrategy");

            // Get the MovementStrategy based on the name obtained from the configuration
            strategy = getMovementStrategyName(movementStrategy);
        }
        catch(Exception e){
            // Handle exceptions (e.g., file not found, parsing error)
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
