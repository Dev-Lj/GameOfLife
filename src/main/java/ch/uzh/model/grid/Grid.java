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

    /**
     * @pre coordinates not out of bounds of grid
     * */
    public void plantCell(int x, int y, Player currentPlayer) throws InvalidCellException {
        assert x >= 0 && x < GRID_SIZE && y >= 0 && y < GRID_SIZE;
        if(grid[x][y] == currentPlayer) {
            throw new InvalidCellException();
        }
        grid[x][y] = currentPlayer;
    }

    /**
     * @pre coordinates not out of bounds of grid
     * */
    private boolean isAlive(int x, int y) {
        assert x >= 0 && x < GRID_SIZE && y >= 0 && y < GRID_SIZE;
        return grid[x][y] != null;
    }

    /**
     * @pre coordinates not out of bounds of grid
     * */
    private int getAliveNeighbours(int x, int y) {
        assert x >= 0 && x < GRID_SIZE && y >= 0 && y < GRID_SIZE;
        int aliveNeighbours = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if(x + i >= 0 && x + i < GRID_SIZE && y + j >= 0 && y + j < GRID_SIZE && !(i == 0 && j == 0) && isAlive(x + i, y + j)) {
                    aliveNeighbours++;
                }
            }
        }
        return aliveNeighbours;
    }

    /**
     * @pre coordinates not out of bounds of grid
     * */
    private Player getMostNeighbourPlayer(int x, int y) {
        assert x >= 0 && x < GRID_SIZE && y >= 0 && y < GRID_SIZE;
        return null;
    }

    private Player[][] cloneGrid() {
        Player[][] newGrid = grid.clone();
        for(int row = 0; row < GRID_SIZE; row++) {
            newGrid[row] = grid[row].clone();
        }
        return newGrid;
    }

    public void computeGeneration() {
        Player[][] newGrid = cloneGrid();
        for(int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                if (isAlive(row, col)) {

                } else {

                }
            }
        }
        // loop over all cells
            // if cell is alive
                // if 2 or 3 neighbours --> don't change anything
                // else --> make cell dead
            // if cell is dead
                // if 3 neightbours --> make alive (color of most neighbours)
                // else --> don't change anything
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
        g.plantCell(0, 2, p2);
        g.plantCell(2, 1, p2);
        g.plantCell(4, 1, p2);

        g.printGrid();
        System.out.println(g.getAliveNeighbours(4,4));
        g.computeGeneration();

    }
}
