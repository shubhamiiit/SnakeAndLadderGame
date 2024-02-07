package test;

import org.junit.jupiter.api.Test;

import Entities.Dice;
import Strategy.MovementStrategy;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class DiceTest {

    @Test
    public void testRollDice() {
        // Create a Dice with 2 dice
        Dice dice = new Dice(2);

        // Roll the dice with SUM strategy
        int sumResult = dice.rollDice(MovementStrategy.SUM);
        assertTrue(sumResult >= 2 && sumResult <= 12, "Sum result should be between 2 and 12");

        // Roll the dice with MAX strategy
        int maxResult = dice.rollDice(MovementStrategy.MAX);
        assertTrue(maxResult >= 1 && maxResult <= 6, "Max result should be between 1 and 6");

        // Roll the dice with MIN strategy
        int minResult = dice.rollDice(MovementStrategy.MIN);
        assertTrue(minResult >= 1 && minResult <= 6, "Min result should be between 1 and 6");
    }
}
