package ch.uzh.model.game;

import ch.uzh.model.grid.Grid;
import ch.uzh.model.grid.InvalidCellException;
import ch.uzh.model.lobby.Lobby;

public class MockGame extends Game{
    private boolean movesInitialized = false;
    private boolean observerAttached = false;
    private boolean gridInitialized = false;

    public MockGame(Grid grid, Lobby lobby) {
        super(grid, lobby);
    }

    public boolean isMovesInitialized() {
        return movesInitialized;
    }

    public boolean isObserverAttached() {
        return observerAttached;
    }

    public boolean isGridInitialized() {
        return gridInitialized;
    }

    @Override
    public void attachObserver(GameObserver observer) {
        this.observerAttached = true;
    }

    @Override
    public void initializeMoves() {
        this.movesInitialized = true;
    }

    @Override
    public void initializeGrid() throws InvalidCellException {
        this.gridInitialized = true;
    }
    
}
