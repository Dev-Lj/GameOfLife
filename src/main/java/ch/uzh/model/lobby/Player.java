package ch.uzh.model.lobby;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Player implements LobbyPlayer {
    private String color;
    private String name;
    private int amountOfCells = 0;

    public static final List<String> PLAYERCOLORS = Collections.unmodifiableList(
        new ArrayList<String>() {
            {
                add("red");
                add("blue");
                add("green");
                add("yellow");
                add("orange");
                add("purple");
                add("black");
                add("white");
            }
        });

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

    public void validate() throws IllegalArgumentException{
        if (color == null) {
            throw new IllegalArgumentException("Color is missing");
        } else if (name == null || name.isEmpty() || name.isBlank()) {
            throw new IllegalArgumentException("Name is missing");
        }
    }

    public void decreaseAmountOfCells() {
        this.amountOfCells--;
    }

    public void increaseAmountOfCells() {
        this.amountOfCells++;
    }
}
