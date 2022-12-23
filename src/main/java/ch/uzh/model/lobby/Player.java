package ch.uzh.model.lobby;

public class Player implements LobbyPlayer {
    private String color;
    private String name;
    private int amountOfCells;

    public Player() {
    }

    public void setAmountOfCells(int amountOfCells) {
        this.amountOfCells = amountOfCells;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    public int getAmountOfCells() {
        return amountOfCells;
    }

    @Override
    public String toString() {
        return name;
    }

    public boolean validPlayer() {
        return name != null && !name.equals("") && color != null;
    }
}
