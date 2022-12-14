package ch.uzh.model;

import java.util.ArrayList;
import java.util.List;

public class Grid {
    List<GridObserver> observers = new ArrayList<>();

    public void changeGrid() {
        notifyObservers();
    }

    public void attachObserver(GridObserver observer) {
        observers.add(observer);
    }

    private void notifyObservers() {
        for (GridObserver gridObserver : observers) {
            gridObserver.notifyUpdate();
        }
    }
}
