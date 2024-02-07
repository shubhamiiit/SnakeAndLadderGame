package test;

import Entities.Dice;
import Entities.GameBoard;
import Entities.Jump;
import Entities.Player;
import Strategy.MovementStrategy;

import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;

public class GameBoardTest {

    private Queue<Player> nextTurn;
    private List<Jump> snakes;
    private List<Jump> ladders;
    private Map<String, Integer> playersCurrentPosition;
    private int boardSize;

    @BeforeEach
    public void setUp() {
        // Initialize the components before each test
        nextTurn = new LinkedList<>();
        snakes = new ArrayList<>();
        ladders = new ArrayList<>();
        playersCurrentPosition = new HashMap<>();
        boardSize = 100;
    }

    @Test
    public void testStartGame() {
        // Create a player
        Player player1 = new Player("Player1", 1);
        Player player2 = new Player("Player2", 2);
        playersCurrentPosition.put("Player1", 1);
        playersCurrentPosition.put("Player2", 1);

        nextTurn.add(player1);
        nextTurn.add(player2);

        // Create a snake at position 10 that leads to position 5
        Jump snake = new Jump(10, 5);
        snakes.add(snake);

        // Create a stub for the Dice class with fixed rolls (3 in this case)
        Dice diceStub = new Dice(2);

        // Create a GameBoard instance
        GameBoard gameBoard = new GameBoard(diceStub, nextTurn, snakes, ladders, playersCurrentPosition, boardSize);

        // Run the startGame method
        gameBoard.startGame(MovementStrategy.SUM);

        // Ensure that the player's position has been updated correctly
        assertTrue(playersCurrentPosition.get("Player1")>94);
        assertTrue(playersCurrentPosition.get("Player2")>94);
    }
}
