package ch.uzh.model.lobby;

public interface LobbyPlayer {
    public String getName();

    public String getColor();

    public int getAmountOfCells();

    public void setAmountOfCells(int amountOfCells);
}
