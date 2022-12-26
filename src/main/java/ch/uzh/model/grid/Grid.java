package ch.uzh.model.grid;

import java.util.*;

import ch.uzh.model.lobby.LobbyPlayer;

public class Grid {
    private final int size;
    private LobbyPlayer[][] grid;
    private int generation = 0;
    private List<GridObserver> observers = new ArrayList<>();

    public Grid (int size) {
        this.size = size;
        this.grid = new LobbyPlayer[size][size];
    }

    /**
     * @pre coordinates not out of bounds of grid
     * */
    public void killCell(int x, int y, LobbyPlayer currentPlayer) throws InvalidCellException {
        // assert x >= 0 && x < size && y >= 0 && y < size;
        if (!isAlive(x, y)) {
            throw new InvalidCellException("Cell is already dead");
        }
        if (grid[x][y].equals(currentPlayer)) {
            throw new InvalidCellException("You can not kill your own cells");
        }
        setDead(grid, x, y);
        notifyObservers();
    }

    /**
     * @pre coordinates not out of bounds of grid
     * */
    public void plantCell(int x, int y, LobbyPlayer currentPlayer) throws InvalidCellException {
        // assert x >= 0 && x < size && y >= 0 && y < size;
        if(isAlive(x, y)) {
            throw new InvalidCellException("Cell is already alive");
        }
        setAlive(grid, x, y, currentPlayer);
        notifyObservers();
    }

    /**
     * @pre coordinates not out of bounds of grid
     * */
    private boolean isAlive(int x, int y) {
        // assert x >= 0 && x < size && y >= 0 && y < size;
        return grid[x][y] != null;
    }

    /**
     *
     * @pre coordinates not out of bounds of grid and cell is alive
     */
    private void setDead(LobbyPlayer[][] grid, int x, int y) {
        // assert x >= 0 && x < size && y >= 0 && y < size && grid[x][y] != null;
        LobbyPlayer owner = grid[x][y];
        grid[x][y] = null;
        owner.decreaseAmountOfCells();
        System.out.println(String.format("%s: %d cells", owner.getName(), owner.getAmountOfCells()));
    }

    /**
     *
     * @pre coordinates not out of bounds of grid and cell is dead
     */
    private void setAlive(LobbyPlayer[][] grid, int x, int y, LobbyPlayer owner) {
        // assert x >= 0 && x < size && y >= 0 && y < size && grid[x][y] == null;
        grid[x][y] = owner;
        owner.increaseAmountOfCells();
        System.out.println(String.format("%s: %d cells", owner.getName(), owner.getAmountOfCells()));
    }

    /**
     * @pre coordinates not out of bounds of grid
     * */
    private int getNumberOfAliveNeighbours(int x, int y) {
        // assert x >= 0 && x < size && y >= 0 && y < size;
        int aliveNeighbours = 0;
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if(x + i >= 0 && x + i < size && y + j >= 0 && y + j < size && !(i == 0 && j == 0) && isAlive(x + i, y + j)) {
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
        // assert x >= 0 && x < size && y >= 0 && y < size;
        Map<LobbyPlayer, Integer> neighbourFrequencies = new HashMap<>();
        for (int i = -1; i <= 1; i++) {
            for (int j = -1; j <= 1; j++) {
                if(x + i >= 0 && x + i < size && y + j >= 0 && y + j < size && !(i == 0 && j == 0) && isAlive(x + i, y + j)) {
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
        for(int row = 0; row < size; row++) {
            newGrid[row] = grid[row].clone();
        }
        return newGrid;
    }

    public void computeGeneration() {
        LobbyPlayer[][] newGrid = cloneGrid();
        for(int x = 0; x < size; x++) {
            for (int y = 0; y < size; y++) {
                int aliveNeighbours = getNumberOfAliveNeighbours(x, y);
                if (isAlive(x, y) && aliveNeighbours != 2 && aliveNeighbours != 3) {
                    setDead(newGrid, x, y);
                } else if(aliveNeighbours == 3) {
                    LobbyPlayer mostNeighbourPlayer = getMostNeighbourPlayer(x, y);
                    setAlive(newGrid, x, y, mostNeighbourPlayer);
                }
            }
        }
        grid = newGrid;
        this.generation++;
        notifyObservers();
    }

    public void attachObserver(GridObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (GridObserver gridObserver : observers) {
            gridObserver.updateGrid(this);
        }
    }

    public String[][] getDrawableGrid() {
        String[][] drawableGrid = new String[grid.length][grid.length];
        for (int x = 0; x < drawableGrid.length; x++) {
            for (int y = 0; y < drawableGrid.length; y++) {
                if (!isAlive(x, y)) {
                    drawableGrid[x][y] = "transparent";
                } else {
                    drawableGrid[x][y] = grid[x][y].getColor();
                }
            }
        }
        return drawableGrid;
    }

    public int getSize() {
        return size;
    }

    public int getGeneration() {
        return generation;
    }

    private LobbyPlayer[][] getGrid() {
        return grid;
    }

    // JUST FOR DEBUGGING PURPOSES
    // public void printGrid() {
    //     for (LobbyPlayer[] gridRow : grid) {
    //         for (LobbyPlayer player : gridRow) {
    //             String playerName = "null";
    //             if (player != null) {
    //                 playerName = player.getName();
    //             }
    //             System.out.print(playerName + "\t");
    //         }
    //         System.out.println();
    //     }
    //     System.out.println("-------------");
    // }

    // // JUST FOR DEBUGGING PURPOSES
    // public static void main(String[] args) throws InvalidCellException {
    //     LobbyPlayer p1 = new Player();
    //     p1.setName(" p1 ");
    //     LobbyPlayer p2 = new Player();
    //     p2.setName(" p2 ");
    //     Grid g = new Grid();

    //     g.plantCell(0, 0, p1);
    //     g.plantCell(0, 1, p1);
    //     g.plantCell(0, 2, p2);
    //     g.printGrid();
    //     g.computeGeneration();
    //     g.printGrid();
    //     g.computeGeneration();
    //     g.printGrid();
    // }
}
