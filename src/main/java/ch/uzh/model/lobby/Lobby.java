package ch.uzh.model.lobby;

import java.util.Arrays;
import java.util.List;

public class Lobby {
    private LobbyPlayer currentPlayer;
    private final List<LobbyPlayer> players;
    private int playerCounter = 1;

    /**
     * 
     * @param players
     * @pre players != null && players.length >= 2
     */
    public Lobby(LobbyPlayer... players) {
        assert players != null && players.length >= 2;
        this.players = Arrays.asList(players);
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
        // TODO use highlevel methods instead of manually checking for duplicates
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

    // TODO there must be a more elegant solution
    public List<LobbyPlayer> getPlayers() {
        return players;
    }

}
