package ch.uzh.model.grid;

import java.util.ArrayList;
import java.util.List;

import ch.uzh.model.lobby.Player;

public class Grid {
    private static final int GRID_SIZE = 5;
    private final Player[][] grid = new Player[GRID_SIZE][GRID_SIZE];
    List<GridObserver> observers = new ArrayList<>();


    public void changeGrid() {
        notifyObservers();
    }

    /**
     * @pre coordinates not out of bounds of grid
     * */
    public void killCell(int x, int y, Player currentPlayer) throws InvalidCellException {
        assert x >= 0 && x < GRID_SIZE && y >= 0 && y < GRID_SIZE;
        if(grid[x][y] == currentPlayer || grid[x][y] == null) {
            throw new InvalidCellException();
        }
        grid[x][y] = null;
    }

    public void plantCell(int x, int y, Player currentPlayer) throws InvalidCellException {
        assert x >= 0 && x < GRID_SIZE && y >= 0 && y < GRID_SIZE;
        if(grid[x][y] == currentPlayer) {
            throw new InvalidCellException();
        }
        grid[x][y] = currentPlayer;
    }

    public void attachObserver(GridObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (GridObserver gridObserver : observers) {
            gridObserver.notifyUpdate();
        }
    }

    public void computeGeneration() {
        // TODO: Implement
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
        System.out.println("-------------");
    }

    // JUST FOR DEBUGGING PURPOSES
    public static void main(String[] args) throws InvalidCellException {
        Player p1 = new Player();
        p1.setName(" p1 ");
        Player p2 = new Player();
        p2.setName(" p2 ");
        Grid g = new Grid();

        g.plantCell(0, 0, p1);
        g.plantCell(0, 1, p1);
        g.plantCell(4, 1, p2);
        g.killCell(0, 0, p2);

        g.printGrid();
    }
}
