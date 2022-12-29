package ch.uzh.controller.components;

import java.net.URL;
import java.util.ResourceBundle;

import ch.uzh.model.lobby.LobbyPlayer;
import ch.uzh.model.lobby.Player;
import ch.uzh.view.components.PlayerConfigFields;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;

public class PlayerconfigController implements Initializable {
    private final int id;
    private Player player;
    private PlayerConfigFields pc;

    public PlayerconfigController(int id) {
        this.id = id;
        player = new Player();
        pc = new PlayerConfigFields(Player.PLAYERCOLORS, "Player " + id);
    }

    @FXML
    public void updatePlayer() {
        player.setName(pc.getName());
        try {
            player.setColor(pc.getColor());
        } catch (Exception e) {
            player.setColor(null);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    public LobbyPlayer getLobbyPlayer() throws IllegalArgumentException{
        try {
            player.validate();
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(String.format("Player %d: %s", id, e.getMessage()));
        }
        return player;
    }

    public HBox getItem() {
        return pc.getItem();
    }

}
