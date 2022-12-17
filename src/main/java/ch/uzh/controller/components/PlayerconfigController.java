package ch.uzh.controller.components;

import java.net.URL;
import java.util.ResourceBundle;

import ch.uzh.model.lobby.Color;
import ch.uzh.model.lobby.Player;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

public class PlayerconfigController implements Initializable{
    private Player player = new Player();

    @FXML
    private ComboBox<String> cmbColorSelection;
    @FXML
    private TextField txtName;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        cmbColorSelection.getItems().addAll("Red", "Blue");
    }

    private boolean validateName(String name) {
        // TODO method stub
        return true;
    }

    private boolean validateColor(Color color) {
        // TODO method stub
        return true;
    }

    public boolean validatePlayer() {
        System.out.println(player + " was validated.");
        return validateName(player.getName()) && validateColor(player.getColor());
    }

    public Player getPlayer() {
        return player;
    }

}
