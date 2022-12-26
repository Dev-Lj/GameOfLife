/*
package ch.uzh.model.lobby;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {

    @Test
    public void testValidPlayertrue() {
        Player player = new Player(1);
        player.setName("test");
        player.setColor("red");
        assertEquals(true, player.validPlayer());
    }

    @Test
    public void testValidPlayerMissingName() {
        Player player = new Player(1);
        player.setName("test");
        assertEquals(false, player.validPlayer());
    }

    @Test
    public void testValidPlayerNameEmpty() {
        Player player = new Player(1);
        player.setName("");
        assertEquals(false, player.validPlayer());
    }

    @Test
    public void testValidPlayerMissingColor() {
        Player player = new Player(1);
        player.setColor("red");
        assertEquals(false, player.validPlayer());
    }

    @Test
    public void testValidPlayerMissingBooth() {
        Player player = new Player(1);
        assertEquals(false, player.validPlayer());
    }

    @Test
    public void testSetAmountOfCells5() {
        Player player = new Player(1);
        player.setAmountOfCells(5);
        assertEquals(5, player.getAmountOfCells());
    }

    @Test
    public void testSetAmountOfCells0() {
        Player player = new Player(1);
        assertEquals(0, player.getAmountOfCells());
    }

    // test set name
    @Test
    public void testSetNametestname() {
        Player player = new Player(1);
        player.setName("testname");
        assertEquals("testname", player.getName());
    }

    @Test
    public void testSetName() {
        Player player = new Player(1);
        assertEquals(null, player.getName());
    }

    @Test
    public void testSetColorRed() {
        Player player = new Player(1);
        player.setColor("red");
        assertEquals("red", player.getColor());
    }

    @Test
    public void testSetColornull() {
        Player player = new Player(1);
        assertEquals(null, player.getName());
    }

    @Test
    public void testToStringTestname() {
        Player player = new Player(1);
        player.setName("testname");
        assertEquals("testname", player.toString());
    }

    @Test
    public void testToStringNull() {
        Player player = new Player(1);
        assertEquals(null, player.toString());
    }

}
*/
