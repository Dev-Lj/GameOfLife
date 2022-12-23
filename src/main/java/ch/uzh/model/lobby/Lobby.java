package ch.uzh.model.lobby;

import java.util.ArrayList;

public class Lobby {
    private LobbyPlayer currentPlayer;
    private ArrayList<LobbyPlayer> players = new ArrayList<LobbyPlayer>();
    private int playerCounter = 1;

    public Lobby(ArrayList<LobbyPlayer> players) {
        this.players = players;
        nextPlayer();
        validPlayer();
    }

    public LobbyPlayer getCurrentPlayer() {
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
        for (LobbyPlayer player : players) {
            if (player.validPlayer() == false) {
                throw new IllegalArgumentException("Plz check if the players have color and name select/ filled out");
            }
        }
        if (players.get(0).getName().equals(players.get(1).getName())
                || players.get(0).getColor().equals(players.get(1).getColor()))
            throw new IllegalArgumentException("Name or collor does not differ, plz choose different names and colors");

    }

    public ArrayList<LobbyPlayer> getPlayers() {
        return players;
    }

}
