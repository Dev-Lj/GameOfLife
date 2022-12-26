package ch.uzh.model.lobby;

public class MockLobby extends Lobby{

    public MockLobby(LobbyPlayer... players) {
        super(players);
    }

    public static MockLobby createDefaultMock() {
        return new MockLobby(new MockLobbyPlayer("a", "red"), new MockLobbyPlayer("b", "blue"));
    }
    
}
