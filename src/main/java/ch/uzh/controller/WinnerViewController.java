package ch.uzh.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class WinnerViewController {
    @FXML private Label lblWinnerName;

    public void setWinnerName(String winnerName) {
        lblWinnerName.setText(winnerName);
    }
}
