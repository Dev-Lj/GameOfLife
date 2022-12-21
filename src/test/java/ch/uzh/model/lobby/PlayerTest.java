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

}
