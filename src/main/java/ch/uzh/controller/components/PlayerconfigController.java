package ch.uzh.controller.components;

import java.lang.reflect.Field;
import java.net.URL;
import java.util.ResourceBundle;

import ch.uzh.model.lobby.Player;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class PlayerconfigController implements Initializable {
    private Player player = new Player();
    private Label lab = new Label();
    private TextField txt = new TextField();
    ComboBox combnew = new ComboBox<String>();

    public PlayerconfigController(int i) {
        lab.setText("Player " + i);
    }

    /* Todo, keep colors in the model */

    private Color getColor(String colorName) {
        Color color;
        try {
            Field field = Class.forName("javafx.scene.paint.Color").getField(colorName);
            color = (Color) field.get(null);
        } catch (Exception e) {
            color = null;
        }
        return color;
    }

    /*
     * Todo, get colors from the model, add getField to View since its part of the
     * view
     */

    @FXML
    public HBox getField() {
        combnew.getItems().addAll("red", "blue", "green", "yellow", "pink", "orange",
                "purple");
        HBox hbox = new HBox(lab, combnew, txt);
        hbox.setSpacing(20);
        return hbox;
    }

    public void updatePlayer() {
        player.setName(txt.getText());
        try {
            player.setColor(combnew.getValue().toString());
        } catch (Exception e) {
            player.setColor(null);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public Player getPlayer() {
        return player;
    }

}
