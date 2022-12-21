package ch.uzh.model.lobby;

import java.util.ArrayList;

public class Lobby {
    private Player currentPlayer;
    private ArrayList<Player> players = new ArrayList<Player>();
    private int playerCounter = 0;

    public Lobby(ArrayList<Player> players) {
        this.players = players;
        validPlayer();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player nextPlayer() {
        int nextI = (playerCounter + 1) % PlayerConfig.NR_PLAYERS;
        this.playerCounter = nextI + 1;
        return this.currentPlayer;
    }

    public void setPlayer(Player player) {
        players.add(player);
    }

    public void validPlayer() {
        for (Player player : players) {
            if (player.validPlayer() == false) {
                System.out.println(player.validPlayer());
                throw new IllegalArgumentException("Plz check if the players have color and name select/ filled out");
            }
        }
        if (players.get(0).getName().equals(players.get(1).getName())
                || players.get(0).getColor().equals(players.get(1).getColor()))
            throw new IllegalArgumentException("Name or collor does not differ, plz choose different names and colors");

    }

}
