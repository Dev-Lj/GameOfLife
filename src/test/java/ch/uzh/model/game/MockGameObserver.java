package ch.uzh.model.game;

import ch.uzh.model.lobby.LobbyPlayer;

public class MockGameObserver implements GameObserver{
    private LobbyPlayer currentPlayer = null;
    private boolean receivedNextPlayerTurn = false;
    private boolean receivedNextMove = false;


    @Override
    public void nextPlayerTurn(LobbyPlayer currentPlayer) {
        this.currentPlayer = currentPlayer;
        receivedNextPlayerTurn = true;
    }

    @Override
    public void nextMove() {
        receivedNextMove = true;
    }

    public boolean hasReceivedNextMove() {
        return receivedNextMove;
    }

    public boolean hasReceivedNextPlayerTurn() {
        return receivedNextPlayerTurn;
    }
    
    public LobbyPlayer getCurrentPlayer() {
        return currentPlayer;
    }
}
