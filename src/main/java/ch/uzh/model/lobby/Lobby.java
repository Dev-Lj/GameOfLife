package ch.uzh.model.lobby;

import java.util.ArrayList;

public class Lobby {
    private LobbyPlayer currentPlayer;
    private ArrayList<LobbyPlayer> players = new ArrayList<LobbyPlayer>();
    private int playerCounter = 1;

    public Lobby(ArrayList<LobbyPlayer> players) {
        this.players = players;
        nextPlayer();
        validate();
    }

    public LobbyPlayer getCurrentPlayer() {
        return currentPlayer;
    }

    public void nextPlayer() {
        int nextI = (playerCounter + 1) % PlayerConfig.NR_PLAYERS;
        this.playerCounter = nextI;
        this.currentPlayer = players.get(this.playerCounter);
    }

    public void validate() {
        for (LobbyPlayer player : players) {
            player.validPlayer();
        }
        for (int i = 0; i < players.size(); i++) {
            for (int j = i + 1; j < players.size(); j++) {
                if (players.get(i).getColor().equals(players.get(j).getColor())) {
                    throw new IllegalArgumentException(
                            "Color of player " + (i + 1) + " and player " + (j + 1)
                                    + " are the same, please choose different colors");
                } else if (players.get(i).getName().equals(players.get(j).getName())) {
                    throw new IllegalArgumentException(
                            "Name of player " + (i + 1) + " and player " + (j + 1)
                                    + " are the same, please choose different names");
                }
            }
        }
    }

    public ArrayList<LobbyPlayer> getPlayers() {
        return players;
    }

}
