package ImportFiles;

import Strategy.MovementStrategy;

/**
 * Interface representing the type of import for game configuration.
 */

public interface ImportType {

    //Imports configuration from a specific file type (e.g., YAML, JSON).
    public void importFile();

    // Gets the MovementStrategy based on the provided strategy name.
    public default MovementStrategy getMovementStrategyName(String movementStrategy) {
        switch (movementStrategy) {
            case "SUM":
                return MovementStrategy.SUM;       
            case "MAX":
                return MovementStrategy.MAX;
            case "MIN":
                return MovementStrategy.MIN;
        }
        System.err.println("Invalid Movement Strategy");
        return null;
    }

    public int getNumPlayers();

    public int getBoardSize();

    public int getNumSnakes();

    public int getNumLadders();

    public int getNumDice();

    public MovementStrategy getmovementStrategy();
}
