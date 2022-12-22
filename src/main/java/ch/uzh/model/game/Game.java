package ch.uzh.model.game;

import java.util.ArrayList;
import java.util.List;

import ch.uzh.model.grid.Grid;
import ch.uzh.model.grid.InvalidCellException;
import ch.uzh.model.lobby.Lobby;
import ch.uzh.model.lobby.Player;

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
    public void execute(Grid grid, int x, int y, Player currentPlayer) throws InvalidCellException;
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
    }

    public void initialize() {
        this.initializePlayerTurn();
    }

    private void initializePlayerTurn() {
        lobby.nextPlayer();
        remainingMoves = PlayerMove.values().length-1;
        nextMove = PlayerMove.values()[remainingMoves];
        notifyObserversNextPlayerTurn();
        notifyObserversNextMove();
    }

    public void playerCellSelection(int x, int y) throws InvalidCellException {
        nextMove.function.execute(grid, x, y, lobby.getCurrentPlayer());
        calculateNextMove();
    }

    private void calculateNextMove() {
        remainingMoves--;
        if (remainingMoves < 0) {
            grid.computeGeneration();
            initializePlayerTurn();
            return;
        }
        nextMove = PlayerMove.values()[remainingMoves];
        notifyObserversNextMove();
    }

    public void attachObserver(GameObserver observer) {
        this.observers.add(observer);
    }

    private void notifyObserversNextPlayerTurn() {
        Player currentPlayer = lobby.getCurrentPlayer();
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

    
}
