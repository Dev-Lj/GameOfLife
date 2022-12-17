package ch.uzh.model.lobby;

public class Lobby {
    private Player currentPlayer;

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Player nextPlayer() {
        // TODO set currentPlayer to next player
        return this.currentPlayer;
    }
}
