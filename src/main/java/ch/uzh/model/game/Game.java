package ch.uzh.model.game;

import java.util.ArrayList;
import java.util.List;

import ch.uzh.model.grid.Grid;
import ch.uzh.model.grid.InvalidCellException;
import ch.uzh.model.lobby.Lobby;
import ch.uzh.model.lobby.LobbyPlayer;

enum PlayerMove {
    KillCell("Click on an enemy cell to kill it", Grid::killCell),
    PlantCell("Click on a dead cell to bring it to live", Grid::plantCell);

    final String description;
    PlayerMoveFunction function;

    PlayerMove(String description, PlayerMoveFunction function) {
        this.description = description;
        this.function = function;
    }
}

@FunctionalInterface
interface PlayerMoveFunction {
    public void execute(Grid grid, int x, int y, LobbyPlayer currentPlayer) throws InvalidCellException;
}

public class Game {
    private final Grid grid;
    private final Lobby lobby;
    private List<GameObserver> observers = new ArrayList<>();
    private PlayerMove nextMove;
    private int remainingMoves;

    public Game(Grid grid, Lobby lobby) {
        assert grid != null && lobby != null;
        this.grid = grid;
        this.lobby = lobby;
        remainingMoves = PlayerMove.values().length-1;
        nextMove = PlayerMove.values()[remainingMoves];
    }

    public void initializeMoves() {
        notifyObserversNextPlayerTurn(lobby.getCurrentPlayer());
        notifyObserversNextMove();
    }

    public void initializeGrid() throws InvalidCellException {
        setDefaultInitialGridPattern(grid);
    }

    /**
     * Set stable initial pattern for both players
     * @param grid
     * @throws InvalidCellException
     * @pre grid != null && grid.getDimension()>=4 && no moves have been made on grid yet
     */
    private void setDefaultInitialGridPattern(Grid grid) throws InvalidCellException {
        assert grid != null && grid.getDimension()>=4;
        List<LobbyPlayer> players = lobby.getPlayers();
        int halfLower = (int)grid.getDimension()/2;
        // pattern for player 1
        grid.plantCell(0, halfLower, players.get(0));
        grid.plantCell(0, halfLower - 1, players.get(0));
        grid.plantCell(1, halfLower, players.get(0));
        grid.plantCell(1, halfLower - 1, players.get(0));
        // pattern for player 2
        grid.plantCell(grid.getDimension()-1, halfLower, players.get(1));
        grid.plantCell(grid.getDimension()-1, halfLower - 1, players.get(1));
        grid.plantCell(grid.getDimension()-2, halfLower, players.get(1));
        grid.plantCell(grid.getDimension()-2, halfLower - 1, players.get(1));
    }

    public void playerCellSelection(int x, int y) throws InvalidCellException {
        nextMove.function.execute(grid, x, y, lobby.getCurrentPlayer());
        calculateNextMove();
    }

    private void calculateNextMove() {
        remainingMoves--;
        if (remainingMoves < 0) {
            // switch player
            grid.computeGeneration();
            lobby.nextPlayer();
            remainingMoves = PlayerMove.values().length-1;
            nextMove = PlayerMove.values()[remainingMoves];
            notifyObserversNextPlayerTurn(lobby.getCurrentPlayer());
        } else {
            nextMove = PlayerMove.values()[remainingMoves];
        }
        notifyObserversNextMove();
    }

    public void attachObserver(GameObserver observer) {
        this.observers.add(observer);
    }

    private void notifyObserversNextPlayerTurn(LobbyPlayer currentPlayer) {
        for (GameObserver gameObserver : observers) {
            gameObserver.nextPlayerTurn(currentPlayer);
        }
    }

    private void notifyObserversNextMove() {
        for (GameObserver gameObserver : observers) {
            gameObserver.nextMove();
        }
    }

    public String getCurrentMoveDescription() {
        return nextMove.description;
    }

    public Grid getGrid() {
        return grid;
    }

    public Lobby getLobby() {
        return lobby;
    }

    
}
