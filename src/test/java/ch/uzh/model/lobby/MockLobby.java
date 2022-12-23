package ch.uzh.model.lobby;

import java.util.ArrayList;
import java.util.Arrays;

public class MockLobby extends Lobby{

    public MockLobby(ArrayList<LobbyPlayer> players) {
        super(players);
        //TODO Auto-generated constructor stub
    }

    public static MockLobby createDefaultMock() {
        ArrayList<LobbyPlayer> players = new ArrayList<>(Arrays.asList(new MockLobbyPlayer("a", "red"), new MockLobbyPlayer("b", "blue")));
        return new MockLobby(players);
    }
    
}
