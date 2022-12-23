package ch.uzh.model.game;

import ch.uzh.model.lobby.LobbyPlayer;

public interface GameObserver {
    public void nextPlayerTurn(LobbyPlayer currentPlayer);
    public void nextMove();
}
