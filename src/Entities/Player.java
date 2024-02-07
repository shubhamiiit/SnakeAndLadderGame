package Entities;

public class Player {
    private String PlayerName;
    private int ID;

    public Player(String playerName, int id){
        this.PlayerName = playerName;
        this.ID = id;
    }

    public String getPlayerName() {
        return this.PlayerName;
    }

    public int getPlayerID(){
        return this.ID;
    }
}
