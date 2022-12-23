package ch.uzh.model.game;

import ch.uzh.model.grid.Grid;
import ch.uzh.model.lobby.Lobby;

public class MockGame extends Game{
    private boolean movesInitialized = false;
    private boolean observerAttached = false;
    private boolean gridInitialized = false;
    private int cellSelection_x = -1;
    private int cellSelection_y = -1;

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
    public void initializeGrid() {
        this.gridInitialized = true;
    }

    @Override
    public void playerCellSelection(int x, int y) {
        cellSelection_x = x;
        cellSelection_y = y;
    }

    public int getCellSelection_x() {
        return cellSelection_x;
    }

    public int getCellSelection_y() {
        return cellSelection_y;
    }
    
}
