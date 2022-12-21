package ch.uzh.model.lobby;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LobbyTest {
    public ArrayList<Player> createPlayerlist() {
        Player player1 = new Player();
        Player player2 = new Player();
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(player1);
        players.add(player2);
        return players;
    }

    public ArrayList<Player> createCorrectList() {
        ArrayList<Player> players = createPlayerlist();
        players.get(0).setName("pl1");
        players.get(1).setName("pl2");
        players.get(0).setColor("green");
        players.get(1).setColor("red");
        return players;
    }

    @Test
    public void testLobbyPlayerNull() {
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Lobby(createPlayerlist());
                });
        assertEquals("Plz check if the players have color and name select/ filled out", exception.getMessage());
    }

    @Test
    public void testLobbyPlayerSameColor() {
        ArrayList<Player> players = createPlayerlist();
        for (Player pl : players) {
            pl.setColor("red");
        }
        players.get(0).setName("pl1");
        players.get(1).setName("pl2");
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Lobby(players);
                });
        assertEquals("Name or collor does not differ, plz choose different names and colors", exception.getMessage());
    }

    @Test
    public void testLobbyPlayerSameName() {
        ArrayList<Player> players = createPlayerlist();
        for (Player pl : players) {
            pl.setName("pl1");
        }
        players.get(0).setColor("green");
        players.get(1).setColor("red");
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Lobby(players);
                });
        assertEquals("Name or collor does not differ, plz choose different names and colors", exception.getMessage());
    }

    @Test
    public void testLobyCreationCorrect() {
        ArrayList<Player> players = createCorrectList();
        Lobby lob = new Lobby(players);
        assertEquals(Lobby.class, lob.getClass());
    }

    @Test
    public void testCurrentPlayer() {
        ArrayList<Player> players = createCorrectList();
        Lobby lob = new Lobby(players);
        assertEquals("pl1", lob.getCurrentPlayer().getName());
    }

    @Test
    public void testCurrentNextPlayer() {
        ArrayList<Player> players = createCorrectList();
        Lobby lob = new Lobby(players);
        lob.nextPlayer();
        assertEquals("pl2", lob.getCurrentPlayer().getName());
    }

    @Test
    public void testCurrentNextPlayer4() {
        ArrayList<Player> players = createCorrectList();
        Lobby lob = new Lobby(players);
        lob.nextPlayer();
        lob.nextPlayer();
        lob.nextPlayer();
        lob.nextPlayer();
        assertEquals("pl1", lob.getCurrentPlayer().getName());
    }

}