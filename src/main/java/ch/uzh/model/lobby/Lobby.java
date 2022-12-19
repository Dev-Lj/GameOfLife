package ch.uzh.model.lobby;

import java.lang.reflect.Field;

public class Lobby {
    private Player currentPlayer;
    private Player[] players;
    private int playerCounter = 2;
    static final int NR_PLAYERS = 2;

    public Lobby() {
        for (int i = 0; i < NR_PLAYERS; i++) {
            players[i] = new Player();
        }
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player nextPlayer() {
        int nextI = playerCounter % NR_PLAYERS;
        this.currentPlayer = players[nextI];
        this.playerCounter = nextI + 1;
        return this.currentPlayer;
    }
}
