package ch.uzh.model;

enum Color {
    Red,
    Blue
}

public class Player {
    private Color color;
    private String name;

    public void setColor(String colorString) {
        this.color = Color.valueOf(colorString);
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
