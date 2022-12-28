package ch.uzh.model.lobby;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LobbyTest {

    private Lobby createCorrectLobby() {
        MockLobbyPlayer pl1 = new MockLobbyPlayer("pl1", "blue");
        MockLobbyPlayer pl2 = new MockLobbyPlayer("pl2", "red");
        LobbyPlayer[] plList = new LobbyPlayer[2];
        plList[0] = pl1;
        plList[1] = pl2;
        Lobby lob = new Lobby(plList);
        return lob;
    }

    @Test
    public void testLobbyPlayerSameName() {
        MockLobbyPlayer pl1 = new MockLobbyPlayer("pl1", "red");
        MockLobbyPlayer pl2 = new MockLobbyPlayer("pl1", "blue");
        LobbyPlayer[] plList = new LobbyPlayer[2];
        plList[0] = pl1;
        plList[1] = pl2;
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Lobby(plList);
                });
        assertEquals("Duplicate names", exception.getMessage());
    }

    @Test
    public void testLobbyPlayerOnePlayer() {
        MockLobbyPlayer pl1 = new MockLobbyPlayer("pl1", "red");
        LobbyPlayer[] plList = new LobbyPlayer[2];
        plList[0] = pl1;
        assertThrows(
                NullPointerException.class,
                () -> {
                    new Lobby(plList);
                });
    }

    @Test
    public void testLobbyPlayerOnePlayerNull() {
        MockLobbyPlayer pl1 = null;
        MockLobbyPlayer pl2 = new MockLobbyPlayer("pl1", "red");
        LobbyPlayer[] plList = new LobbyPlayer[2];
        plList[0] = pl1;
        plList[1] = pl2;
        assertThrows(
                NullPointerException.class,
                () -> {
                    new Lobby(plList);
                });
    }

    @Test
    public void testLobbyPlayerTwoPlayerNull() {
        MockLobbyPlayer pl1 = null;
        MockLobbyPlayer pl2 = null;
        LobbyPlayer[] plList = new LobbyPlayer[2];
        plList[0] = pl1;
        plList[1] = pl2;
        assertThrows(
                NullPointerException.class,
                () -> {
                    new Lobby(plList);
                });
    }

    @Test
    public void testLobbyPlayerOnlyOnePlayerNull() {
        MockLobbyPlayer pl1 = null;
        LobbyPlayer[] plList = new LobbyPlayer[2];
        plList[0] = pl1;
        assertThrows(
                NullPointerException.class,
                () -> {
                    new Lobby(plList);
                });
    }

    @Test
    public void testLobbyPlayerSameColor() {
        MockLobbyPlayer pl1 = new MockLobbyPlayer("pl12", "red");
        MockLobbyPlayer pl2 = new MockLobbyPlayer("pl1", "red");
        LobbyPlayer[] plList = new LobbyPlayer[2];
        plList[0] = pl1;
        plList[1] = pl2;
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Lobby(plList);
                });
        assertEquals("Duplicate colors", exception.getMessage());
    }

    @Test
    public void testLobyCreationCorrect() {
        assertDoesNotThrow(() -> createCorrectLobby());
    }

    @Test
    public void testLobbyPlayerGetPlayers() {
        Lobby lob = createCorrectLobby();
        assertEquals("pl1", lob.getPlayers().get(0).getName());
        assertEquals(2, lob.getPlayers().size());
    }

    @Test
    public void testCurrentPlayer() {
        assertEquals("pl1", createCorrectLobby().getCurrentPlayer().getName());
    }

    @Test
    public void testCurrentNextPlayer() {
        Lobby lob = createCorrectLobby();
        lob.nextPlayer();
        assertEquals("pl2", lob.getCurrentPlayer().getName());
    }

    @Test
    public void testCurrentNextPlayer4() {
        Lobby lob = createCorrectLobby();
        lob.nextPlayer();
        assertEquals("pl2", lob.getCurrentPlayer().getName());
        lob.nextPlayer();
        assertEquals("pl1", lob.getCurrentPlayer().getName());
        lob.nextPlayer();
        assertEquals("pl2", lob.getCurrentPlayer().getName());
        lob.nextPlayer();
        assertEquals("pl1", lob.getCurrentPlayer().getName());
    }

    @Test
    public void testCheckForWinner() {
        MockLobbyPlayer p1 = new MockLobbyPlayer("a", "red");
        MockLobbyPlayer p2 = new MockLobbyPlayer("b", "blue");
        Lobby lob = new Lobby(p1, p2);
        // players still have cells
        p1.setAmountOfCells(4);
        p2.setAmountOfCells(4);
        lob.checkForWinner();
        assertFalse(lob.hasWinner());
        // p2 has no alive cells
        p2.setAmountOfCells(0);
        lob.checkForWinner();
        assertTrue(lob.hasWinner());
        assertEquals(lob.getWinner(), p1);
    }

}
