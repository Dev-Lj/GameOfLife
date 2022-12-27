package ch.uzh.model.lobby;

public class MockLobbyPlayer implements LobbyPlayer{
    private final String name;
    private final String color;
    private int amountOfCells = 0;

    

    public MockLobbyPlayer(String name, String color) {
        this.name = name;
        this.color = color;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getColor() {
        return color;
    }

    @Override
    public int getAmountOfCells() {
        return amountOfCells;
    }

    @Override
    public void decreaseAmountOfCells() {
        this.amountOfCells--;
    }

    @Override
    public void increaseAmountOfCells() {
        this.amountOfCells++;
    }
    
}
