package Entities;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Strategy.MovementStrategy;

public class Dice{
    private int numberOfDice;

    // Constructor to initialize the number of dice
    public Dice(int numberOfDice){
        this.numberOfDice = numberOfDice;
    }

    // Method to roll the dice based on the given movement strategy
    public int rollDice(MovementStrategy movementStrategy){
        // List to store the values obtained from rolling the dice
        List<Integer> diceValues = new ArrayList<Integer>();

        // Generate random values for each die
        for(int i=0;i<numberOfDice;i++){
            diceValues.add(((int) (Math.random()*5)) + 1);
        }
        
        // Apply the specified movement strategy
        switch (movementStrategy) {
            case SUM:
                // Calculate and return the sum of all dice values
                return diceValues.stream().mapToInt(Integer::intValue).sum();
            case MAX:
                // Return the maximum value among the dice
                return Collections.max(diceValues);
            case MIN:
                // Return the minimum value among the dice
                return Collections.min(diceValues);
            default:
                // Handle the case where an irrelevant movement strategy is provided
                System.err.println("Irrelevant Movement Strategy");
                break;
        }
         
        return 0;
    }
}