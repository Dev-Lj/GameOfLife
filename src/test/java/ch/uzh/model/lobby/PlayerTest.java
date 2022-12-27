/*
package ch.uzh.model.lobby;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class PlayerTest {

    @Test
    public void testValidPlayertrue() {
        Player player = new Player();
        player.setName("test");
        player.setColor("red");
        assertDoesNotThrow(() -> player.validate());
    }

    @Test
    public void testValidPlayerMissingColor() {
        Player player = new Player();
        player.setName("test");
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    player.validate();
                });

        assertEquals("Color is missing", exception.getMessage());
    }

    @Test
    public void testValidPlayerNameEmpty() {
        Player player = new Player();
        player.setColor("red");
        player.setName("");
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    player.validate();
                });
        assertEquals("Name is missing", exception.getMessage());
    }

    @Test
    public void testValidPlayerMissingName() {
        Player player = new Player();
        player.setColor("red");
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    player.validate();
                });
        assertEquals("Name is missing", exception.getMessage());
    }

    @Test
    public void testValidPlayerMissingBooth() {
        Player player = new Player();
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    player.validate();
                });
        assertEquals("Color is missing", exception.getMessage());
    }

    @Test
    public void testIncreaseAmountOfCells5() {
        Player player = new Player();
        player.increaseAmountOfCells();
        player.increaseAmountOfCells();
        assertEquals(2, player.getAmountOfCells());
    }

    @Test
    public void testDecreaseAmountOfCells5() {
        Player player = new Player();
        player.increaseAmountOfCells();
        player.increaseAmountOfCells();
        player.increaseAmountOfCells();
        player.decreaseAmountOfCells();
        assertEquals(2, player.getAmountOfCells());
    }

    @Test
    public void testSetAmountOfCells0() {
        Player player = new Player();
        assertEquals(0, player.getAmountOfCells());
    }

    // test set name
    @Test
    public void testSetNametestname() {
        Player player = new Player();
        player.setName("testname");
        assertEquals("testname", player.getName());
    }

    @Test
    public void testSetName() {
        Player player = new Player();
        assertEquals(null, player.getName());
    }

    @Test
    public void testSetColorRed() {
        Player player = new Player();
        player.setColor("red");
        assertEquals("red", player.getColor());
    }

    @Test
    public void testSetColornull() {
        Player player = new Player();
        assertEquals(null, player.getName());
    }

    @Test
    public void testToStringTestname() {
        Player player = new Player();
        player.setName("testname");
        assertEquals("testname", player.toString());
    }

    @Test
    public void testToStringNull() {
        Player player = new Player();
        assertEquals(null, player.toString());
    }

}
*/
