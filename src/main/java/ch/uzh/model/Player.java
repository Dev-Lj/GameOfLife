package ch.uzh.model;

public class Player {
    private Color color;
    private String name;

    public void setColor(String colorString) {
        this.color = Color.valueOf(colorString);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public String toString() {
        return name;
    }
}
