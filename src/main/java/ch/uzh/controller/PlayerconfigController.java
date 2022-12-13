package ch.uzh.controller;

import ch.uzh.model.Player;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class PlayerconfigController {
    @FXML
    private ComboBox<String> CmbColorSelectionPlayer1;
    @FXML
    private TextField TxtNamePlayer1;

    private Player p1 = new Player();
    private Player p2 = new Player();

    @FXML
    public void initialize() {
        CmbColorSelectionPlayer1.getItems().addAll("Red", "Blue");
    }

    @FXML
    public void goNext() {
        System.out.println("next");
    }

    // TODO research onChange update handlders for Text & Combobox
}
