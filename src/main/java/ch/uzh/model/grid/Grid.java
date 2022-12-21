package ch.uzh.model.grid;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
    public void printGrid() {
        for (Player[] gridRow : grid) {
            for (Player player : gridRow) {
                String playerName = "null";
                if (player != null) {
                    playerName = player.getName();
                }
                System.out.print(playerName + "\t");
            }
            System.out.println();
        }
    }

    // JUST FOR DEBUGGING PURPOSES
    public static void main(String[] args) {
        Player p1 = new Player();
        p1.setName("p1");
        Player p2 = new Player();
        p2.setName("p2");
        Grid g = new Grid();
        g.plantCell(0, 0, p1);
        g.printGrid();
    }
}
