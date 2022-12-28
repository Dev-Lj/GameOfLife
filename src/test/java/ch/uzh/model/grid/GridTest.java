package ch.uzh.model.grid;

import ch.uzh.model.lobby.LobbyPlayer;
import ch.uzh.model.lobby.MockLobbyPlayer;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {
    private static final int SIZE = 4;
    private Grid grid = new Grid(SIZE);
    private MockLobbyPlayer p1 = new MockLobbyPlayer("p1", "red");
    private MockLobbyPlayer p2 = new MockLobbyPlayer("p2", "blue");

    LobbyPlayer getCell(int x, int y) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method gridMethod = Grid.class.getDeclaredMethod("getCell", int.class, int.class);
        gridMethod.setAccessible(true);
        return (LobbyPlayer) gridMethod.invoke(grid, x, y);
    }

    @Test
    void testKillCell() throws InvalidCellException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        grid.plantCell(1, 1, p1);
        grid.killCell(1, 1, p2);
        assertNull(getCell(1, 1));
    }

    @Test
    void testKillCell_notAlive() throws InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        try {
            grid.killCell(1, 1, p1);
            fail("No InvalidCellException thrown");
        } catch(InvalidCellException e) {
            // all good here
        }
    }

    @Test
    void testKillCell_samePlayer() throws InvalidCellException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        try {
            grid.plantCell(1, 1, p1);
            grid.killCell(1, 1, p1);
            fail("No InvalidCellException thrown");
        } catch(InvalidCellException e) {
            // all good here
        }
    }

    @Test
    void testPlantCell() throws InvalidCellException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        grid.plantCell(1, 1, p1);
        assertEquals(getCell(1, 1), p1);
    }

    @Test
    void testPlantCell_outOfBoundsException() throws InvalidCellException {
        try {
            grid.plantCell(SIZE, SIZE, p1);
            fail("No ArrayIndexOutOfBoundsException thrown");
        } catch(ArrayIndexOutOfBoundsException e) {
            // all good here
        }
    }

    @Test
    void testPlantCell_invalidCellException() {
        try {
            grid.plantCell(1, 1, p1);
            grid.plantCell(1, 1, p1);
            fail("No InvalidCellException thrown");
        } catch(InvalidCellException e) {
            // all good here
        }
    }

    @Test
    void testComputeGeneration_upperLeft() throws InvalidCellException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        grid.plantCell(0, 0, p1);
        grid.plantCell(0, 1, p1);
        grid.plantCell(0, 2, p1);
        grid.computeGeneration();
        assertNull(getCell(0, 0));
        assertNull(getCell(0, 2));
        assertEquals(getCell(0, 1), getCell(0, 1));
        assertEquals(getCell(0, 1), getCell(1, 1));
    }

    @Test
    void testComputeGeneration_lowerRight() throws InvalidCellException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        grid.plantCell(SIZE-1, SIZE-1, p1);
        grid.plantCell(SIZE-2, SIZE-1, p1);
        grid.plantCell(SIZE-1, SIZE-2, p2);
        grid.computeGeneration();
        assertEquals(getCell(SIZE-1, SIZE-1), p1);
        assertEquals(getCell(SIZE-2, SIZE-1), p1);
        assertEquals(getCell(SIZE-1, SIZE-2), p2);
        assertEquals(getCell(SIZE-2, SIZE-2), p1);
    }

    @Test
    void testComputeGeneration_fiveSquares() throws InvalidCellException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        int halfLower = (int)grid.getSize()/2;
        // pattern for player 1
        grid.plantCell(0, halfLower, p1);
        grid.plantCell(0, halfLower - 1, p1);
        grid.plantCell(1, halfLower, p1);
        grid.plantCell(1, halfLower - 1, p1);
        grid.plantCell(2, halfLower - 1, p1);
        assertEquals(5, p1.getAmountOfCells());

        grid.computeGeneration();
        assertEquals(5, p1.getAmountOfCells());
    }

    @Test
    void testGetDrawableGrid() {

    }

    @Test
    void testGetDimension() {
        assertEquals(SIZE, grid.getSize());
    }
    @Test
    void testGetGeneration() {
        assertEquals(0, grid.getGeneration());
    }
}