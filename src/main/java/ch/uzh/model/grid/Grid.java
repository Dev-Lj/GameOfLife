package ch.uzh.model.grid;

import java.util.ArrayList;
import java.util.List;

import ch.uzh.model.lobby.Player;

public class Grid {
    private static final int GRID_SIZE = 3;
    private final Player[][] grid = new Player[GRID_SIZE][GRID_SIZE];
    List<GridObserver> observers = new ArrayList<>();


    public void changeGrid() {
        notifyObservers();
    }

    /**
     * @pre coordinates not out of bounds of grid
     * */
    public void killCell(int x, int y, Player currentPlayer) {
        assert x >= 0 && x < GRID_SIZE && y >= 0 && y < GRID_SIZE;
    }

    public void plantCell(int x, int y, Player currentPlayer) {
        assert x >= 0 && x < GRID_SIZE && y >= 0 && y < GRID_SIZE;
    }

    public void computeGeneration() {
        // TODO: Implement
    }

    public void attachObserver(GridObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (GridObserver gridObserver : observers) {
            gridObserver.notifyUpdate();
        }
    }

    // JUST FOR DEBUGGING PURPOSES
    public static void main(String[] args) {
        Grid gridInstance = new Grid();
    }
}
