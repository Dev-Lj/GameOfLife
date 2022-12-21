package ch.uzh.model.lobby;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerTest {

    @Test
    public void testValidPlayertrue() {
        Player player = new Player();
        player.setName("test");
        player.setColor("red");
        assertEquals(true, player.validPlayer());
    }

    @Test
    public void testValidPlayerMissingName() {
        Player player = new Player();
        player.setName("test");
        assertEquals(false, player.validPlayer());
    }

    @Test
    public void testValidPlayerNameEmpty() {
        Player player = new Player();
        player.setName("");
        assertEquals(false, player.validPlayer());
    }

    @Test
    public void testValidPlayerMissingColor() {
        Player player = new Player();
        player.setColor("red");
        assertEquals(false, player.validPlayer());
    }

    @Test
    public void testValidPlayerMissingBooth() {
        Player player = new Player();
        assertEquals(false, player.validPlayer());
    }

    @Test
    public void testSetAmountOfCells5() {
        Player player = new Player();
        player.setAmountOfCells(5);
        assertEquals(5, player.getAmountOfCells());
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

}
