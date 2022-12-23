package ch.uzh.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import ch.uzh.model.game.MockGame;
import ch.uzh.model.grid.MockGrid;
import ch.uzh.model.lobby.MockLobby;

public class GameViewControllerTest {
    @Test
    void testInitializeData() throws Exception {
        GameViewController controller = new GameViewController();
        
        MockGame mockGame = new MockGame(new MockGrid(), MockLobby.createDefaultMock());

        controller.initializeData(mockGame);
        assertTrue(mockGame.isObserverAttached());
        assertTrue(mockGame.isMovesInitialized());
        assertTrue(mockGame.isGridInitialized());
        
    }
}
