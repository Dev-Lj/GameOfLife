package ch.uzh.model.game;

import ch.uzh.model.lobby.Player;

public interface GameObserver {
    public void nextPlayerTurn(Player currentPlayer);
}
