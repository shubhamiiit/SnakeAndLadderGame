package Entities;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import Strategy.MovementStrategy;

public class GameBoard {
    private Dice dice;
    private Queue<Player> nextTurn;
    private List<Jump> snakes;
    private List<Jump> ladders;
    private Map<String, Integer> playersCurrentPosition;
    int boardSize;

    public GameBoard(Dice dice, Queue<Player> nextTurn, List<Jump> snakes, List<Jump> ladders,
        Map<String,Integer> playersCurrentPosition,int boardSize) {
        this.dice = dice;
        this.nextTurn = nextTurn;
        this.snakes = snakes;
        this.ladders = ladders;
        this.playersCurrentPosition = playersCurrentPosition;
        this.boardSize = boardSize;
    }

    public void startGame(MovementStrategy strategy){
        // Continue the game until only one player is left
        while(nextTurn.size()>1) {
            Player player = nextTurn.poll(); // Get the next player

            int currentPosition = playersCurrentPosition.get(player.getPlayerName());
            int diceValue = dice.rollDice(strategy);
            int nextCell = currentPosition + diceValue;

            // Check if the next cell exceeds the board size
            if (nextCell > boardSize) 
                nextTurn.offer(player); // Put the player back in the queue
            else if (nextCell == boardSize) {
                System.out.println(player.getPlayerName() + " rolled a " + diceValue 
                 + " and moved from " + currentPosition + " to " + nextCell);
            }else{
                int[] nextPosition = new int[1];
                boolean[] foundLadder = new boolean[1];
                nextPosition[0] = nextCell;

                // Check for snakes
                snakes.forEach(snake -> {
                    if (snake.getStartPoint() == nextCell) {
                        nextPosition[0] = snake.getEndPoint();
                    }
                });

                // Handle snake bite
                if (nextPosition[0] != nextCell){
                    System.out.println(player.getPlayerName() +
                     " rolled a " + diceValue + " and bitten by snake at " 
                     + nextCell + " and moved from " + nextCell + " to " + nextPosition[0]);
                    
                    //Check for players position at snakes' tail
                    nextTurn.forEach(players -> {
                        if (playersCurrentPosition.get(players.getPlayerName()) == nextPosition[0]) {
                            playersCurrentPosition.put(players.getPlayerName(), 0);
                        }
                    });
                }

                // Check for ladders
                ladders.forEach(ladder -> {
                    if (ladder.getStartPoint() == nextCell) {
                        nextPosition[0] = ladder.getEndPoint();
                        foundLadder[0] = true;
                    }
                });

                // Handle ladder climb
                if (nextPosition[0] != nextCell && foundLadder[0])
                    System.out.println(player.getPlayerName() + 
                    " rolled a " + diceValue + " and climed the ladder at "
                    + nextCell + " and moved from " + nextCell + " to " + nextPosition[0]);

                // Check if the player has reached the final cell
                if (nextPosition[0] == boardSize) {
                    System.out.println(player.getPlayerName() + " rolled a " + diceValue 
                    + " and moved from " + currentPosition + " to " + nextPosition[0]);
                } else {
                    playersCurrentPosition.put(player.getPlayerName(), nextPosition[0]);
                    System.out.println(player.getPlayerName() + " rolled a " + diceValue 
                    + " and moved from " + currentPosition + " to " + nextPosition[0]);
                    nextTurn.offer(player); // Put the player back in the queue
                }
            }
        }
    }
}
