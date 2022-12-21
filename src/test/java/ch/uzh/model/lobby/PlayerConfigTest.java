package ch.uzh.model.lobby;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlayerConfigTest {
    PlayerConfig playerConfig = new PlayerConfig();

    @Test
    public void testList() {
        assertEquals("blue", PlayerConfig.COLORS.get(1));
    }

    @Test
    public void testListSize() {
        assertEquals(8, PlayerConfig.COLORS.size());
    }
}
