package ch.uzh.model.lobby;

import java.lang.reflect.Field;

public class Lobby {
    private Player currentPlayer;
    private Player[] players;
    private int playerCounter = 0;
    static final int NR_PLAYERS = 2;

    public Lobby() {
        for (int i = 0; i < NR_PLAYERS; i++) {
            players[i] = new Player();
        }
        currentPlayer = players[playerCounter];
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player nextPlayer() {
        int nextI = (playerCounter + 1) % NR_PLAYERS;
        this.currentPlayer = players[nextI];
        this.playerCounter = nextI + 1;
        return this.currentPlayer;
    }
}
