package ch.uzh.model.lobby;

public interface LobbyPlayer {
    public String getName();

    public String getColor();

    public int getAmountOfCells();

    public void setAmountOfCells(int amountOfCells);

    // TODO should not be needed, purpose of LobbyPlayer is, that only valid players can be LobbyPlayers
    public boolean validPlayer();
}
