package ch.uzh.model.lobby;

import java.util.ArrayList;

// TODO replace Player with LobbyPlayer

public class Lobby {
    private Player currentPlayer;
    private ArrayList<Player> players = new ArrayList<Player>();
    private int playerCounter = 1;

    public Lobby(ArrayList<Player> players) {
        this.players = players;
        nextPlayer();
        validPlayer();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void nextPlayer() {
        int nextI = (playerCounter + 1) % PlayerConfig.NR_PLAYERS;
        this.playerCounter = nextI;
        this.currentPlayer = players.get(this.playerCounter);
    }

    // TODO imo validate() would be more clear.
    // TODO validate each player and say exactly what is wrong. 
    public void validPlayer() {
        for (Player player : players) {
            if (player.validPlayer() == false) {
                throw new IllegalArgumentException("Plz check if the players have color and name select/ filled out");
            }
        }
        if (players.get(0).getName().equals(players.get(1).getName())
                || players.get(0).getColor().equals(players.get(1).getColor()))
            throw new IllegalArgumentException("Name or collor does not differ, plz choose different names and colors");

    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

}
