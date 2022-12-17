package ch.uzh.model.grid;

import java.util.ArrayList;
import java.util.List;

import ch.uzh.model.lobby.Player;

public class Grid {
    List<GridObserver> observers = new ArrayList<>();

    public void changeGrid() {
        notifyObservers();
    }

    public static void killCell(Grid grid, int x, int y, Player currentPlayer) {
        // TODO implement
    }

    public static void plantCell(Grid grid, int x, int y, Player currentPlayer) {
        // TODO implement
    }

    public void attachObserver(GridObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (GridObserver gridObserver : observers) {
            gridObserver.notifyUpdate();
        }
    }
}
