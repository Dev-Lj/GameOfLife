package ch.uzh.controller.components;

import java.net.URL;
import java.util.ResourceBundle;
import ch.uzh.model.lobby.Player;
import ch.uzh.model.lobby.PlayerConfig;
import ch.uzh.view.components.PlayerConfigFields;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;

public class PlayerconfigController implements Initializable {
    private Player player;
    private PlayerConfigFields pc;

    public PlayerconfigController(int id) {
        player = new Player(id);
        pc = new PlayerConfigFields(PlayerConfig.COLORS, "Player " + id);
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

    public Player getPlayer() {
        return player;
    }

    public HBox getItem() {
        return pc.getItem();
    }

}
