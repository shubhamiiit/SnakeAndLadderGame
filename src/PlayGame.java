
import java.util.*;
import Entities.Dice;
import Entities.GameBoard;
import Entities.Jump;
import Entities.Player;
import ImportFiles.ImportFactory;
import ImportFiles.ImportType;

public class PlayGame {
    public static void main(String[] args) {
        // Create an instance of ImportFactory to get the ImportType based on the file type
        ImportFactory importfactory = new ImportFactory();

        // Get the ImportType object for YAML files
        ImportType importObj = importfactory.importFileType("YML");

        // Import game configuration from the specified file
        importObj.importFile();

        // Scanner to read user input
        @SuppressWarnings("resource")
        Scanner myScanner = new Scanner(System.in);

        // Read the number of snakes and create a list to store snake positions
        int numSnakes = myScanner.nextInt();
        List<Jump> snakes = new ArrayList<>();
        for(int i=0;i<numSnakes;i++){
            int snakeHead = myScanner.nextInt();
            int snakeTail = myScanner.nextInt();

            if(snakeTail >= snakeHead){
                System.err.println("Invalid Snake");
                return;
            }

            // Add a snake to the list
            snakes.add(new Jump(snakeHead, snakeTail));
        }

        // Read the number of ladders and create a list to store ladder positions
        int numLadders = myScanner.nextInt();
        List<Jump> ladders = new ArrayList<>();
        for(int i=0;i<numLadders;i++){
            int ladderBottom = myScanner.nextInt();
            int ladderTop = myScanner.nextInt();

            if(ladderBottom >= ladderTop){
                System.err.println("Invalid Ladder");
                return;
            }
            // Add a ladder to the list
            ladders.add(new Jump(ladderBottom, ladderTop));
        }

        // Create a queue to store all players and a map to store their current positions
        Queue<Player> allPlayers = new LinkedList<>();
        Map<String,Integer> playersCurrentPosition = new HashMap<>();

        // Read the number of players and create Player objects
        int numPlayers = myScanner.nextInt();
        for(int i=0;i<numPlayers;i++){
            String playerName = myScanner.next();
            Player player = new Player(playerName, i+1);

            // Read and store the initial position of the player
            int playerPosition = myScanner.nextInt();
            playersCurrentPosition.put(playerName, playerPosition);
            allPlayers.add(player);
        }

        // Create a Dice object with the number of dice obtained from the configuration
        Dice dice = new Dice(importObj.getNumDice());
      
        // Create a GameBoard with the imported configuration and start the game
        GameBoard gameboard = new GameBoard(dice,allPlayers,snakes,ladders,
        playersCurrentPosition,importObj.getBoardSize());

        // Start the game with the specified movement strategy
        gameboard.startGame(importObj.getmovementStrategy());
    }
}