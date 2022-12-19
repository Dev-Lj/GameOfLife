package ch.uzh.model.lobby;

public class Player {
    private Color color;
    private String name;
    private int amountOfCells;

    public void setColor(String colorString) {
        this.color = Color.valueOf(colorString);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmountOfCells(int amountOfCells) {
        this.amountOfCells = amountOfCells;
    }

    public Color getColor() {
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
}
