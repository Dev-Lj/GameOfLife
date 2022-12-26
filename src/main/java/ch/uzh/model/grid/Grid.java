package ch.uzh.model.grid;

import java.util.*;

import ch.uzh.model.lobby.LobbyPlayer;

public class Grid {
    private static final int GRID_SIZE = 4;
    private LobbyPlayer[][] grid = new LobbyPlayer[GRID_SIZE][GRID_SIZE];
    List<GridObserver> observers = new ArrayList<>();

    /**
     * @pre coordinates not out of bounds of grid
     * */
    public void killCell(int x, int y, LobbyPlayer currentPlayer) throws InvalidCellException {
        assert x >= 0 && x < GRID_SIZE && y >= 0 && y < GRID_SIZE;
        LobbyPlayer cell = grid[x][y];
        if (cell == null) {
            throw new InvalidCellException("Cell is already dead");
        }
        if (cell.equals(currentPlayer)) {
            throw new InvalidCellException("You can not kill your own cells");
        }
        grid[x][y] = null;
        notifyObservers();
    }

    /**
     * @pre coordinates not out of bounds of grid
     * */
    public void plantCell(int x, int y, LobbyPlayer currentPlayer) throws InvalidCellException {
        assert x >= 0 && x < GRID_SIZE && y >= 0 && y < GRID_SIZE;
        if(grid[x][y] != null) {
            throw new InvalidCellException("Cell is already alive");
        }
        grid[x][y] = currentPlayer;
        notifyObservers();
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
    private int getNumberOfAliveNeighbours(int x, int y) {
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
    private LobbyPlayer getMostNeighbourPlayer(int x, int y) {
        assert x >= 0 && x < GRID_SIZE && y >= 0 && y < GRID_SIZE;
        Map<LobbyPlayer, Integer> neighbourFrequencies = new HashMap<>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if(x + i >= 0 && x + i < GRID_SIZE && y + j >= 0 && y + j < GRID_SIZE && !(i == 0 && j == 0) && isAlive(x + i, y + j)) {
                    LobbyPlayer player = grid[x + i][y + j];
                    Integer count = neighbourFrequencies.get(player);
                    neighbourFrequencies.put(player, count != null ? count + 1 : 1);
                }
            }
        }
        return neighbourFrequencies.entrySet().stream().max(Comparator.comparingInt(Map.Entry::getValue)).get().getKey();
    }

    private LobbyPlayer[][] cloneGrid() {
        LobbyPlayer[][] newGrid = grid.clone();
        for(int row = 0; row < GRID_SIZE; row++) {
            newGrid[row] = grid[row].clone();
        }
        return newGrid;
    }

    public void computeGeneration() {
        LobbyPlayer[][] newGrid = cloneGrid();
        for(int x = 0; x < GRID_SIZE; x++) {
            for (int y = 0; y < GRID_SIZE; y++) {
                int aliveNeighbours = getNumberOfAliveNeighbours(x, y);
                if (isAlive(x, y) && aliveNeighbours != 2 && aliveNeighbours != 3) {
                    newGrid[x][y] = null;
                } else if(aliveNeighbours == 3) {
                    LobbyPlayer mostNeighbourPlayer = getMostNeighbourPlayer(x, y);
                    newGrid[x][y] = mostNeighbourPlayer;
                }
            }
        }
        grid = newGrid;
        notifyObservers();
    }

    public void attachObserver(GridObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (GridObserver gridObserver : observers) {
            gridObserver.notifyUpdate();
        }
    }

    public String[][] getDrawableGrid() {
        String[][] drawableGrid = new String[grid.length][grid.length];
        for (int x = 0; x < drawableGrid.length; x++) {
            for (int y = 0; y < drawableGrid.length; y++) {
                if (isAlive(x, y)) {
                    drawableGrid[x][y] = "transparent";
                } else {
                    drawableGrid[x][y] = grid[x][y].getColor();
                }
            }
        }
        return drawableGrid;
    }

    public int getDimension() {
        return grid.length;
    }
}
