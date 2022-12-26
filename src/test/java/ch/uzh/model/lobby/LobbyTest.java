/*
package ch.uzh.model.lobby;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LobbyTest {
    public ArrayList<Player> createPlayerlist() {
        Player player1 = new Player(1);
        Player player2 = new Player(1);
        ArrayList<Player> players = new ArrayList<Player>();
        players.add(player1);
        players.add(player2);
        return players;
    }

    public ArrayList<LobbyPlayer> createCorrectList() {
        ArrayList<Player> players = createPlayerlist();
        ArrayList<LobbyPlayer> lobbyPlayers = new ArrayList<LobbyPlayer>();
        players.get(0).setName("pl1");
        players.get(1).setName("pl2");
        players.get(0).setColor("green");
        players.get(1).setColor("red");
        for (Player player : players) {
            lobbyPlayers.add(player);
        }
        return lobbyPlayers;
    }

    @Test
    public void testLobbyPlayerNull() {
        ArrayList<Player> players = createPlayerlist();
        ArrayList<LobbyPlayer> lobbyPlayers = new ArrayList<LobbyPlayer>();
        for (Player player : players) {
            lobbyPlayers.add(player);
        }
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Lobby(lobbyPlayers);
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
        ArrayList<LobbyPlayer> lobbyPlayers = new ArrayList<LobbyPlayer>();
        for (Player player : players) {
            lobbyPlayers.add(player);
        }
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Lobby(lobbyPlayers);
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
        ArrayList<LobbyPlayer> lobbyPlayers = new ArrayList<LobbyPlayer>();
        for (Player player : players) {
            lobbyPlayers.add(player);
        }
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> {
                    new Lobby(lobbyPlayers);
                });
        assertEquals("Name or collor does not differ, plz choose different names and colors", exception.getMessage());
    }

    @Test
    public void testLobyCreationCorrect() {
        Lobby lob = new Lobby(createCorrectList());
        assertEquals(Lobby.class, lob.getClass());
    }

    @Test
    public void testCurrentPlayer() {
        ArrayList<LobbyPlayer> players = createCorrectList();
        Lobby lob = new Lobby(players);
        assertEquals("pl1", lob.getCurrentPlayer().getName());
    }

    @Test
    public void testCurrentNextPlayer() {
        ArrayList<LobbyPlayer> players = createCorrectList();
        Lobby lob = new Lobby(players);
        lob.nextPlayer();
        assertEquals("pl2", lob.getCurrentPlayer().getName());
    }

    @Test
    public void testCurrentNextPlayer4() {
        ArrayList<LobbyPlayer> players = createCorrectList();
        Lobby lob = new Lobby(players);
        lob.nextPlayer();
        lob.nextPlayer();
        lob.nextPlayer();
        lob.nextPlayer();
        assertEquals("pl1", lob.getCurrentPlayer().getName());
    }

}*/
