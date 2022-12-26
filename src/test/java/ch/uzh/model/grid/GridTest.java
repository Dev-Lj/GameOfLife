package ch.uzh.model.grid;

import ch.uzh.model.lobby.LobbyPlayer;
import ch.uzh.model.lobby.Player;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class GridTest {
    Grid grid = new Grid(4);
    Player p1 = new Player();
    Player p2 = new Player();

    LobbyPlayer[][] getGrid() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method gridMethod = Grid.class.getDeclaredMethod("getGrid");
        gridMethod.setAccessible(true);
        return (LobbyPlayer[][]) gridMethod.invoke(grid);
    }

    @Test
    void testKillCell() {
    }

    @Test
    void testPlantCell() throws InvalidCellException, IllegalAccessException, InvocationTargetException, NoSuchMethodException {
        grid.plantCell(1, 1, p1);
        LobbyPlayer[][] gridArray = getGrid();
        assertEquals(gridArray[1][1], p1);
    }

    /*@Test
    void testPlantCell_exception() throws InvalidCellException {
        grid.plantCell(1, 1, p1);
    }*/

    @Test
    void testSetDead() {

    }

    @Test
    void testComputeGeneration() {
        grid.computeGeneration();
        assertTrue(true);
    }

    @Test
    void testAttachObserver() {

    }

    @Test
    void testGetDrawableGrid() {

    }

    @Test
    void testGetDimension() {
        assertEquals(4, grid.getSize());
    }
    @Test
    void testGetGeneration() {
        assertEquals(0, grid.getGeneration());
    }
}