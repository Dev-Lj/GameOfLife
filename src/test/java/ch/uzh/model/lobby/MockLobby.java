package ch.uzh.model.lobby;

public class MockLobby extends Lobby{
    private LobbyPlayer winner;

    public MockLobby(LobbyPlayer... players) {
        super(players);
    }

    public static MockLobby createDefaultMock() {
        return new MockLobby(new MockLobbyPlayer("a", "red"), new MockLobbyPlayer("b", "blue"));
    }

    public void setWinner(LobbyPlayer winner) {
        this.winner = winner;
    }

    @Override
    public boolean hasWinner() {
        return winner != null;
    }

    @Override
    public void checkForWinner() {
        return;
    }

    @Override
    public LobbyPlayer getWinner() {
        return this.winner;
    }
    
    
}
