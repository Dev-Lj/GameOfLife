package ch.uzh.model.lobby;

import java.util.ArrayList;

public class Lobby {
    private Player currentPlayer;
    private ArrayList<Player> players = new ArrayList<Player>();
    private int playerCounter = 0;
    public static final int NR_PLAYERS = 2;
    private String errors;

    public Lobby() {

    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player nextPlayer() {
        int nextI = (playerCounter + 1) % NR_PLAYERS;
        this.playerCounter = nextI + 1;
        return this.currentPlayer;
    }

    public void setPlayer(Player player) {
        players.add(player);
    }

    public String getErrors() {
        return errors;
    }

    public boolean validPlayer() {
        for (Player player : players) {
            if (player.validPlayer() == false) {
                errors = "Color or name is not valid";
            }
        }
        if (players.get(0).getName().equals(players.get(1).getName())
                || players.get(0).getColor().equals(players.get(1).getColor()))
            errors = "Player names or colors are not unique or null";

        System.out.println(errors);
        if (errors != null && !errors.isEmpty())
            return false;
        else
            return true;
    }

}
