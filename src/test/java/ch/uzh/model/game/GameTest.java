package ch.uzh.model.game;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

import ch.uzh.model.grid.MockGrid;
import ch.uzh.model.lobby.Lobby;
import ch.uzh.model.lobby.LobbyPlayer;
import ch.uzh.model.lobby.MockLobbyPlayer;

public class GameTest {
    private MockGrid mockGrid;
    private MockLobbyPlayer p1;
    private MockLobbyPlayer p2;
    private Lobby stubLobby;
    private Game game;
    
    public GameTest() {
        mockGrid = new MockGrid();
        p1 = new MockLobbyPlayer("p1", "red");
        p2 = new MockLobbyPlayer("p2", "blue");
        stubLobby = new Lobby(new ArrayList<LobbyPlayer>(Arrays.asList(p1, p2)));
        game = new Game(mockGrid, stubLobby);
    }

    private void notifyObserversNextPlayerTurn(Game game, LobbyPlayer currentPlayer) throws Throwable {
        Method method = game.getClass().getDeclaredMethod("notifyObserversNextPlayerTurn", LobbyPlayer.class);
        method.setAccessible(true);
        try {
            method.invoke(game, currentPlayer);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }

    private void notifyObserversNextMove(Game game) throws Throwable {
        Method method = game.getClass().getDeclaredMethod("notifyObserversNextMove");
        method.setAccessible(true);
        try {
            method.invoke(game);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }

    private void calculateNextMove(Game game) throws Throwable {
        Method method = game.getClass().getDeclaredMethod("calculateNextMove");
        method.setAccessible(true);
        try {
            method.invoke(game);
        } catch (InvocationTargetException e) {
            throw e.getCause();
        }
    }

    @Test
    public void testCalculateNextMove_twoMovesPerPlayer() throws Throwable {
        game = new Game(mockGrid, stubLobby);
        LobbyPlayer p = stubLobby.getCurrentPlayer();
        calculateNextMove(game);
        assertEquals(p.getName(), stubLobby.getCurrentPlayer().getName());
    }

    @Test
    public void testCalculateNextMove_switchPlayerAfterTwoMoves() throws Throwable {
        assertEquals(p1, stubLobby.getCurrentPlayer());
        calculateNextMove(game);
        calculateNextMove(game);
        assertEquals(p2, stubLobby.getCurrentPlayer());
    }

    @Test
    public void testCalculateNextMove_generationComputedAfterTwoMoves() throws Throwable {
  
        assertFalse(mockGrid.isGenerationComputed());
        calculateNextMove(game);
        calculateNextMove(game);
        assertTrue(mockGrid.isGenerationComputed());
    }

    @Test
    public void testCalculateNextMove_nextMoveNotified() throws Throwable {
        MockGameObserver obs1 = new MockGameObserver();
        game.attachObserver(obs1);
        calculateNextMove(game);
        assertTrue(obs1.hasReceivedNextMove());
    }

    @Test
    public void testNotifyObserversNextPlayerTurn() throws Throwable {
        MockGameObserver obs1 = new MockGameObserver();
        MockGameObserver obs2 = new MockGameObserver();
        game.attachObserver(obs1);
        game.attachObserver(obs2);
        notifyObserversNextPlayerTurn(game, p1);
        assertTrue(obs1.hasReceivedNextPlayerTurn());
        assertTrue(obs2.hasReceivedNextPlayerTurn());
    }

    @Test
    public void testNotifyObserversNextPlayerTurn_correctCurrentPlayer() throws Throwable {
        MockGameObserver obs1 = new MockGameObserver();
        game.attachObserver(obs1);
        notifyObserversNextPlayerTurn(game, p1);
        assertEquals(p1, obs1.getCurrentPlayer());
    }

    @Test
    public void testNotifyObserversNextMove() throws Throwable {
        MockGameObserver obs1 = new MockGameObserver();
        MockGameObserver obs2 = new MockGameObserver();
        game.attachObserver(obs1);
        game.attachObserver(obs2);
        notifyObserversNextMove(game);
        assertTrue(obs1.hasReceivedNextMove());
        assertTrue(obs2.hasReceivedNextMove());
    }
}
